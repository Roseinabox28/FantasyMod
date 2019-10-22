package roseinabox.fantasymod.world.biome;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.Biome.FlowerEntry;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseBiome extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<Biome>{

	public static Biome biome = toBiome(null);
	protected BiomeDecorator decorator;
	
	private static final Logger LOGGER = LogManager.getLogger();
    protected static final IBlockState STONE = Blocks.STONE.getDefaultState();
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
    protected static final IBlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
    protected static final IBlockState RED_SANDSTONE = Blocks.RED_SANDSTONE.getDefaultState();
    protected static final IBlockState SANDSTONE = Blocks.SANDSTONE.getDefaultState();
    protected static final IBlockState ICE = Blocks.ICE.getDefaultState();
    protected static final IBlockState WATER = Blocks.WATER.getDefaultState();
    public static final ObjectIntIdentityMap<Biome> MUTATION_TO_BASE_ID_MAP = new ObjectIntIdentityMap<Biome>();
    protected static final NoiseGeneratorPerlin TEMPERATURE_NOISE = new NoiseGeneratorPerlin(new Random(1234L), 1);
    protected static final NoiseGeneratorPerlin GRASS_COLOR_NOISE = new NoiseGeneratorPerlin(new Random(2345L), 1);
    protected static final WorldGenDoublePlant DOUBLE_PLANT_GENERATOR = new WorldGenDoublePlant();
    /** The tree generator. */
    protected static final WorldGenTrees TREE_FEATURE = new WorldGenTrees(false);
    /** The big tree generator. */
    protected static final WorldGenBigTree BIG_TREE_FEATURE = new WorldGenBigTree(false);
    /** The swamp tree generator. */
    protected static final WorldGenSwamp SWAMP_FEATURE = new WorldGenSwamp();
    public static final RegistryNamespaced<ResourceLocation, Biome> REGISTRY = net.minecraftforge.registries.GameData.getWrapper(Biome.class);
    private final String biomeName;
    /** The base height of this biome. Default 0.1. */
    private final float baseHeight;
    /** The variation from the base height of the biome. Default 0.3. */
    private final float heightVariation;
    /** The temperature of this biome. */
    private final float temperature;
    /** The rainfall in this biome. */
    private final float rainfall;
    /** Color tint applied to water depending on biome */
    private final int waterColor;
    /** Set to true if snow is enabled for this biome. */
    private final boolean enableSnow;
    /** Is true (default) if the biome support rain (desert and nether can't have rain) */
    private final boolean enableRain;
    /** The unique identifier of the biome for which this is a mutation of. */
    @Nullable
    private final String baseBiomeRegName;
    /** The block expected to be on the top of this biome */
    public IBlockState topBlock = Blocks.GRASS.getDefaultState();
    /** The block to fill spots in when not on the top */
    public IBlockState fillerBlock = Blocks.DIRT.getDefaultState();
    /** The biome decorator. */

    /** Holds the classes of IMobs (hostile mobs) that can be spawned in the biome. */
    protected List<BaseBiome.SpawnListEntry> spawnableMonsterList = Lists.<BaseBiome.SpawnListEntry>newArrayList();
    /** Holds the classes of any creature that can be spawned in the biome as friendly creature. */
    protected List<BaseBiome.SpawnListEntry> spawnableCreatureList = Lists.<BaseBiome.SpawnListEntry>newArrayList();
    /** Holds the classes of any aquatic creature that can be spawned in the water of the biome. */
    protected List<BaseBiome.SpawnListEntry> spawnableWaterCreatureList = Lists.<BaseBiome.SpawnListEntry>newArrayList();
    protected List<BaseBiome.SpawnListEntry> spawnableCaveCreatureList = Lists.<BaseBiome.SpawnListEntry>newArrayList();
    // Forge: Stores the spawnable lists for non-vanilla EnumCreatureTypes. Can't be an EnumMap as that doesn't handle new enum values being added after it's created.
    protected java.util.Map<EnumCreatureType, List<BaseBiome.SpawnListEntry>> modSpawnableLists = com.google.common.collect.Maps.newHashMap();
	
    public static int getIdForBiome(Biome biome)
    {
        return REGISTRY.getIDForObject(biome);
    }

    private static Biome toBiome(BaseBiome biome) {
    	net.minecraftforge.registries.IForgeRegistryEntry.Impl<Biome> temp = (net.minecraftforge.registries.IForgeRegistryEntry.Impl<Biome>) biome;
    	Biome tempBiome = (Biome) temp;
		return tempBiome;
	}

	@Nullable
    public static Biome getBiomeForId(int id)
    {
        return REGISTRY.getObjectById(id);
    }

    @Nullable
    public static Biome getMutationForBiome(Biome biome)
    {
        return MUTATION_TO_BASE_ID_MAP.getByValue(getIdForBiome(biome));
    }
    
	public BaseBiome(BaseBiome.BiomeProperties properties){
		this.decorator = biome.decorator;
		this.biomeName = properties.biomeName;
        this.baseHeight = properties.baseHeight;
        this.heightVariation = properties.heightVariation;
        this.temperature = properties.temperature;
        this.rainfall = properties.rainfall;
        this.waterColor = properties.waterColor;
        this.enableSnow = properties.enableSnow;
        this.enableRain = properties.enableRain;
        this.baseBiomeRegName = properties.baseBiomeRegName;
        this.decorator = this.createBiomeDecorator();

	}
	 public BiomeDecorator createBiomeDecorator()
	    {
	        return getModdedBiomeDecorator(new BiomeDecorator());
	    }

	    public boolean isMutation()
	    {
	        return this.baseBiomeRegName != null;
	    }

	    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
	        return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
	    }

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
	    public WorldGenerator getRandomWorldGenForGrass(Random rand)
	    {
	        return new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	    }

	    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
	    {
	        return rand.nextInt(3) > 0 ? BlockFlower.EnumFlowerType.DANDELION : BlockFlower.EnumFlowerType.POPPY;
	    }

	    /**
	     * takes temperature, returns color
	     */
	    @SideOnly(Side.CLIENT)
	    public int getSkyColorByTemp(float currentTemperature)
	    {
	        currentTemperature = currentTemperature / 3.0F;
	        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
	        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
	    }

	    /**
	     * Returns the correspondent list of the EnumCreatureType informed.
	     */
	    public List<BaseBiome.SpawnListEntry> getSpawnableList(EnumCreatureType creatureType)
	    {
	        switch (creatureType)
	        {
	            case MONSTER:
	                return this.spawnableMonsterList;
	            case CREATURE:
	                return this.spawnableCreatureList;
	            case WATER_CREATURE:
	                return this.spawnableWaterCreatureList;
	            case AMBIENT:
	                return this.spawnableCaveCreatureList;
	            default:
	                // Forge: Return a non-empty list for non-vanilla EnumCreatureTypes
	                if (!this.modSpawnableLists.containsKey(creatureType)) this.modSpawnableLists.put(creatureType, Lists.<BaseBiome.SpawnListEntry>newArrayList());
	                return this.modSpawnableLists.get(creatureType);
	        }
	    }

	    /**
	     * Returns true if the biome have snowfall instead a normal rain.
	     */
	    public boolean getEnableSnow()
	    {
	        return this.isSnowyBiome();
	    }

	    /**
	     * Check if rain can occur in biome
	     */
	    public boolean canRain()
	    {
	        return this.isSnowyBiome() ? false : this.enableRain;
	    }

	    /**
	     * Checks to see if the rainfall level of the biome is extremely high
	     */
	    public boolean isHighHumidity()
	    {
	        return this.getRainfall() > 0.85F;
	    }

	    /**
	     * returns the chance a creature has to spawn.
	     */
	    public float getSpawningChance()
	    {
	        return 0.1F;
	    }
	    /**
	     * Gets the current temperature at the given location, based off of the default for this biome, the elevation of the
	     * position, and {@linkplain #TEMPERATURE_NOISE} some random perlin noise.
	     */
	    public final float getTemperature(BlockPos pos)
	    {
	        if (pos.getY() > 64)
	        {
	            float f = (float)(TEMPERATURE_NOISE.getValue((double)((float)pos.getX() / 8.0F), (double)((float)pos.getZ() / 8.0F)) * 4.0D);
	            return this.getDefaultTemperature() - (f + (float)pos.getY() - 64.0F) * 0.05F / 30.0F;
	        }
	        else
	        {
	            return this.getDefaultTemperature();
	        }
	    }

	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {
	        this.decorator.decorate(worldIn, rand, biome, pos);
	    }

	    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	    {
	        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	    }

	    @SideOnly(Side.CLIENT)
	    public int getGrassColorAtPos(BlockPos pos)
	    {
	        double d0 = (double)MathHelper.clamp(this.getTemperature(pos), 0.0F, 1.0F);
	        double d1 = (double)MathHelper.clamp(this.getRainfall(), 0.0F, 1.0F);
	        return getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(d0, d1));
	    }

	    /**
	     * Given x, z coordinates, we count down all the y positions starting at 255 and working our way down. When we hit a
	     * non-air block, we replace it with this.topBlock (default grass, descendants may set otherwise), and then a
	     * relatively shallow layer of blocks of type this.fillerBlock (default dirt). A random set of blocks below y == 5
	     * (but always including y == 0) is replaced with bedrock.
	     *  
	     * If we don't hit non-air until somewhat below sea level, we top with gravel and fill down with stone.
	     *  
	     * If this.fillerBlock is red sand, we replace some of that with red sandstone.
	     */
	    public final void generateBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	    {
	        int i = worldIn.getSeaLevel();
	        IBlockState iblockstate = this.topBlock;
	        IBlockState iblockstate1 = this.fillerBlock;
	        int j = -1;
	        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
	        int l = x & 15;
	        int i1 = z & 15;
	        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

	        for (int j1 = 255; j1 >= 0; --j1)
	        {
	            if (j1 <= rand.nextInt(5))
	            {
	                chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
	            }
	            else
	            {
	                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

	                if (iblockstate2.getMaterial() == Material.AIR)
	                {
	                    j = -1;
	                }
	                else if (iblockstate2.getBlock() == Blocks.STONE)
	                {
	                    if (j == -1)
	                    {
	                        if (k <= 0)
	                        {
	                            iblockstate = AIR;
	                            iblockstate1 = STONE;
	                        }
	                        else if (j1 >= i - 4 && j1 <= i + 1)
	                        {
	                            iblockstate = this.topBlock;
	                            iblockstate1 = this.fillerBlock;
	                        }

	                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
	                        {
	                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F)
	                            {
	                                iblockstate = ICE;
	                            }
	                            else
	                            {
	                                iblockstate = WATER;
	                            }
	                        }

	                        j = k;

	                        if (j1 >= i - 1)
	                        {
	                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
	                        }
	                        else if (j1 < i - 7 - k)
	                        {
	                            iblockstate = AIR;
	                            iblockstate1 = STONE;
	                            chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
	                        }
	                        else
	                        {
	                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
	                        }
	                    }
	                    else if (j > 0)
	                    {
	                        --j;
	                        chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

	                        if (j == 0 && iblockstate1.getBlock() == Blocks.SAND && k > 1)
	                        {
	                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
	                            iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
	                        }
	                    }
	                }
	            }
	        }
	    }

	    @SideOnly(Side.CLIENT)
	    public int getFoliageColorAtPos(BlockPos pos)
	    {
	        double d0 = (double)MathHelper.clamp(this.getTemperature(pos), 0.0F, 1.0F);
	        double d1 = (double)MathHelper.clamp(this.getRainfall(), 0.0F, 1.0F);
	        return getModdedBiomeFoliageColor(ColorizerFoliage.getFoliageColor(d0, d1));
	    }

	    public Class <? extends BaseBiome > getBiomeClass()
	    {
	        return this.getClass();
	    }

	    public Biome.TempCategory getTempCategory()
	    {
	        if ((double)this.getDefaultTemperature() < 0.2D)
	        {
	            return Biome.TempCategory.COLD;
	        }
	        else
	        {
	            return (double)this.getDefaultTemperature() < 1.0D ? Biome.TempCategory.MEDIUM : Biome.TempCategory.WARM;
	        }
	    }

	    /**
	     * return the biome specified by biomeID, or 0 (ocean) if out of bounds
	     */
	    @Nullable
	    public static Biome getBiome(int id)
	    {
	        return getBiome(id, (Biome)null);
	    }

	    public static Biome getBiome(int biomeId, Biome fallback)
	    {
	        Biome biome = getBiomeForId(biomeId);
	        return biome == null ? fallback : biome;
	    }

	    public boolean ignorePlayerSpawnSuitability()
	    {
	        return false;
	    }

	    public final float getBaseHeight()
	    {
	        return this.baseHeight;
	    }

	    /**
	     * Gets a floating point representation of this biome's rainfall
	     */
	    public final float getRainfall()
	    {
	        return this.rainfall;
	    }

	    @SideOnly(Side.CLIENT)
	    public final String getBiomeName()
	    {
	        return this.biomeName;
	    }

	    public final float getHeightVariation()
	    {
	        return this.heightVariation;
	    }

	    /**
	     * Gets the constant default temperature for this biome.
	     */
	    public final float getDefaultTemperature()
	    {
	        return this.temperature;
	    }

	    @SideOnly(Side.CLIENT)
	    public final int getWaterColor()
	    {
	        return getWaterColorMultiplier();
	    }

	    public final boolean isSnowyBiome()
	    {
	        return this.enableSnow;
	    }
	    /* ========================================= FORGE START ======================================*/
	    protected List<FlowerEntry> flowers = new java.util.ArrayList<FlowerEntry>();

	    public BiomeDecorator getModdedBiomeDecorator(BiomeDecorator original)
	    {
	        return new net.minecraftforge.event.terraingen.DeferredBiomeDecorator(original);
	    }

	    public int getWaterColorMultiplier()
	    {
	        net.minecraftforge.event.terraingen.BiomeEvent.GetWaterColor event = new net.minecraftforge.event.terraingen.BiomeEvent.GetWaterColor(biome, waterColor);
	        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
	        return event.getNewColor();
	    }

	    public int getModdedBiomeGrassColor(int original)
	    {
	        net.minecraftforge.event.terraingen.BiomeEvent.GetGrassColor event = new net.minecraftforge.event.terraingen.BiomeEvent.GetGrassColor(biome, original);
	        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
	        return event.getNewColor();
	    }

	    public int getModdedBiomeFoliageColor(int original)
	    {
	        net.minecraftforge.event.terraingen.BiomeEvent.GetFoliageColor event = new net.minecraftforge.event.terraingen.BiomeEvent.GetFoliageColor(biome, original);
	        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
	        return event.getNewColor();
	    }
	    public static class FlowerEntry extends WeightedRandom.Item
	    {
	        public final net.minecraft.block.state.IBlockState state;
	        public FlowerEntry(net.minecraft.block.state.IBlockState state, int weight)
	        {
	            super(weight);
	            this.state = state;
	        }
	    }

	    /**
	     * Adds the default flowers, as of 1.7, it is 2 yellow, and 1 red. I chose 10 to allow some wiggle room in the numbers.
	     */
	    public void addDefaultFlowers()
	    {
	        addFlower(Blocks.YELLOW_FLOWER.getDefaultState().withProperty(Blocks.YELLOW_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 20);
	        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.POPPY), 10);
	    }

	    /** Register a new plant to be planted when bonemeal is used on grass.
	     * @param state The block to place.
	     * @param weight The weight of the plant, where red flowers are
	     *               10 and yellow flowers are 20.
	     */
	    public void addFlower(IBlockState state, int weight)
	    {
	        this.flowers.add(new FlowerEntry(state, weight));
	    }

	    public void plantFlower(World world, Random rand, BlockPos pos)
	    {
	        if (flowers.isEmpty()) return;
	        FlowerEntry flower = (FlowerEntry)WeightedRandom.getRandomItem(rand, flowers);
	        if (flower == null || flower.state == null ||
	            (flower.state.getBlock() instanceof net.minecraft.block.BlockBush &&
	              !((net.minecraft.block.BlockBush)flower.state.getBlock()).canBlockStay(world, pos, flower.state)))
	        {
	            return;
	        }

	        world.setBlockState(pos, flower.state, 3);
	    }
	    
	    public static void registerBiome(int id, String name, BaseBiome baseBiome)
	    {
	        REGISTRY.register(id, new ResourceLocation(name), biome);

	        if (biome.isMutation())
	        {
	            MUTATION_TO_BASE_ID_MAP.put(biome, getIdForBiome(REGISTRY.getObject(new ResourceLocation(baseBiome.baseBiomeRegName))));
	        }
	    }

	    public static class BiomeProperties
	        {
	            private final String biomeName;
	            private float baseHeight = 0.1F;
	            private float heightVariation = 0.2F;
	            private float temperature = 0.5F;
	            private float rainfall = 0.5F;
	            private int waterColor = 16777215;
	            private boolean enableSnow;
	            private boolean enableRain = true;
	            @Nullable
	            private String baseBiomeRegName;

	            public BiomeProperties(String nameIn)
	            {
	                this.biomeName = nameIn;
	            }

	            public BaseBiome.BiomeProperties setTemperature(float temperatureIn)
	            {
	                if (temperatureIn > 0.1F && temperatureIn < 0.2F)
	                {
	                    throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
	                }
	                else
	                {
	                    this.temperature = temperatureIn;
	                    return this;
	                }
	            }

	            public BaseBiome.BiomeProperties setRainfall(float rainfallIn)
	            {
	                this.rainfall = rainfallIn;
	                return this;
	            }

	            public BaseBiome.BiomeProperties setBaseHeight(float baseHeightIn)
	            {
	                this.baseHeight = baseHeightIn;
	                return this;
	            }

	            public BaseBiome.BiomeProperties setHeightVariation(float heightVariationIn)
	            {
	                this.heightVariation = heightVariationIn;
	                return this;
	            }

	            public BaseBiome.BiomeProperties setRainDisabled()
	            {
	                this.enableRain = false;
	                return this;
	            }

	            public BaseBiome.BiomeProperties setSnowEnabled()
	            {
	                this.enableSnow = true;
	                return this;
	            }

	            public BaseBiome.BiomeProperties setWaterColor(int waterColorIn)
	            {
	                this.waterColor = waterColorIn;
	                return this;
	            }

	            public BaseBiome.BiomeProperties setBaseBiome(String nameIn)
	            {
	                this.baseBiomeRegName = nameIn;
	                return this;
	            }
	        }

	    public static class SpawnListEntry extends WeightedRandom.Item
	        {
	            public Class <? extends EntityLiving > entityClass;
	            public int minGroupCount;
	            public int maxGroupCount;

	            public SpawnListEntry(Class <? extends EntityLiving > entityclassIn, int weight, int groupCountMin, int groupCountMax)
	            {
	                super(weight);
	                this.entityClass = entityclassIn;
	                this.minGroupCount = groupCountMin;
	                this.maxGroupCount = groupCountMax;
	            }

	            public String toString()
	            {
	                return this.entityClass.getSimpleName() + "*(" + this.minGroupCount + "-" + this.maxGroupCount + "):" + this.itemWeight;
	            }

	            public EntityLiving newInstance(World world) throws Exception
	            {
	                net.minecraftforge.fml.common.registry.EntityEntry entry = net.minecraftforge.fml.common.registry.EntityRegistry.getEntry(this.entityClass);
	                if (entry != null) return (EntityLiving) entry.newInstance(world);
	                return this.entityClass.getConstructor(World.class).newInstance(world);
	            }
	        }

	    public static enum TempCategory
	    {
	        OCEAN,
	        COLD,
	        MEDIUM,
	        WARM;
	    }


}
