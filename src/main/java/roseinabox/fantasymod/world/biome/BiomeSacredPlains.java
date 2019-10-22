package roseinabox.fantasymod.world.biome;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.util.ModInfo;
import roseinabox.fantasymod.world.dimension.wasteland.DimensionWasteland;

public class BiomeSacredPlains extends BiomeFantasy 
{
	//protected static final WorldAbstractTree TREE = new WorldGenCorrruptTree();
	
	public BiomeSacredPlains() {
		super( new BiomeProperties("Sacred Plains").setBaseHeight(DimensionWasteland.LAYER_HEIGHT - 5).setHeightVariation(3F)
				.setTemperature(2.0F).setRainfall(0.0F).setRainDisabled().setWaterColor(6422666));
		// TODO Auto-generated constructor stub
		this.setWeight(25);
		this.getBiomeGenerator().setTopBlockState(ModBlocks.CORRUPT_GRASS.getDefaultState()).setFillerBlockHeight(1);
				//.setDecorator(new BiomeDecoratorSludgePlains(this))
				
		this.setFoliageColors(0x5B3522, 0xD36423);
		
		//this.decorator.coalGen = new WorldGenMinable(ModBlocks.ELDRITCH_STONE.getDefaultState(), 10);
		
		
		this.decorator.treesPerChunk = 0;
		this.getBiomeGenerator();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWitherSkeleton.class, 10, 1, 5));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 5));
	}
	
	@Override
	public void addTypes() {
		BiomeDictionary.addTypes(this, Type.PLAINS, Type.DRY, Type.DEAD, Type.MAGICAL);
	}
	
}
