package roseinabox.fantasymod.world.biome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.util.entity.spawning.ICustomSpawnEntriesProvider;
import roseinabox.fantasymod.util.entity.spawning.ICustomSpawnEntry;
import roseinabox.fantasymod.util.entity.spawning.IWeightProvider;
import roseinabox.fantasymod.util.event.InitializeFantasyBiomeEvent;
import roseinabox.fantasymod.world.gen.biome.generator.BiomeGenerator;

public class BiomeFantasy extends Biome implements IWeightProvider, ICustomSpawnEntriesProvider 
{
	
	private final List<ICustomSpawnEntry> blSpawnEntries = new ArrayList<>();
	private int grassColor = -1, foliageColor = -1, secondaryGrassColor = -1, secondaryFoliageColor = -1;
	private short biomeWeight;
	private BiomeGenerator biomeGenerator;
	private int[] fogColorRGB = new int[]{(int) 255, (int) 255, (int) 255};
	
	public BiomeFantasy( BiomeProperties properties) {
		super(properties);
		
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.biomeWeight = 100;
		this.topBlock = ModBlocks.CORRUPT_GRASS.getDefaultState();
		this.fillerBlock = ModBlocks.GENERIC_STONE.getDefaultState();
		this.biomeGenerator = new BiomeGenerator(this);

		//this.setFogColor(10, 30, 22);
		
		List<ICustomSpawnEntry> spawnEntries = new ArrayList<>();
		//this.addSpawnEntries(spawnEntries);
		
		MinecraftForge.EVENT_BUS.post(new InitializeFantasyBiomeEvent(this, spawnEntries));
		
		this.blSpawnEntries.addAll(spawnEntries);
	}
	/**
	 * Adds the entity spawn entries
	 */
	/*
	protected void addSpawnEntries(List<ICustomSpawnEntry> entries) {
		BiPredicate<World, BlockPos> bloodSkyPredicate = ConditionalSpawnEntry
				.createEventPredicate(new ResourceLocation(ModInfo.ID, "blood_sky"));

		entries.add(new ConditionalSpawnEntry(800,
				new SurfaceSpawnEntry(-1, EntityFirefly.class, EntityFirefly::new, (short) 280), bloodSkyPredicate)
						.setSpawnCheckRadius(16.0D).setGroupSize(1, 4));
		entries.add(new ConditionalSpawnEntry(801,
				new SurfaceSpawnEntry(-1, EntitySwampHag.class, EntitySwampHag::new, (short) 250), bloodSkyPredicate) {
			@Override
			public EntityLiving createEntity(World world) {
				EntityLiving entity = super.createEntity(world);
				entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32D);
				entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
				return entity;
			}
		}.setHostile(true));
		entries.add(new ConditionalSpawnEntry(802,
				new SurfaceSpawnEntry(-1, EntityPeatMummy.class, EntityPeatMummy::new, (short) 65), bloodSkyPredicate) {
			@Override
			public EntityLiving createEntity(World world) {
				EntityLiving entity = super.createEntity(world);
				entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
						.setBaseValue(EntityPeatMummy.BASE_SPEED + 0.075D);
				entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
						.setBaseValue(EntityPeatMummy.BASE_DAMAGE + 2.0D);
				return entity;
			}
		}.setHostile(true).setSpawnCheckRadius(20.0D));
		entries.add(new ConditionalSpawnEntry(804,
				new WallSpawnEntry(-1, EntityCCGroundSpawner.class, EntityCCGroundSpawner::new, (short) 120),
				bloodSkyPredicate).setSpawnCheckRadius(32.0D).setGroupSize(1, 1).setHostile(true));

		entries.add(new LocationSpawnEntry(803, EntityPyrad.class, EntityPyrad::new, (short) 120,
				EnumLocationType.GIANT_TREE) {
			@Override
			public boolean canSpawn(World world, Chunk chunk, BlockPos pos, IBlockState blockState,
					IBlockState surfaceBlockState) {
				return !blockState.isNormalCube() && SurfaceType.MIXED_GROUND.matches(surfaceBlockState);
			};
		}.setHostile(true).setSpawnCheckRadius(50.0D).setSpawningInterval(5000));
	}
*/
	/**
	 * Sets the biome generator
	 * 
	 * @param generator
	 * @return
	 */
	protected final BiomeFantasy setBiomeGenerator(BiomeGenerator generator) {
		if (generator.getBiome() != this)
			throw new RuntimeException("Generator was assigned to a different biome!");
		this.biomeGenerator = generator;
		return this;
	}

	/**
	 * Returns the biome generator. If no generator was specified the default biome
	 * generator is returned
	 * 
	 * @return
	 */
	public final BiomeGenerator getBiomeGenerator() {
		return this.biomeGenerator;
	}

	@Override
	public final List<ICustomSpawnEntry> getCustomSpawnEntries() {
		return Collections.unmodifiableList(this.blSpawnEntries);
	}

	/**
	 * Sets Biome specific weighted probability. The default weight is 100.
	 * 
	 * @param weight
	 */
	protected final BiomeFantasy setWeight(int weight) {
		this.biomeWeight = (short) weight;
		return this;
	}

	/**
	 * Sets the grass and foliage colors
	 * 
	 * @param grassColor
	 * @param foliageColor
	 * @return
	 */
	public final BiomeFantasy setFoliageColors(int grassColor, int foliageColor) {
		this.grassColor = grassColor;
		this.foliageColor = foliageColor;
		return this;
	}

	/**
	 * Sets the secondary grass and foliage color. Will be applied to patches using
	 * noise
	 * 
	 * @param grassColor
	 * @param foliageColor
	 * @return
	 */
	public final BiomeFantasy setSecondaryFoliageColors(int grassColor, int foliageColor) {
		this.secondaryGrassColor = grassColor;
		this.secondaryFoliageColor = foliageColor;
		return this;
	}

	/**
	 * Sets the biome fog color
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	public final BiomeFantasy setFogColor(int red, int green, int blue) {
		this.fogColorRGB[0] = red;
		this.fogColorRGB[1] = green;
		this.fogColorRGB[2] = blue;
		return this;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		if (this.secondaryGrassColor < 0) {
			if (this.grassColor < 0) {
				return super.getGrassColorAtPos(pos);
			}
			return this.grassColor;
		}
		double noise = GRASS_COLOR_NOISE.getValue((double) pos.getX() * 0.0225D, (double) pos.getZ() * 0.0225D);
		if (noise < -0.1) {
			if (this.grassColor < 0) {
				return super.getGrassColorAtPos(pos);
			}
			return this.grassColor;
		} else {
			if (this.secondaryGrassColor < 0) {
				return super.getGrassColorAtPos(pos);
			}
			return this.secondaryGrassColor;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		if (this.secondaryFoliageColor < 0) {
			if (this.foliageColor < 0) {
				return super.getGrassColorAtPos(pos);
			}
			return this.foliageColor;
		}
		double noise = GRASS_COLOR_NOISE.getValue((double) pos.getX() * 0.0225D, (double) pos.getZ() * 0.0225D);
		if (noise < -0.1) {
			if (this.foliageColor < 0) {
				return super.getGrassColorAtPos(pos);
			}
			return this.foliageColor;
		} else {
			if (this.secondaryFoliageColor < 0) {
				return super.getGrassColorAtPos(pos);
			}
			return this.secondaryFoliageColor;
		}
	}

	/**
	 * Returns Biome specific weighted probability.
	 */
	@Override
	public final short getWeight() {
		return this.biomeWeight;
	}
	
	public void addTypes() {

	}

	
}
