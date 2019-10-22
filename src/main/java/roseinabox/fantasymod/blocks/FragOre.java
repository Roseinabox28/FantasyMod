package roseinabox.fantasymod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.init.ModItems;

public class FragOre extends BlockBase {
	
	private static String fragType;
	private static int maxDrop = 4;
	private static int minDrop = 1;
	private static int meta;
	protected static int index;
	//public static final PropertyEnum<FragOre.EnumType> VARIANT = PropertyEnum.<FragOre.EnumType>create("variant", FragOre.EnumType.class);
	public FragOre(String name, Material material, String type, int max, int min) {
		
		super(name, material);
		this.setSoundType(SoundType.METAL);
		this.setHardness(5.0F);
		this.setResistance(15.0F);
		
		this.setHarvestLevel("pickaxe", 1);
		this.fragType = type;
		this.index = ModItems.FRAGS.size();
		System.out.println(this.index);
		ModItems.FRAGS.add(type);
		this.meta = this.index + 1;
		System.out.println(this.meta);
		this.maxDrop = max;
		this.minDrop = min;
	}
	
	 
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getByNameOrId("fantasy:" + fragType);
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		return rand.nextInt(this.maxDrop) + this.minDrop;
	}
	
	

	

}
