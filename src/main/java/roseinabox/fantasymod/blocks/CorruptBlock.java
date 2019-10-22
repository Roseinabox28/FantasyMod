package roseinabox.fantasymod.blocks;

import roseinabox.fantasymod.blocks.*;
import roseinabox.fantasymod.init.ModBlocks;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.tools.nsc.backend.icode.BasicBlocks;
import scala.tools.nsc.backend.icode.BasicBlocks.BasicBlock;
import scala.tools.nsc.backend.icode.Members.IMethod;

public class CorruptBlock extends BlockBase{

	
	private final String corruptType;

	public CorruptBlock(String name, Material material, String type) {
		super(name, material);
		corruptType = type;
		setSoundType(SoundType.GROUND); //when walking over block
		setHardness(0.5F); //how hard to break
		setResistance(3.0F); //explosions
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.5F); //emits light
		setTickRandomly(true);
		//setBlockUnbreakable();
		System.out.println("INIT");
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand, String type)
    {
		if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2)
            {
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
            }
            else
            {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 0)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                        if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
                        {
                            return;
                        }

                        IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                        if (iblockstate1.getBlock() == Blocks.DIRT && iblockstate1.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2)
                        {
                            worldIn.setBlockState(blockpos, ModBlocks.CORRUPT_GRASS.getDefaultState());
                        }
                    }
                }
            }
        }
    }
}
