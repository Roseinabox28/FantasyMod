package roseinabox.fantasymod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AlterBase extends BlockBase
{

	public AlterBase(String name, Material material) 
	{
		super(name, material);
		setSoundType(SoundType.SAND); //when walking over block
		setHardness(3.0F); //how hard to break
		setResistance(45.0F); //explosions
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0F); //does not emit light
		setLightOpacity(0); //windows = 1
		//setBlockUnbreakable();
		
	}

}
