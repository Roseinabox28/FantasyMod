package thebetweenlands.common.tile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.entity.IEntityBL;
import thebetweenlands.api.entity.IEntityScreenShake;
import thebetweenlands.api.storage.ILocalStorageHandler;
import thebetweenlands.client.audio.DecayPitGearsSound;
import thebetweenlands.client.render.particle.BLParticles;
import thebetweenlands.client.render.particle.BatchedParticleRenderer;
import thebetweenlands.client.render.particle.DefaultParticleBatches;
import thebetweenlands.client.render.particle.ParticleFactory;
import thebetweenlands.client.render.particle.entity.ParticleGasCloud;
import thebetweenlands.common.entity.EntityRootGrabber;
import thebetweenlands.common.entity.EntityTriggeredSludgeWallJet;
import thebetweenlands.common.entity.mobs.EntityChiromaw;
import thebetweenlands.common.entity.mobs.EntityLargeSludgeWorm;
import thebetweenlands.common.entity.mobs.EntityShambler;
import thebetweenlands.common.entity.mobs.EntitySludge;
import thebetweenlands.common.entity.mobs.EntitySludgeJet;
import thebetweenlands.common.entity.mobs.EntitySludgeWorm;
import thebetweenlands.common.entity.mobs.EntitySmollSludge;
import thebetweenlands.common.entity.mobs.EntitySwampHag;
import thebetweenlands.common.entity.mobs.EntityTermite;
import thebetweenlands.common.entity.mobs.EntityTinySludgeWorm;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.SoundRegistry;
import thebetweenlands.common.world.gen.feature.structure.utils.SludgeWormMazeBlockHelper;
import thebetweenlands.common.world.storage.BetweenlandsWorldStorage;
import thebetweenlands.common.world.storage.location.LocationSludgeWormDungeon;

public class TileEntityDecayPitControl extends TileEntity implements ITickable, IEntityScreenShake {

	public float animationTicks = 0;
	public float animationTicksPrev = 0;
	public float plugDropTicks = 0;
	public float plugDropTicksPrev = 0;
	public float floorFadeTicks = 0;
	public float floorFadeTicksPrev = 0;
	public int spawnType = 0;
	public boolean IS_PLUGGED = false;
	public boolean SHOW_FLOOR = true;
	private int prev_shake_timer;
	private int shake_timer;
	private boolean shaking = false;
	private int shakingTimerMax = 60;
	public boolean playGearSound = true;
	private SludgeWormMazeBlockHelper blockHelper = new SludgeWormMazeBlockHelper(null);
	protected final Map<Block, Boolean> invisibleBlocks = new HashMap<Block, Boolean>(); // dont need states so blocks
																							// will do

	public TileEntityDecayPitControl() {
		initInvisiBlockMap();
	}

	private void initInvisiBlockMap() {
		if (invisibleBlocks.isEmpty()) {
			invisibleBlocks.put(blockHelper.DECAY_PIT_INVISIBLE_FLOOR_BLOCK.getBlock(), true);
			invisibleBlocks.put(blockHelper.DECAY_PIT_INVISIBLE_FLOOR_BLOCK_DIAGONAL.getBlock(), true);
			invisibleBlocks.put(blockHelper.DECAY_PIT_INVISIBLE_FLOOR_BLOCK_L_1.getBlock(), true);
			invisibleBlocks.put(blockHelper.DECAY_PIT_INVISIBLE_FLOOR_BLOCK_L_2.getBlock(), true);
			invisibleBlocks.put(blockHelper.DECAY_PIT_INVISIBLE_FLOOR_BLOCK_R_1.getBlock(), true);
			invisibleBlocks.put(blockHelper.DECAY_PIT_INVISIBLE_FLOOR_BLOCK_R_2.getBlock(), true);
		}
	}

	public boolean isInvisibleBlock(Block block) {
		return invisibleBlocks.get(block) != null;
	}

	@Override
	public void update() {
		if (!isPlugged()) {
			animationTicksPrev = animationTicks;

			animationTicks += 1F;
			if (animationTicks >= 360F)
				animationTicks = animationTicksPrev = 0;

			if (!getWorld().isRemote) {

				if (animationTicks == 15 || animationTicks == 195) {
					spawnSludgeJet(getPos().getX() + 5.5D, getPos().getY() + 3D, getPos().getZ() - 1.5D);
					spawnSludgeJet(getPos().getX() - 4.5D, getPos().getY() + 3D, getPos().getZ() + 2.5D);
				}

				if (animationTicks == 60 || animationTicks == 240) {
					spawnSludgeJet(getPos().getX() + 2.5D, getPos().getY() + 3D, getPos().getZ() - 4.5D);
					spawnSludgeJet(getPos().getX() - 1.5D, getPos().getY() + 3D, getPos().getZ() + 5.5D);
				}

				if (animationTicks == 105 || animationTicks == 285) {
					spawnSludgeJet(getPos().getX() - 1.5D, getPos().getY() + 3D, getPos().getZ() - 4.5D);
					spawnSludgeJet(getPos().getX() + 2.5D, getPos().getY() + 3D, getPos().getZ() + 5.5D);
				}

				if (animationTicks == 150 || animationTicks == 330) {
					spawnSludgeJet(getPos().getX() - 4.5D, getPos().getY() + 3D, getPos().getZ() - 1.5D);
					spawnSludgeJet(getPos().getX() + 5.5D, getPos().getY() + 3D, getPos().getZ() + 2.5D);
				}

				// TODO remove ghetto syncing
				if (getWorld().getTotalWorldTime() % 20 == 0)
					updateBlock();

				if (getWorld().getTotalWorldTime() % 1200 == 0) { // once a
																	// minute
					// S
					checkTurretSpawn(4, 12, 11);
					checkTurretSpawn(-4, 12, 11);
					// E
					checkTurretSpawn(11, 12, 4);
					checkTurretSpawn(11, 12, -4);
					// N
					checkTurretSpawn(4, 12, -11);
					checkTurretSpawn(-4, 12, -11);
					// W
					checkTurretSpawn(-11, 12, -4);
					checkTurretSpawn(-11, 12, 4);
				}

				// spawn stuff here
				if (getWorld().getTotalWorldTime() % 80 == 0) {
					Entity thing = getEntitySpawned(getSpawnType());
					if (thing != null) {
						thing.setPosition(getPos().getX() + 0.5D, getPos().getY() + 1D, getPos().getZ() + 0.5D);
						getWorld().spawnEntity(thing);
					}
				}
				if (getSpawnType() == 5) {
					setPlugged(true);
					removeInvisiBlocks(getWorld(), getPos());
					updateBlock();
					getWorld().playSound(null, getPos().add(1, 6, 0), SoundEvents.BLOCK_ANVIL_BREAK,
							SoundCategory.HOSTILE, 0.5F, 1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
					getWorld().playSound(null, getPos().add(-1, 6, 0), SoundEvents.BLOCK_ANVIL_BREAK,
							SoundCategory.HOSTILE, 0.5F, 1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
					getWorld().playSound(null, getPos().add(0, 6, 1), SoundEvents.BLOCK_ANVIL_BREAK,
							SoundCategory.HOSTILE, 0.5F, 1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
					getWorld().playSound(null, getPos().add(0, 6, -1), SoundEvents.BLOCK_ANVIL_BREAK,
							SoundCategory.HOSTILE, 0.5F, 1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				}
			} else {
				this.spawnAmbientParticles();
			}
			checkSurfaceCollisions();
		}

		if (isPlugged()) {
			plugDropTicksPrev = plugDropTicks;
			floorFadeTicksPrev = floorFadeTicks;
			if (getWorld().isRemote) {
				if (plugDropTicks <= 0.8F) {
					chainBreakParticles(getWorld(), getPos().add(1, 6, 0));
					chainBreakParticles(getWorld(), getPos().add(-1, 6, 0));
					chainBreakParticles(getWorld(), getPos().add(0, 6, 1));
					chainBreakParticles(getWorld(), getPos().add(0, 6, -1));
				}
			}

			if (plugDropTicks <= 1.6F)
				plugDropTicks += 0.2F;

			if (plugDropTicks == 0.6F) {
				shaking = true;
				if (!getWorld().isRemote)
					getWorld().playSound(null, getPos(), SoundRegistry.PLUG_LOCK, SoundCategory.HOSTILE, 1F, 1F);
			}
			if (plugDropTicks > 1.6F && plugDropTicks <= 2)
				plugDropTicks += 0.1F;

			if (plugDropTicks >= 2)
				if (getShowFloor())
					floorFadeTicks += 0.025F;

			if (floorFadeTicks >= 1)
				if (!getWorld().isRemote) {
					setShowFloor(false);
					updateBlock();
				}
			if (shaking)
				shake(60);

			// Remove dungeon locations
			ILocalStorageHandler storageHandler = BetweenlandsWorldStorage.forWorld(this.world)
					.getLocalStorageHandler();
			storageHandler
					.getLocalStorages(LocationSludgeWormDungeon.class, new AxisAlignedBB(this.getPos()), l -> true)
					.forEach(location -> location.removeLocations());

			// TODO;
			// render plug as animation falling in to place in the hole *DONE
			// remove invisible blocks from edges of pit *DONE
			// animate floor so it fades away *DONE
			// whatever whizz bangs we add with shaders and particles
			// spawn loots and stuff
		}

		if (getWorld().isRemote) {
			if (!isPlugged())
				if (playGearSound) {
					playGearsSound(getWorld(), getPos());
					playGearSound = false;
				}
		}
	}

	@SideOnly(Side.CLIENT)
	public void playGearsSound(World world, BlockPos pos) {
		ISound chain_sound = new DecayPitGearsSound(this);
		Minecraft.getMinecraft().getSoundHandler().playSound(chain_sound);
	}

	@SideOnly(Side.CLIENT)
	public void chainBreakParticles(World world, BlockPos pos) {
		double px = pos.getX() + 0.5D;
		double py = pos.getY() + 0.5D;
		double pz = pos.getZ() + 0.5D;
		for (int i = 0, amount = 10; i < amount; i++) {
			double ox = getWorld().rand.nextDouble() * 0.6F - 0.3F;
			double oz = getWorld().rand.nextDouble() * 0.6F - 0.3F;
			double motionX = getWorld().rand.nextDouble() * 0.4F - 0.2F;
			double motionY = getWorld().rand.nextDouble() * 0.3F + 0.075F;
			double motionZ = getWorld().rand.nextDouble() * 0.4F - 0.2F;
			world.spawnParticle(EnumParticleTypes.BLOCK_DUST, px + ox, py, pz + oz, motionX, motionY, motionZ,
					Block.getStateId(BlockRegistry.DECAY_PIT_HANGING_CHAIN.getDefaultState()));
		}
	}

	private void removeInvisiBlocks(World world, BlockPos pos) {
		Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-4F, 2F, -4F), pos.add(4F, 2F, 4F));
		for (BlockPos posIteration : blocks)
			if (isInvisibleBlock(getWorld().getBlockState(posIteration).getBlock()))
				world.setBlockToAir(posIteration);
	}

	private void checkTurretSpawn(int x, int y, int z) {
		BlockPos checkPos = getPos().add(x, y, z);
		AxisAlignedBB checkBox = new AxisAlignedBB(checkPos);
		List<EntityTriggeredSludgeWallJet> entityList = getWorld()
				.getEntitiesWithinAABB(EntityTriggeredSludgeWallJet.class, checkBox);
		for (EntityTriggeredSludgeWallJet entity : entityList) {
			if (entity instanceof EntityTriggeredSludgeWallJet) {
				break;
			}
		}
		if (entityList.isEmpty()) {
			EntityTriggeredSludgeWallJet jet = new EntityTriggeredSludgeWallJet(getWorld());
			jet.setPosition(checkPos.getX() + 0.5D, checkPos.getY(), checkPos.getZ() + 0.5D);
			getWorld().spawnEntity(jet);
		}
	}

	@SideOnly(Side.CLIENT)
	private void spawnAmbientParticles() {
		BlockPos pos = this.getPos();

		double x = pos.getX() + 0.5D + (this.world.rand.nextFloat() - 0.5F) / 2.0F;
		double y = pos.getY() + 1.5D;
		double z = pos.getZ() + 0.5D + (this.world.rand.nextFloat() - 0.5F) / 2.0F;
		double mx = (this.world.rand.nextFloat() - 0.5F) * 0.08F;
		double my = this.world.rand.nextFloat() * 0.175F;
		double mz = (this.world.rand.nextFloat() - 0.5F) * 0.08F;
		int[] color = { 100, 70, 0, 255 };

		ParticleGasCloud hazeParticle = (ParticleGasCloud) BLParticles.GAS_CLOUD.create(this.world, x, y, z,
				ParticleFactory.ParticleArgs.get().withData(null).withMotion(mx, my, mz)
						.withColor(color[0] / 255.0F, color[1] / 255.0F, color[2] / 255.0F, color[3] / 255.0F)
						.withScale(8f));

		BatchedParticleRenderer.INSTANCE.addParticle(DefaultParticleBatches.GAS_CLOUDS_HEAT_HAZE, hazeParticle);

		ParticleGasCloud particle = (ParticleGasCloud) BLParticles.GAS_CLOUD.create(this.world, x, y, z,
				ParticleFactory.ParticleArgs.get().withData(null).withMotion(mx, my, mz)
						.withColor(color[0] / 255.0F, color[1] / 255.0F, color[2] / 255.0F, color[3] / 255.0F)
						.withScale(4f));

		BatchedParticleRenderer.INSTANCE.addParticle(DefaultParticleBatches.GAS_CLOUDS_TEXTURED, particle);
	}

	private void updateBlock() {
		getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 3);
	}

	private Entity checkSurfaceCollisions() {
		boolean reverse = false;
		for (Entity entity : getEntityAbove()) {
			if (entity != null && !(entity instanceof EntitySludgeJet) && !(entity instanceof EntityRootGrabber)
					&& !(entity instanceof IEntityBL)) {
				if (entity instanceof EntityArrow)
					entity.setDead();
				if (getDistance(entity) >= 4.25F - entity.width * 0.5F
						&& getDistance(entity) <= 7F + entity.width * 0.5F) {
					reverse = false;
					if (entity.posY <= getPos().getY() + 3D) {
						entity.motionX = 0D;
						entity.motionY = 0.1D;
						entity.motionZ = 0D;
					} else if (entity.motionY < 0) {
						entity.motionY = 0;
						checkJumpOnTopOfAABB(entity);
					}
				}

				if (getDistance(entity) < 4.25F - entity.width * 0.5F
						&& getDistance(entity) >= 2.5F + entity.width * 0.5F) {
					if (entity.posY <= getPos().getY() + 2D + 0.0625D) {
						reverse = true;
						checkJumpOnTopOfAABB(entity);
					}
				}

				if (getDistance(entity) >= 2.5F + entity.width * 0.5F) {
					Vec3d center = new Vec3d(getPos().getX() + 0.5D, 0, getPos().getZ() + 0.5D);
					Vec3d entityOffset = new Vec3d(entity.posX, 0, entity.posZ);

					double dist = entityOffset.distanceTo(center);
					double circumference = 2 * Math.PI * dist;
					double speed = circumference / 360 * (reverse ? 1F : 0.75F) /* angle per tick */;

					Vec3d push = new Vec3d(0, 1, 0).crossProduct(entityOffset.subtract(center).normalize()).normalize()
							.scale(reverse ? -speed : speed);

					if (!entity.world.isRemote || entity instanceof EntityPlayer) {
						entity.move(MoverType.SELF, push.x, 0, push.z);
					}
				}
			}
		}
		return null;
	}

	public float getDistance(Entity entityIn) {
		float f = (float) (getPos().getX() + 0.5D - entityIn.posX);
		float f1 = (float) (getPos().getY() + 2D - entityIn.posY);
		float f2 = (float) (getPos().getZ() + 0.5D - entityIn.posZ);
		return MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
	}

	public void checkJumpOnTopOfAABB(Entity entity) {
		if (entity.getEntityWorld().isRemote && entity instanceof EntityPlayer) {
			boolean jump = Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();
			if (jump)
				((EntityPlayer) entity).jump();
		}
	}

	public List<Entity> getEntityAbove() {
		return getWorld().<Entity>getEntitiesWithinAABB(Entity.class, getFloorEntityBoundingBox(),
				EntitySelectors.IS_ALIVE);
	}

	private AxisAlignedBB getFloorEntityBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(7D, 0.0625D, 7D).offset(0D, 2D, 0D);
	}

	private AxisAlignedBB getSpawningBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(12D, 6D, 12D).offset(0D, 6D, 0D);
	}

	private void spawnSludgeJet(double posX, double posY, double posZ) {
		EntitySludgeJet jet = new EntitySludgeJet(getWorld());
		jet.setPosition(posX, posY, posZ);
		getWorld().spawnEntity(jet);
	}

	public void setSpawnType(int spawn_type) {
		spawnType = spawn_type;
	}

	public int getSpawnType() {
		return spawnType;
	}

	protected Entity getEntitySpawned(int spawnType) {
		List<EntityLivingBase> list = getWorld().getEntitiesWithinAABB(EntityLivingBase.class,
				getSpawningBoundingBox());
		if (list.stream().filter(e -> e instanceof IMob).count() >= 5
				&& list.stream().filter(e -> e instanceof IEntityBL).count() >= 5)
			return null;
		Entity spawned_entity = null;
		Random rand = getWorld().rand;
		switch (spawnType) {
		case 0:
			return rand.nextBoolean() ? new EntityTinySludgeWorm(getWorld())
					: rand.nextBoolean() ? new EntitySmollSludge(getWorld())
							: rand.nextBoolean() ? new EntityTermite(getWorld())
									: new EntityLargeSludgeWorm(getWorld());
		case 1:
			return rand.nextBoolean() ? new EntitySludgeWorm(getWorld())
					: rand.nextBoolean() ? new EntityChiromaw(getWorld()) : new EntityLargeSludgeWorm(getWorld());
		case 2:
			return rand.nextBoolean() ? new EntitySwampHag(getWorld())
					: rand.nextBoolean() ? new EntitySludge(getWorld()) : new EntityLargeSludgeWorm(getWorld());
		case 3:
			return rand.nextBoolean() ? new EntityShambler(getWorld())
					: rand.nextBoolean() ? new EntityChiromaw(getWorld()) : new EntityLargeSludgeWorm(getWorld());
		case 4:
			return new EntityLargeSludgeWorm(getWorld());
		}
		return spawned_entity;
	}

	public void setPlugged(boolean plugged) {
		IS_PLUGGED = plugged;
	}

	public boolean isPlugged() {
		return IS_PLUGGED;
	}

	public boolean isUnPlugged() {
		return !IS_PLUGGED;
	}

	public void setShowFloor(boolean show_floor) {
		SHOW_FLOOR = show_floor;
	}

	public boolean getShowFloor() {
		return SHOW_FLOOR;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setFloat("animationTicks", animationTicks);
		nbt.setInteger("spawnType", getSpawnType());
		nbt.setFloat("plugDropTicks", plugDropTicks);
		nbt.setBoolean("plugged", isPlugged());
		nbt.setBoolean("showFloor", getShowFloor());
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		animationTicks = nbt.getFloat("animationTicks");
		setSpawnType(nbt.getInteger("spawnType"));
		plugDropTicks = nbt.getFloat("plugDropTicks");
		setPlugged(nbt.getBoolean("plugged"));
		setShowFloor(nbt.getBoolean("showFloor"));
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		return writeToNBT(nbt);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return super.getRenderBoundingBox().grow(10);
	}

	public void shake(int shakeTimerMax) {
		shakingTimerMax = shakeTimerMax;
		prev_shake_timer = shake_timer;
		if (shake_timer == 0) {
			shaking = true;
			shake_timer = 1;
		}
		if (shake_timer > 0)
			shake_timer++;

		if (shake_timer >= shakingTimerMax)
			shaking = false;
		else
			shaking = true;
	}

	@Override
	public float getShakeIntensity(Entity viewer, float partialTicks) {
		if (isShaking()) {
			double dist = getShakeDistance(viewer);
			float shakeMult = (float) (1.0F - dist / 10.0F);
			if (dist >= 10.0F) {
				return 0.0F;
			}
			return (float) ((Math.sin(getShakingProgress(partialTicks) * Math.PI) + 0.1F) * 2F * shakeMult);
		} else {
			return 0.0F;
		}
	}

	public float getShakeDistance(Entity entity) {
		float distX = (float) (getPos().getX() - entity.getPosition().getX());
		float distY = (float) (getPos().getY() - entity.getPosition().getY());
		float distZ = (float) (getPos().getZ() - entity.getPosition().getZ());
		return MathHelper.sqrt(distX * distX + distY * distY + distZ * distZ);
	}

	public boolean isShaking() {
		return shaking;
	}

	public float getShakingProgress(float delta) {
		return 1.0F / shakingTimerMax * (prev_shake_timer + (shake_timer - prev_shake_timer) * delta);
	}
}
