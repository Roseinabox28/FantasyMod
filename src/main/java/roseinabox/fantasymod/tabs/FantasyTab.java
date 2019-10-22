package roseinabox.fantasymod.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import roseinabox.fantasymod.init.ModBlocks;

public class FantasyTab extends CreativeTabs{

	public FantasyTab(String label) {
		super("fantasytab");
		//this.setBackgroundImageName("fantasy.png");
		
	}

	@Override
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return new ItemStack(Item.getItemFromBlock(ModBlocks.WASTELAND_BRICK));
	}

}
