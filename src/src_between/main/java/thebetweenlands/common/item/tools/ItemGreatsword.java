package thebetweenlands.common.item.tools;

import java.util.List;

import com.google.common.collect.Multimap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.event.ArmSwingSpeedEvent;
import thebetweenlands.api.item.IExtendedReach;
import thebetweenlands.client.tab.BLCreativeTabs;
import thebetweenlands.common.item.BLMaterialRegistry;
import thebetweenlands.common.registries.SoundRegistry;

public class ItemGreatsword extends ItemBLSword implements IExtendedReach {
	protected static final String NBT_SWING_START_COOLDOWN = "swingStartCooldownState";
	protected static final String NBT_SWING_START_TICKS = "swingStartTicks";
	protected static final String NBT_LONG_SWING_STATE = "longSwingState";

	public ItemGreatsword(ToolMaterial mat) {
		super(mat);
		setCreativeTab(BLCreativeTabs.GEARS);
	}

	public ItemGreatsword() {
		this(BLMaterialRegistry.TOOL_VALONITE);
	}

	protected double getAoEReach(EntityLivingBase entityLiving, ItemStack stack) {
		return 2.8D;
	}

	protected double getAoEHalfAngle(EntityLivingBase entityLiving, ItemStack stack) {
		return 45.0D;
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if (entityLiving instanceof EntityPlayer) {
			boolean enemiesInReach = false;

			EntityPlayer player = (EntityPlayer) entityLiving;

			if (!player.world.isRemote && !entityLiving.isSwingInProgress) {
				stack.setTagInfo(NBT_SWING_START_COOLDOWN, new NBTTagFloat(player.getCooledAttackStrength(0)));
				stack.setTagInfo(NBT_SWING_START_TICKS, new NBTTagInt(player.ticksExisted));
				stack.setTagInfo(NBT_LONG_SWING_STATE, new NBTTagByte((byte) 1));
			}

			double aoeReach = this.getAoEReach(entityLiving, stack);
			double aoeHalfAngle = this.getAoEHalfAngle(entityLiving, stack);

			List<EntityLivingBase> others = entityLiving.world.getEntitiesWithinAABB(EntityLivingBase.class,
					entityLiving.getEntityBoundingBox().grow(aoeReach));
			for (EntityLivingBase target : others) {
				if (target != entityLiving) {
					double dist = target.getDistance(entityLiving);

					if (dist < aoeReach) {
						double angle = Math.min(
								Math.toDegrees(
										Math.acos(target.getPositionVector().subtract(entityLiving.getPositionVector())
												.normalize().dotProduct(entityLiving.getLookVec()))),
								Math.min(
										Math.toDegrees(Math.acos(
												target.getPositionVector().subtract(entityLiving.getPositionEyes(1))
														.normalize().dotProduct(entityLiving.getLookVec()))),
										Math.toDegrees(Math.acos(target.getPositionVector().add(0, target.height / 2, 0)
												.subtract(entityLiving.getPositionEyes(1)).normalize()
												.dotProduct(entityLiving.getLookVec())))));

						if (angle < aoeHalfAngle) {
							double distXZ = Math
									.sqrt((target.posX - entityLiving.posX) * (target.posX - entityLiving.posX)
											+ (target.posZ - entityLiving.posZ) * (target.posZ - entityLiving.posZ));

							double hitY = entityLiving.posY + entityLiving.getEyeHeight()
									+ entityLiving.getLookVec().y / Math.sqrt(Math.pow(entityLiving.getLookVec().x, 2)
											+ Math.pow(entityLiving.getLookVec().z, 2) + 0.1D) * distXZ;

							if (hitY > target.getEntityBoundingBox().minY - 0.25D
									&& hitY < target.getEntityBoundingBox().maxY + 0.25D) {
								if (player.world.rayTraceBlocks(
										player.getPositionVector().add(0, player.getEyeHeight(), 0),
										target.getPositionVector().add(0, target.height / 2, 0), false, true,
										false) == null) {
									if (!entityLiving.world.isRemote) {
										player.attackTargetEntityWithCurrentItem(target);
									}

									enemiesInReach = true;
								}
							}
						}
					}
				}
			}

			if (entityLiving.world.isRemote && (!entityLiving.isSwingInProgress
					|| entityLiving.swingProgressInt >= entityLiving.getArmSwingAnimationEnd() / 2
					|| entityLiving.swingProgressInt < 0)) {
				this.playSwingSound(player, stack);

				if (enemiesInReach) {
					this.playSliceSound(player, stack);
				}
			}
		}

		return super.onEntitySwing(entityLiving, stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity holder, int slot, boolean isHeldItem) {
		super.onUpdate(stack, world, holder, slot, isHeldItem);

		if (!world.isRemote) {
			boolean swingInProgress = this.isLongSwingInProgress(stack);
			boolean newSwingInProgress = false;

			if (holder instanceof EntityLivingBase && ((EntityLivingBase) holder).getHeldItemMainhand() == stack) {
				int ticksElapsed = holder.ticksExisted - this.getSwingStartTicks(stack);
				newSwingInProgress = ticksElapsed >= 0
						&& ticksElapsed < this.getLongSwingDuration((EntityLivingBase) holder, stack);
			}

			if (swingInProgress != newSwingInProgress) {
				stack.setTagInfo(NBT_LONG_SWING_STATE, new NBTTagByte(newSwingInProgress ? (byte) 1 : (byte) 0));
			}
		}
	}

	protected float getSwingStartCooledAttackStrength(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null && tag.hasKey(NBT_SWING_START_COOLDOWN, Constants.NBT.TAG_FLOAT)) {
			return tag.getFloat(NBT_SWING_START_COOLDOWN);
		}
		return 0.0f;
	}

	protected int getSwingStartTicks(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null && tag.hasKey(NBT_SWING_START_TICKS, Constants.NBT.TAG_INT)) {
			return tag.getInteger(NBT_SWING_START_TICKS);
		}
		return 0;
	}

	protected boolean isLongSwingInProgress(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null && tag.hasKey(NBT_LONG_SWING_STATE, Constants.NBT.TAG_BYTE)) {
			return tag.getBoolean(NBT_LONG_SWING_STATE);
		}
		return false;
	}

	protected float getLongSwingDuration(EntityLivingBase entity, ItemStack stack) {
		return entity.getArmSwingAnimationEnd() / 3.0f / this.getSwingSpeedMultiplier(entity, stack);
	}

	protected void playSwingSound(EntityPlayer player, ItemStack stack) {
		player.world.playSound(player, player.posX, player.posY, player.posZ, SoundRegistry.LONG_SWING,
				SoundCategory.PLAYERS, 1.2F,
				0.925F * ((0.65F + this.getSwingSpeedMultiplier(player, stack)) * 0.66F + 0.33F)
						+ player.world.rand.nextFloat() * 0.15F);
	}

	protected void playSliceSound(EntityPlayer player, ItemStack stack) {
		player.world.playSound(player, player.posX, player.posY, player.posZ, SoundRegistry.LONG_SLICE,
				SoundCategory.PLAYERS, 1.2F,
				0.925F * ((0.65F + this.getSwingSpeedMultiplier(player, stack)) * 0.66F + 0.33F)
						+ player.world.rand.nextFloat() * 0.15F);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		target.knockBack(attacker, 0.8F, (double) MathHelper.sin(attacker.rotationYaw * 0.017453292F),
				(double) (-MathHelper.cos(attacker.rotationYaw * 0.017453292F)));
		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);
		if (slot == EntityEquipmentSlot.MAINHAND) {
			modifiers.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
			modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.0D, 0));
		}
		return modifiers;
	}

	@Override
	public int getMinRepairFuelCost(ItemStack stack) {
		return BLMaterialRegistry.getMinRepairFuelCost(BLMaterialRegistry.TOOL_LEGEND);
	}

	@Override
	public int getFullRepairFuelCost(ItemStack stack) {
		return BLMaterialRegistry.getFullRepairFuelCost(BLMaterialRegistry.TOOL_LEGEND);
	}

	@Override
	public int getMinRepairLifeCost(ItemStack stack) {
		return BLMaterialRegistry.getMinRepairLifeCost(BLMaterialRegistry.TOOL_LEGEND);
	}

	@Override
	public int getFullRepairLifeCost(ItemStack stack) {
		return BLMaterialRegistry.getFullRepairLifeCost(BLMaterialRegistry.TOOL_LEGEND);
	}

	@Override
	public double getReach() {
		return 5.5;
	}

	protected float getSwingSpeedMultiplier(EntityLivingBase entity, ItemStack stack) {
		return 0.35F;
	}

	protected boolean doesBlockShieldUse(EntityLivingBase entity, ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	private static boolean renderingHand = false;

	@SubscribeEvent
	public static void onStartUsingItem(LivingEntityUseItemEvent.Start event) {
		if (handleItemUse(event.getEntityLiving(), event.getItem())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onStartUsingItem(PlayerInteractEvent.RightClickItem event) {
		if (handleItemUse(event.getEntityLiving(), event.getEntityLiving().getHeldItem(event.getHand()))) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onItemUsing(LivingEntityUseItemEvent.Tick event) {
		if (handleItemUse(event.getEntityLiving(), event.getItem())) {
			event.setCanceled(true);
		}
	}

	private static boolean handleItemUse(EntityLivingBase entity, ItemStack useStack) {
		if (!useStack.isEmpty() && useStack.getItem().isShield(useStack, entity)) {
			for (EnumHand hand : EnumHand.values()) {
				ItemStack stack = entity.getHeldItem(hand);

				if (!stack.isEmpty() && stack.getItem() instanceof ItemGreatsword
						&& ((ItemGreatsword) stack.getItem()).doesBlockShieldUse(entity, stack)) {
					return true;
				}
			}
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onArmSwingSpeed(ArmSwingSpeedEvent event) {
		EntityLivingBase entity = event.getEntityLiving();

		if (entity.isSwingInProgress && entity.swingingHand != null) {
			ItemStack stack = entity.getHeldItem(entity.swingingHand);

			if (!stack.isEmpty() && stack.getItem() instanceof ItemGreatsword) {
				event.setSpeed(
						event.getSpeed() * ((ItemGreatsword) stack.getItem()).getSwingSpeedMultiplier(entity, stack));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onRenderHand(RenderSpecificHandEvent event) {
		if (!renderingHand) {
			ItemStack stack = event.getItemStack();

			if (!stack.isEmpty() && stack.getItem() instanceof ItemGreatsword) {
				event.setCanceled(true);

				renderingHand = true;

				try {
					GlStateManager.pushMatrix();

					float drive = event.getSwingProgress();

					float driveScale = 0.05f;
					float drivePow = 2f;

					drive = (float) (1 - Math.pow((1 - drive) * driveScale, drivePow) / Math.pow(driveScale, drivePow));

					float xOff = -0.65f;
					float yOff = 1f;
					float zOff = 0.85f;

					float leftMove = (float) Math.sin(drive * Math.PI);

					float roll = (float) Math.sin(Math.min(drive * Math.PI * 2, Math.PI / 2));
					float roll2 = (drive > 0.75F ? (float) Math.pow(Math.sin((drive - 0.75F) * Math.PI * 2), 3) : 0);
					float yaw = (float) Math.sin(drive * Math.PI);

					GlStateManager.translate(leftMove * -1.2f, leftMove * 0.7f - event.getEquipProgress() * 0.2f, 0);

					GlStateManager.translate(-xOff, -yOff, -zOff);
					GlStateManager.rotate(roll * -90, 0, 0, 1);
					GlStateManager.rotate(yaw * -190, 1, 0, 0);
					GlStateManager.rotate(roll2 * 90, 0, 0, 1);
					GlStateManager.translate(xOff, yOff, zOff);

					float equipProg = 0 /* event.getEquipProgress() */;
					float swingProg = 0 /* event.getSwingProgress() */;

					// Give other listeners the chance to render their own custom hand or item (e.g.
					// decay renderer)
					if (!ForgeHooksClient.renderSpecificFirstPersonHand(event.getHand(), event.getPartialTicks(),
							event.getInterpolatedPitch(), swingProg, equipProg, event.getItemStack())) {
						Minecraft mc = Minecraft.getMinecraft();
						mc.getItemRenderer().renderItemInFirstPerson(mc.player, event.getPartialTicks(),
								event.getInterpolatedPitch(), event.getHand(), swingProg, event.getItemStack(),
								equipProg);
					}

					GlStateManager.popMatrix();
				} finally {
					renderingHand = false;
				}
			}
		}
	}
}
