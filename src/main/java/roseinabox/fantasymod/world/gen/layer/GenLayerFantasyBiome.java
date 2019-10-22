package roseinabox.fantasymod.world.gen.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import roseinabox.fantasymod.init.BiomeInit;
import roseinabox.fantasymod.registries.BiomeRegistry;
import roseinabox.fantasymod.world.biome.BiomeFantasy;

public class GenLayerFantasyBiome extends GenLayerFantasy {
	private final WeightedList<BiomeFantasy> biomesToGenerate = new WeightedList<BiomeFantasy>();
	private final int totalWeight;

	public GenLayerFantasyBiome(InstancedIntCache cache, long seed) {
		super(cache, seed);
		for(BiomeFantasy biome : BiomeInit.REGISTERED_BIOMES) {
			System.out.println("Biome " + biome.getBiomeName() + " grabbing weight");
			System.out.println("Weight of " + biome.getBiomeName() + ": " + biome.getWeight());
			if(biome.getWeight() > 0) {
				System.out.println("Weight of " + biome.getBiomeName() + ": " + biome.getWeight()+ "... adding");
				this.biomesToGenerate.add(biome);
			}
		}
		System.out.println("TotalWeight = " + this.biomesToGenerate.getTotalWeight());
		this.totalWeight = this.biomesToGenerate.getTotalWeight();
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ) {
		int[] ints = this.cache.getIntCache(sizeX * sizeZ);

		for (int zz = 0; zz < sizeZ; ++zz) {
			for (int xx = 0; xx < sizeX; ++xx) {
				this.initChunkSeed(xx + x, zz + z);
				//System.out.println("Total weight = " + this.totalWeight);
				if(this.totalWeight != 0) {
				ints[xx + zz * sizeX] = Biome.getIdForBiome(this.biomesToGenerate.getRandomItem(this.nextInt(this.totalWeight)));
				}
				else {
				ints[xx + zz * sizeX] = Biome.getIdForBiome(this.biomesToGenerate.getRandomItem(this.nextInt(5)));
				}
			
			}
				
		}

		return ints;
	}
}
