package roseinabox.fantasymod.world.biome;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.world.dimension.wasteland.DimensionWasteland;

public class BiomeSacredPlainsClearing extends BiomeFantasy {
	//protected static final WorldAbstractTree TREE = new WorldGenCorrruptTree();
	
		public BiomeSacredPlainsClearing() {
			super( new BiomeProperties("Sacred Plains Clearing").setBaseHeight(DimensionWasteland.LAYER_HEIGHT + 4).setHeightVariation(0F)
					.setTemperature(2.0F).setRainfall(0.0F).setRainDisabled().setWaterColor(6422666));
			
			
			this.setWeight(0);
			this.getBiomeGenerator().setTopBlockState(ModBlocks.CORRUPT_GRASS.getDefaultState()).setFillerBlockHeight(1);
					//.setDecorator(new BiomeDecoratorSacredPlainsClearing(this));
			
			
			topBlock = ModBlocks.CORRUPT_GRASS.getDefaultState();
			fillerBlock = ModBlocks.GENERIC_STONE.getDefaultState();
			
			this.decorator.coalGen = new WorldGenMinable(ModBlocks.ELDRITCH_STONE.getDefaultState(), 10);
			
			this.setFoliageColors(-1, 0xA8A800);
			this.setSecondaryFoliageColors(-1, 0xa87800);
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
