package roseinabox.fantasymod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import roseinabox.fantasymod.init.ModItems;

public class ItemRune extends ItemBase{
	
	public Style style = new Style().setColor(TextFormatting.GREEN).setBold(true);
	private static String runeType;
	public ItemRune(String name, CreativeTabs tab, String type) {
		super(name, tab);
		this.runeType = type;
		this.setCreativeTab(tab);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn)
    {
		ItemStack itemstack = player.getHeldItem(handIn);
		 
		player.sendMessage(new TextComponentString("Item Rune used").setStyle(style));
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		
    }

}
