package roseinabox.fantasymod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import roseinabox.fantasymod.blocks.BlockBase;
import roseinabox.fantasymod.blocks.BlockFluid;
import roseinabox.fantasymod.blocks.CorruptBlock;
import roseinabox.fantasymod.blocks.EldritchBlock;
import roseinabox.fantasymod.blocks.FragOre;
import roseinabox.fantasymod.blocks.TeleporterBlock;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<String> FRAGMENTS = new ArrayList<String>();
	/**
	 * Overworld Blocks
	 */
	public static final Block GENERIC_STONE = new BlockBase("generic_stone", Material.GROUND );
	
	/**
	 * Fragments
	 */
	public static final Block POWER_FRAG_ORE = new FragOre("power_frag_ore", Material.ROCK, "power", 3, 1);
	public static final Block LIFE_FRAG_ORE = new FragOre("life_frag_ore", Material.ROCK, "life", 2, 1);
	
	
	/**
	 * Structure Blocks
	 */
	public static final Block ELDRITCH_STONE = new EldritchBlock("eldritch_stone", Material.ROCK);
	
	/**
	 * Liquid Blocks
	 */
	public static final Block ETHER_BLOCK = new BlockFluid("ether", FluidInit.ETHER_FLUID, Material.WATER);
	
	/**
	 * Wasteland Blocks
	 */
	public static final Block WASTELAND_BRICK = new BlockBase("wl_brick", Material.ROCK);
	public static final Block CORRUPT_GRASS = new CorruptBlock("corrupt_grass", Material.GRASS, "grass");
	//public static final Block BEDROCK = new BlockBase("corrupt_bedrock", Material.GROUND);
	//public static final Block WASTELAND_PORTAL = new TeleporterBlock("wasteland_portal", 28);
	
}
