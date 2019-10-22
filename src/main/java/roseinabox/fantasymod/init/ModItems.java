package roseinabox.fantasymod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import roseinabox.fantasymod.Main;
import roseinabox.fantasymod.items.FireStaff;
import roseinabox.fantasymod.items.Fragment;
import roseinabox.fantasymod.items.ItemBase;
import roseinabox.fantasymod.items.ItemRune;
import roseinabox.fantasymod.items.ItemScroll;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<String> FRAGS = new ArrayList<String>();
	
	public static final Item CRYSTAL = new ItemBase("crystal",CreativeTabs.TOOLS);
	public static final Item FIRE_SCROLL = new ItemScroll("fire_scroll");
	
	public static final Item OLD_RUNE = new ItemRune("old_rune", CreativeTabs.TOOLS, "used");
	public static final Item FIRE_STAFF = new FireStaff("fire_staff", ToolMaterial.WOOD);
	
	public static final Item POWER = new Fragment("power",CreativeTabs.MATERIALS, "power");
	public static final Item LIFE = new Fragment("life",CreativeTabs.MATERIALS, "life");
	
	
	
	
}
