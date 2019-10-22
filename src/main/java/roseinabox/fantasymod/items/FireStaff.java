package roseinabox.fantasymod.items;



import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import roseinabox.fantasymod.Main;
import roseinabox.fantasymod.init.ModItems;
import roseinabox.fantasymod.util.IHasModel;

public class FireStaff extends ItemSword implements IHasModel{


	    public FireStaff(String name, ToolMaterial material)
	    {
	    	super (material);
	        setUnlocalizedName(name);
	        setRegistryName(name);
	        ModItems.ITEMS.add(this);
	    }
	
	    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn)
	    {
	        ItemStack itemstack = player.getHeldItem(handIn);

	        if (!player.capabilities.isCreativeMode && this.getDamage(itemstack) < this.getMaxDamage())
	        {
	            this.setDamage(itemstack, this.getDamage(itemstack) + 1);;
	        }

	        

	        if (!world.isRemote && this.getDamage(itemstack) < this.getMaxDamage())
	        {
	        	
	          world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL, 1.5F, 1.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	        	
	          Vec3d v3 = player.getLook(1);
	          
	          
	          EntitySmallFireball smallfireball = new EntitySmallFireball(world);
	          smallfireball.setVelocity(v3.x, v3.y, v3.z);
	          world.spawnEntity(smallfireball);
	          
	          
	        }

	        player.addStat(StatList.getObjectUseStats(this));
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	    }
	    
	  
	    
	    @Override
		public void registerModels() {
			
			Main.proxy.registerItemRenderer(this, 0, "inventory");
			
		}
	    
}
