package roseinabox.fantasymod.world.gen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextEnd;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event;
import roseinabox.fantasymod.init.BiomeInit;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.world.biome.BiomeFantasy;
import roseinabox.fantasymod.world.gen.biome.BiomeWeights;
import roseinabox.fantasymod.world.gen.biome.generator.BiomeGenerator;
import roseinabox.fantasymod.world.gen.biome.generator.BiomeGenerator.EnumGeneratorPass;

public class ChunkGeneratorWasteland implements IChunkGenerator {

	//Block that replaces End Stone
		public final Block baseBlock;
	
		/**
		 * Layer block generated below layerHeight. Vanilla: water
		 */
		public final Block layerBlock;
	
		public final IBlockState baseBlockState;
		public final IBlockState layerBlockState;
	    
	    //List of all the mobs you want to spawn
	    protected List<SpawnListEntry> spawnableCreatureList = Lists.<SpawnListEntry>newArrayList();
		
	    //Keep all these the same
	    private final Random rand;
	    private final World world;
	    //private final boolean mapFeaturesEnabled;
	    //private final BlockPos spawnPoint;
	    private Biome[] biomesForGeneration;
	   
	    private final WorldType terrainType;
	    private final double[] heightMap;
	    private final float[] biomeWeights;
	    private int chunkX = 0, chunkZ = 0;
	    
	    private final int layerHeight;
	    
	    private NoiseGeneratorOctaves minLimitPerlinNoise;
	    private NoiseGeneratorOctaves maxLimitPerlinNoise;
	    private NoiseGeneratorOctaves mainPerlinNoise;
	    private NoiseGeneratorPerlin surfaceNoise;
	    public NoiseGeneratorOctaves scaleNoise;
	    public NoiseGeneratorOctaves depthNoise;
	    public NoiseGeneratorOctaves forestNoise;
	    private NoiseGeneratorOctaves lperlinNoise1, lperlinNoise2, perlinNoise1, noiseGen5, noiseGen6;

	    private NoiseGeneratorSimplex treeNoise;
		private NoiseGeneratorSimplex speleothemDensityNoise;
		private double[] surfaceNoiseBuffer = new double[256];
		private float[] terrainBiomeWeights = new float[25];
		private float[] interpolatedTerrainBiomeWeights = new float[256];
	    private double[] buffer;
	    private double[] mainNoiseRegion;
		private double[] minLimitRegion;
		private double[] maxLimitRegion;
		private double[] depthRegion;
	    double[] pnr, ar, br;
	    private long seed;
	    
	    //Put map features here
	    //e.g. private MapGenEndCity endCityGen = new MapGenEndCity(this);
	    
	    public ChunkGeneratorWasteland(World world, long seed, Block baseBlock, Block layerBlock, int layerHeight)
	    {
	        this.world = world;
	        this.baseBlock = baseBlock;
			this.baseBlockState = baseBlock.getDefaultState();
	        this.layerBlock = layerBlock;
			this.layerBlockState = layerBlock.getDefaultState();
	        this.layerHeight = layerHeight;
	        
	        //spawnableCreatureList.add(new SpawnListEntry(EntityCentaur.class, 10, 1, 5));
	        spawnableCreatureList.add(new SpawnListEntry(EntityWitherSkeleton.class, 50, 1, 5));
	        
	        this.terrainType = world.getWorldInfo().getTerrainType();
	        this.rand = new Random(seed);
	        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
	        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
	        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
	        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
	        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
	        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
	        this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
	        this.heightMap = new double[825];
	        this.biomeWeights = new float[25];
	        
	        for (int i = -2; i <= 2; ++i)
	        {
	            for (int j = -2; j <= 2; ++j)
	            {
	                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
	                this.biomeWeights[i + 2 + (j + 2) * 5] = f;
	            }
	        }
	        
	        this.treeNoise = new NoiseGeneratorSimplex(this.rand);
			this.speleothemDensityNoise = new NoiseGeneratorSimplex(this.rand);
	        
	        
	        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
			this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
			this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
			
			ContextFantasy ctx = new ContextFantasy(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise,
					surfaceNoise, scaleNoise, depthNoise, treeNoise, speleothemDensityNoise);
	        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(world, this.rand, ctx);
	        this.minLimitPerlinNoise = ctx.getLPerlin1();
	        this.maxLimitPerlinNoise = ctx.getLPerlin2();
	        this.mainPerlinNoise = ctx.getPerlin();
	        this.minLimitPerlinNoise = ctx.getLPerlin1();
			this.maxLimitPerlinNoise = ctx.getLPerlin2();
			this.mainPerlinNoise = ctx.getPerlin();
			this.surfaceNoise = ctx.getSurfaceNoise();
			this.scaleNoise = ctx.getScale();
			this.depthNoise = ctx.getDepth();
			this.treeNoise = ctx.getTreeNoise();
			this.speleothemDensityNoise = ctx.getSpeleothemDensityNoise();
			world.setSeaLevel(layerHeight);
			
			
	        //TODO Set the map features to be something
	        //e.g. this.endCityGen = (MapGenEndCity) TerrainGen.getModdedMapGen(this.endCityGen,InitMapGenEvent.EventType.END_CITY);

	    }

		@Override
		public Chunk generateChunk(int chunkX, int chunkZ) {
			this.rand.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
			

			ChunkPrimer chunkprimer = new ChunkPrimer();

			this.setBlocksInChunk(chunkX, chunkZ, chunkprimer);

			// Interpolate biome weights
			for (int z = 0; z < 16; z++) {
				for (int x = 0; x < 16; x++) {
					float fractionZ = (z % 4) / 4.0F;
					float fractionX = (x % 4) / 4.0F;
					int biomeWeightZ = z / 4;
					int biomeWeightX = x / 4;

					float weightXCZC = this.terrainBiomeWeights[biomeWeightX + biomeWeightZ * 5];
					float weightXNZC = this.terrainBiomeWeights[biomeWeightX + 1 + biomeWeightZ * 5];
					float weightXCZN = this.terrainBiomeWeights[biomeWeightX + (biomeWeightZ + 1) * 5];
					float weightXNZN = this.terrainBiomeWeights[biomeWeightX + 1 + (biomeWeightZ + 1) * 5];

					float interpZAxisXC = weightXCZC + (weightXCZN - weightXCZC) * fractionZ;
					float interpZAxisXN = weightXNZC + (weightXNZN - weightXNZC) * fractionZ;
					float currentVal = interpZAxisXC + (interpZAxisXN - interpZAxisXC) * fractionX;

					this.interpolatedTerrainBiomeWeights[x + z * 16] = currentVal;
				}
			}
			
			BiomeWeights biomeWeights = new BiomeWeights(this.interpolatedTerrainBiomeWeights);

			this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16,
					chunkZ * 16, 16, 16);

			this.replaceBiomeBlocks(chunkX, chunkZ, chunkprimer, this.biomesForGeneration, biomeWeights);
			
			
			// Add biome features (post cave)
			for (int z = 0; z < 16; z++) {
				for (int x = 0; x < 16; x++) {
					double baseBlockNoise = this.surfaceNoiseBuffer[z + x * 16];
					Biome biome = this.biomesForGeneration[z + x * 16];
					if (biome instanceof BiomeFantasy) {
						BiomeGenerator generator = ((BiomeFantasy) biome).getBiomeGenerator();
						generator.runBiomeFeatures(chunkZ * 16 + z, chunkX * 16 + x, z, x, baseBlockNoise, chunkprimer,
								this, this.biomesForGeneration, biomeWeights, EnumGeneratorPass.POST_GEN_CAVES);
					}
				}
			}
			Chunk chunk = new Chunk(this.world, chunkprimer, chunkX, chunkZ);
			byte[] biomeArray = chunk.getBiomeArray();

			for (int i = 0; i < biomeArray.length; ++i) {
				biomeArray[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
			}

			chunk.generateSkylightMap();
			return chunk;
			
			
		}
		
		public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
			this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration,
					chunkX * 4 - 5, chunkZ * 4 - 5, 15, 15);

			this.generateHeightmap(chunkX * 4, 0, chunkZ * 4);

			// X
			for (int heightMapX = 0; heightMapX < 4; ++heightMapX) {
				int indexXC = heightMapX * 5; // 1
				int indexXN = (heightMapX + 1) * 5; // 2

				/*
				 * 1 2
				 */

				// Z
				for (int heightMapZ = 0; heightMapZ < 4; ++heightMapZ) {
					int indexXCZC = (indexXC + heightMapZ) * 33; // 1
					int indexXCZN = (indexXC + heightMapZ + 1) * 33; // 2
					int indexXNZC = (indexXN + heightMapZ) * 33; // 3
					int indexXNZN = (indexXN + heightMapZ + 1) * 33; // 4

					/*
					 * 1 3 2 4
					 */

					// Y
					for (int heightMapY = 0; heightMapY < 32; ++heightMapY) {
						// Values
						double valXCZCYC = this.heightMap[indexXCZC + heightMapY]; // 1
						double valXCZNYC = this.heightMap[indexXCZN + heightMapY]; // 2
						double valXNZCYC = this.heightMap[indexXNZC + heightMapY]; // 3
						double valXNZNYC = this.heightMap[indexXNZN + heightMapY]; // 4
						double valXCZCYN = this.heightMap[indexXCZC + heightMapY + 1]; // 5
						double valXCZNYN = this.heightMap[indexXCZN + heightMapY + 1]; // 6
						double valXNZCYN = this.heightMap[indexXNZC + heightMapY + 1]; // 7
						double valXNZNYN = this.heightMap[indexXNZN + heightMapY + 1]; // 8

						// Step along Y axis (1/8 of the difference)
						double stepYAxisXCZC = (valXCZCYN - valXCZCYC) * 0.125D;
						double stepYAxisXCZN = (valXCZNYN - valXCZNYC) * 0.125D;
						double stepYAxisXNZC = (valXNZCYN - valXNZCYC) * 0.125D;
						double stepYAxisXNZN = (valXNZNYN - valXNZNYC) * 0.125D;

						double currentValXCZCYC = valXCZCYC;
						double currentValXCZNYC = valXCZNYC;
						double currentValXNZCYC = valXNZCYC;
						double currentValXNZNYC = valXNZNYC;

						/*
						 * At this point we have the values of a 2x2x2 cube and their difference along
						 * the Y axis (e.g. 5 - 1, 6 - 2, 7 - 3, 8 - 4)
						 * 
						 * Y: 1 3 2 4
						 * 
						 * Y+1: 5 7 6 8
						 */

						// Now it expands that cube into a 8x4x4 area and linearly interpolates the
						// values

						// Step Y axis
						for (int blockY = 0; blockY < 8; ++blockY) {
							double currentValXCZC = currentValXCZCYC;
							double currentValXCZN = currentValXCZNYC;

							// Step along X axis
							double stepXAxisZC = (currentValXNZCYC - currentValXCZCYC) * 0.25D;
							double stepXAxisZN = (currentValXNZNYC - currentValXCZNYC) * 0.25D;

							// Step X axis
							for (int blockX = 0; blockX < 4; ++blockX) {
								// Step along Z axis
								double stepZAxis = (currentValXCZN - currentValXCZC) * 0.25D;

								double currentValZC = currentValXCZC - stepZAxis;

								// Step Z axis
								for (int blockZ = 0; blockZ < 4; ++blockZ) {
									if ((currentValZC += stepZAxis) > 0.0D) {
										primer.setBlockState(heightMapX * 4 + blockX, heightMapY * 8 + blockY,
												heightMapZ * 4 + blockZ, this.baseBlockState);
									} else if (heightMapY * 8 + blockY <= this.layerHeight) {
										primer.setBlockState(heightMapX * 4 + blockX, heightMapY * 8 + blockY,
												heightMapZ * 4 + blockZ, this.layerBlockState);
									}
								}

								currentValXCZC += stepXAxisZC;
								currentValXCZN += stepXAxisZN;
							}

							currentValXCZCYC += stepYAxisXCZC;
							currentValXCZNYC += stepYAxisXCZN;
							currentValXNZCYC += stepYAxisXNZC;
							currentValXNZNYC += stepYAxisXNZN;
						}
					}
				}
			}
		}
		/**
		 * Generates a 33x5x5 (Y*X*Z) heightmap
		 * 
		 * @param x
		 * @param y
		 * @param z
		 */
		private void generateHeightmap(int x, int y, int z) {
			this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
			float scaleXZ = 684.412F * 8;
			float scaleY = 684.412F * 8;
			this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, x, y, z, 5, 33, 5,
					(double) (scaleXZ / 80.0F), (double) (scaleY / 160.0F), (double) (scaleXZ / 80.0F));
			this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, x, y, z, 5, 33, 5,
					(double) scaleXZ, (double) scaleY, (double) scaleXZ);
			this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, x, y, z, 5, 33, 5,
					(double) scaleXZ, (double) scaleY, (double) scaleXZ);

			int noiseIndex = 0;
			int heightMapIndex = 0;

			for (int heightMapX = 0; heightMapX < 5; ++heightMapX) {
				for (int heightMapZ = 0; heightMapZ < 5; ++heightMapZ) {
					float biomeVariation = 0.0F;
					float biomeDepth = 0.0F;
					float totalBiomeWeight = 0.0F;
					Biome centerBiome = this.biomesForGeneration[heightMapX + 5 + (heightMapZ + 5) * 15];

					float nearestOtherBiomeSq = 50;

					// Averages biome height and variation in a 5x5 area and calculates the biome
					// terrain weight from an 11x11 area
					for (int offsetX = -5; offsetX <= 5; ++offsetX) {
						for (int offsetZ = -5; offsetZ <= 5; ++offsetZ) {
							Biome nearbyBiome = this.biomesForGeneration[heightMapX + 5 + offsetX
									+ (heightMapZ + 5 + offsetZ) * 15];
							float nearbyBiomeDepth = nearbyBiome.getBaseHeight();
							float nearbyBiomeVariation = nearbyBiome.getHeightVariation();

							// No amplified terrain
							/*
							 * if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F) { f5 = 1.0F + f5 *
							 * 2.0F; f6 = 1.0F + f6 * 4.0F; }
							 */

							if (offsetX >= -2 && offsetX <= 2 && offsetZ >= -2 && offsetZ <= 2) {
								float weight = this.biomeWeights[offsetX + 2 + (offsetZ + 2) * 5];

								if (nearbyBiome.getBaseHeight() > centerBiome.getBaseHeight()) {
									weight /= 2.0F;
								}

								biomeVariation += nearbyBiomeVariation * weight;
								biomeDepth += nearbyBiomeDepth * weight;
								totalBiomeWeight += weight;
							}

							float distWeighted = (offsetX * offsetX + offsetZ * offsetZ);
							if (nearbyBiome != centerBiome && distWeighted < nearestOtherBiomeSq) {
								nearestOtherBiomeSq = distWeighted;
							}
						}
					}

					// The 0 point is offset by some blocks so that the lerp doesn't cause problems
					// later on
					this.terrainBiomeWeights[heightMapIndex] = MathHelper
							.clamp(Math.max((nearestOtherBiomeSq - 2) / 46.0F, 0.0F), 0.0F, 1.0F);

					biomeVariation = biomeVariation / totalBiomeWeight;
					biomeDepth = biomeDepth / totalBiomeWeight;

					// Small offset for biome depth?
					double depthPerturbation = this.depthRegion[heightMapIndex] / 8000.0D;

					// depthPerturbation = 0.0D;

					if (depthPerturbation < 0.0D) {
						depthPerturbation = -depthPerturbation * 0.3D;
					}

					depthPerturbation = depthPerturbation * 3.0D - 2.0D;

					if (depthPerturbation < 0.0D) {
						depthPerturbation = depthPerturbation / 2.0D;

						if (depthPerturbation < -1.0D) {
							depthPerturbation = -1.0D;
						}

						depthPerturbation = depthPerturbation / 1.4D;
						depthPerturbation = depthPerturbation / 2.0D;
					} else {
						if (depthPerturbation > 1.0D) {
							depthPerturbation = 1.0D;
						}

						depthPerturbation = depthPerturbation / 8.0D;
					}

					++heightMapIndex;

					// double depth = (biomeDepth * this.layerHeight) / 256.0D;

					for (int heightMapY = 0; heightMapY < 33; ++heightMapY) {
						double densityOffset = ((double) heightMapY * 8.0D - biomeDepth
								- (depthPerturbation * biomeVariation / 256.0D)) / 256.0D;

						double maxGenDensity16 = 32767.0D;
						/*
						 * double maxGenDensity16 = 0.0D; for(int i = 0; i < 16 - 1; i++) {
						 * maxGenDensity16 += 2 * Math.pow(2.0D, i); } maxGenDensity16 /= 2.0D;
						 */

						double maxGenDensity8 = 127.0;
						/*
						 * double maxGenDensity8 = 0.0D; for(int i = 0; i < 8 - 1; i++) { maxGenDensity8
						 * += 2 * Math.pow(2.0D, i); } maxGenDensity8 /= 2.0D;
						 */

						double minDensity = (this.minLimitRegion[noiseIndex] / maxGenDensity16) * biomeVariation / 256.0D;
						double maxDensity = (this.maxLimitRegion[noiseIndex] / maxGenDensity16) * biomeVariation / 256.0D;
						double mainDensity = (this.mainNoiseRegion[noiseIndex] / maxGenDensity8);

						// TODO Not sure if clampedlerp is the right thing to use
						this.heightMap[noiseIndex] = MathHelper.clampedLerp(minDensity, maxDensity, mainDensity)
								- densityOffset;

						++noiseIndex;
					}
				}
			}
		}
		/**
		 * Modifies the terrain with biome specific features
		 * 
		 * @param chunkX
		 * @param chunkZ
		 * @param primer
		 * @param biomesIn
		 */
		public void replaceBiomeBlocks(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomesIn,
				BiomeWeights biomeWeights) {
			if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer,
					this.world))
				return;

			this.surfaceNoiseBuffer = this.surfaceNoise.getRegion(this.surfaceNoiseBuffer, (double) (chunkX * 16),
					(double) (chunkZ * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

			List<BiomeGenerator> foundGenerators = new ArrayList<BiomeGenerator>();

			for (int z = 0; z < 16; z++) {
				for (int x = 0; x < 16; x++) {
					double baseBlockNoise = this.surfaceNoiseBuffer[z + x * 16];
					Biome biome = biomesIn[z + x * 16];
					if (biome instanceof BiomeFantasy) {
						BiomeGenerator generator = ((BiomeFantasy) biome).getBiomeGenerator();
						generator.initializeGenerators(this.seed);
						generator.generateNoise(chunkZ, chunkX);
						foundGenerators.add(generator);
						generator.runBiomeFeatures(chunkZ * 16 + z, chunkX * 16 + x, z, x, baseBlockNoise, primer, this,
								biomesIn, biomeWeights, EnumGeneratorPass.PRE_REPLACE_BIOME_BLOCKS);
						generator.replaceBiomeBlocks(chunkZ * 16 + z, chunkX * 16 + x, z, x, baseBlockNoise, this.rand,
								this.seed, primer, this, biomesIn, biomeWeights);
						generator.runBiomeFeatures(chunkZ * 16 + z, chunkX * 16 + x, z, x, baseBlockNoise, primer, this,
								biomesIn, biomeWeights, EnumGeneratorPass.POST_REPLACE_BIOME_BLOCKS);
					} else {
						biome.genTerrainBlocks(this.world, this.rand, primer, chunkX * 16 + x, chunkZ * 16 + z,
								baseBlockNoise);
					}
				}
			}

			for (BiomeGenerator gen : foundGenerators) {
				gen.resetNoise();
			}
		}
		
		



		@Override
		public void populate(int x, int z) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean generateStructures(Chunk chunkIn, int x, int z) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
			return ImmutableList.of(); // Spawning is handled by MobSpawnHandler
		}

		@Nullable
		@Override
		public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
				boolean findUnexplored) {
			return null;
		}

		@Override
		public void recreateStructures(Chunk chunkIn, int x, int z) {
			// No structures
		}

		@Override
		public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
			return false;
		}
		
		public double evalTreeNoise(double x, double z) {
			return this.treeNoise.getValue(x, z);
		}

		public double evalSpeleothemDensityNoise(double x, double z) {
			return this.speleothemDensityNoise.getValue(x, z);
		}
		
		private static class ChunkProvide {
			int x, z;

			boolean player;

			int provides;

			String[] cause;

			int[] signature;

			public ChunkProvide(int x, int z, boolean player, int provides, String[] cause, int[] signature) {
				this.x = x;
				this.z = z;
				this.player = player;
				this.provides = provides;
				this.cause = cause;
				this.signature = signature;
			}

			@Override
			public int hashCode() {
				return x * 31 + z;
			}

			@Override
			public boolean equals(Object obj) {
				return obj instanceof ChunkProvide && ((ChunkProvide) obj).x == x && ((ChunkProvide) obj).z == z;
			}
			
		}
		
		private Set<ChunkProvide> chunkProvides = Collections.synchronizedSet(new LinkedHashSet<>());

		private int maxSigLen, maxProvides;

	   

}
