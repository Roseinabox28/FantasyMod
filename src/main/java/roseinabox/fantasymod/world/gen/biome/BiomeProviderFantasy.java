package roseinabox.fantasymod.world.gen.biome;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.storage.WorldInfo;
import roseinabox.fantasymod.init.BiomeInit;
import roseinabox.fantasymod.world.dimension.wasteland.DimensionWasteland;
import roseinabox.fantasymod.world.gen.layer.GenLayerFantasy;

public class BiomeProviderFantasy extends BiomeProvider {
	public static final List<Biome> ALLOWED_SPAWN_BIOMES = Lists
			.newArrayList(BiomeInit.SACRED_PLAINS);

	protected final DimensionWasteland provider;

	public BiomeProviderFantasy(DimensionWasteland provider, WorldInfo worldInfo) {
		super(worldInfo);
		this.provider = provider;
	}

	@Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
		System.out.println("About to call GenLayerFantasy... pray");
		try {
		original = GenLayerFantasy.initializeAllBiomeGenerators(seed, worldType);
		return super.getModdedBiomeGenerators(worldType, seed, original);
		}
		catch(java.lang.ArithmeticException e) {
			return null;
		}
	}

	@Override
	public List<Biome> getBiomesToSpawnIn() {
		return ALLOWED_SPAWN_BIOMES;
	}

	@Override
	public float getTemperatureAtHeight(float biomeTemp, int y) {
		return biomeTemp;
	}
}
