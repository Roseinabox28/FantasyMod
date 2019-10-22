package roseinabox.fantasymod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.init.ModItems;

public class BlockFluid extends BlockFluidClassic {

	public BlockFluid(String name, Fluid fluid, Material material) {
		super(fluid, material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		// TODO Auto-generated constructor stub
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

}
