package roseinabox.fantasymod.world.biome;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.world.dimension.wasteland.DimensionWasteland;

public class BiomeWasteland extends BiomeFantasy {

	public BiomeWasteland() {
		super(new BiomeProperties("Wasteland").setBaseHeight(DimensionWasteland.LAYER_HEIGHT - 2).setHeightVariation(0F)
				.setTemperature(2.0F).setRainfall(0.0F).setRainDisabled().setWaterColor(13629951));
		this.setWeight(15);
		this.getBiomeGenerator().setTopBlockState(ModBlocks.CORRUPT_GRASS.getDefaultState()).setFillerBlockHeight(1);
		//.setDecorator(new BiomeDecoratorSludgePlains(this))
		
		this.setFoliageColors(0x5B3522, 0xD36423);
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
		BiomeDictionary.addTypes(this, Type.DRY, Type.DEAD, Type.MAGICAL);
	}

}
