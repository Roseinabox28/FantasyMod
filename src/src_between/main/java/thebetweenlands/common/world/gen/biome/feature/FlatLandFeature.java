package thebetweenlands.common.world.gen.biome.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import thebetweenlands.common.world.gen.ChunkGeneratorBetweenlands;
import thebetweenlands.common.world.gen.biome.BiomeWeights;
import thebetweenlands.common.world.gen.biome.generator.BiomeGenerator.EnumGeneratorPass;

/**
 * Takes all base terrain below the water height, moves it above the water and
 * creates small rivers
 */
public class FlatLandFeature extends BiomeFeature {
	private final int waterHeight;
	private final int terrainOffset;

	private NoiseGeneratorPerlin landNoiseGen;
	private double[] landNoise = new double[256];

	private NoiseGeneratorPerlin riverNoiseGen;
	private double[] riverNoise = new double[256];

	public FlatLandFeature(int waterHeight, int terrainOffset) {
		this.waterHeight = waterHeight;
		this.terrainOffset = terrainOffset;
	}

	@Override
	public void initializeGenerators(long seed, Biome biome) {
		Random rng = new Random(seed);
		this.landNoiseGen = new NoiseGeneratorPerlin(rng, 4);
		this.riverNoiseGen = new NoiseGeneratorPerlin(rng, 2);
	}

	@Override
	public void generateNoise(int chunkX, int chunkZ, Biome biome) {
		this.landNoise = this.landNoiseGen.getRegion(this.landNoise, (double) (chunkX * 16), (double) (chunkZ * 16), 16,
				16, 0.06D, 0.06D, 1.0D);
		this.riverNoise = this.riverNoiseGen.getRegion(this.riverNoise, (double) (chunkX * 16), (double) (chunkZ * 16),
				16, 16, 0.032D, 0.032D, 1.0D);
	}

	@Override
	public void replaceStackBlocks(int x, int z, double baseBlockNoise, ChunkPrimer chunkPrimer,
			ChunkGeneratorBetweenlands chunkGenerator, Biome[] biomesForGeneration, Biome biome,
			BiomeWeights biomeWeights, EnumGeneratorPass pass) {
		if (pass == EnumGeneratorPass.PRE_REPLACE_BIOME_BLOCKS) {
			float biomeWeight = biomeWeights.get(x, z, 0, 10);
			// Flatten terrain
			int lowestBlock = 0;
			for (int yOff = 0; yOff < this.waterHeight; yOff++) {
				int y = this.waterHeight - yOff;
				Block currentBlock = chunkPrimer.getBlockState(x, y, z).getBlock();
				if (currentBlock != chunkGenerator.layerBlock && currentBlock != Blocks.AIR) {
					lowestBlock = y;
					break;
				}
			}
			double noise = this.landNoise[x * 16 + z] / 18.0f;
			double riverNoise = Math.abs(this.riverNoise[x * 16 + z]) * 4.0D;
			riverNoise *= riverNoise * riverNoise * riverNoise * riverNoise;
			riverNoise *= 25.0D;
			int terrainHeight = (int) Math
					.ceil(Math.abs(noise * (this.waterHeight - lowestBlock + this.terrainOffset)));
			float riverThreshold = 6.0f * (terrainHeight + 2);
			double riverPercentage = 1.0D - (riverNoise / riverThreshold);
			float weight = (Math.min(biomeWeight + 0.5F, 1.0F) - 0.5F) * 2.0F;
			if (riverNoise < riverThreshold) {
				for (int y = lowestBlock; y < lerp(
						(this.waterHeight + terrainHeight
								- riverPercentage * (terrainHeight + ((riverThreshold - riverNoise) / 16.0D))),
						lowestBlock, weight); y++) {
					chunkPrimer.setBlockState(x, y, z, chunkGenerator.baseBlockState);
				}
			} else {
				for (int y = lowestBlock; y < lerp((this.waterHeight + terrainHeight), lowestBlock, weight); y++) {
					chunkPrimer.setBlockState(x, y, z, chunkGenerator.baseBlockState);
				}
			}
		}
	}
}
