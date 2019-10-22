package roseinabox.fantasymod.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import roseinabox.fantasymod.init.BiomeInit;

public abstract class GenLayerFantasy extends GenLayer {
	protected final InstancedIntCache cache;

	public GenLayerFantasy(InstancedIntCache cache, long seed) {
		super(seed);
		this.cache = cache;
	}

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType) {
		int biomeSize = getModdedBiomeSize(worldType, (worldType == WorldType.LARGE_BIOMES ? 6 : 4));

		final InstancedIntCache cache = new InstancedIntCache();

		GenLayer genLayer = new GenLayerFantasyBiome(cache, 100L);

		genLayer = GenLayerZoomIncrement.magnify(cache, 2000L, genLayer, false, 2);

		GenLayer sacredPlainsClearingLayer = new GenLayerSurrounded(cache, 102L, genLayer, BiomeInit.SACRED_PLAINS, BiomeInit.SACRED_PLAINS_CLEARING, 1, 1);
		sacredPlainsClearingLayer = new GenLayerMask(cache, sacredPlainsClearingLayer, Biome.getIdForBiome(BiomeInit.SACRED_PLAINS), Biome.getIdForBiome(BiomeInit.SACRED_PLAINS_CLEARING));
		sacredPlainsClearingLayer = GenLayerThinMask.thin(cache, 105L, sacredPlainsClearingLayer, Biome.getIdForBiome(BiomeInit.SACRED_PLAINS_CLEARING), 3, 0.25f, 10);

		genLayer = GenLayerZoomIncrement.magnify(cache, 2345L, genLayer, false, biomeSize);
		sacredPlainsClearingLayer = GenLayerZoomIncrement.magnify(cache, 2345L, sacredPlainsClearingLayer, true, biomeSize);

		sacredPlainsClearingLayer = new GenLayerCircleMask(cache, 103L, sacredPlainsClearingLayer, Biome.getIdForBiome(BiomeInit.SACRED_PLAINS_CLEARING), 10);

		genLayer = new GenLayerMixMask(cache, genLayer, sacredPlainsClearingLayer);

		GenLayer indexLayer = new GenLayerResetCache(cache, new GenLayerVoronoiZoomInstanced(cache, 10L, genLayer));
		genLayer = new GenLayerResetCache(cache, genLayer);

		indexLayer.initWorldGenSeed(seed);
		genLayer.initWorldGenSeed(seed);

		return new GenLayer[]{genLayer, indexLayer, genLayer};
	}
}