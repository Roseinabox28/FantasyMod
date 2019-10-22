package roseinabox.fantasymod.world.dimension.wasteland;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roseinabox.fantasymod.config.FantasyConfig;
import roseinabox.fantasymod.init.BiomeInit;
import roseinabox.fantasymod.init.DimensionInit;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.world.event.FEnvironmentEventRegistry;
import roseinabox.fantasymod.world.gen.ChunkGeneratorWasteland;
import roseinabox.fantasymod.world.gen.biome.BiomeProviderFantasy;

public class DimensionWasteland extends WorldProvider{

	public static final int LAYER_HEIGHT = 120;

	public static final int CAVE_WATER_HEIGHT = 15;

	public static final int PITSTONE_HEIGHT = CAVE_WATER_HEIGHT + 30;

	public static final int CAVE_START = LAYER_HEIGHT - 10;

	private boolean allowHostiles, allowAnimals;
	
	//@SideOnly(Side.CLIENT)
	//private static BLSkyRenderer skyRenderer;

	private boolean showClouds = false;
	public DimensionWasteland()
	{
		this.allowHostiles = true;
		this.allowAnimals = true;
	}
	
	public static final DimensionWasteland getProvider(World world) {
		if (world != null && world.provider instanceof DimensionWasteland) {
			return (DimensionWasteland) world.provider;
		}
		return null;
	}
	
	@Override
	public DimensionType getDimensionType() {
		
		return DimensionInit.WASTELAND;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorWasteland(world, world.getSeed(), Blocks.BEDROCK, ModBlocks.CORRUPT_GRASS, 20);
	}
	
	public boolean getCanSpawnHostiles() {
		return this.allowHostiles;
	}

	public boolean getCanSpawnAnimals() {
		return this.allowAnimals;
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTickTime) {
		return 0.8F;
	}
	
	protected float getOverworldSunBrightnessFactor(float partialTicks) {
		float f = this.getOverworldCelestialAngle(partialTicks);
		float f1 = 1.0F - (MathHelper.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.5F);
		f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
		f1 = 1.0F - f1;
		// f1 = (float)((double)f1 * (1.0D - (double)(this.getRainStrength(partialTicks)
		// * 5.0F) / 16.0D));
		// f1 = (float)((double)f1 * (1.0D -
		// (double)(this.getThunderStrength(partialTicks) * 5.0F) / 16.0D));
		return f1;
	}
	
	@SideOnly(Side.CLIENT)
	protected float getOverworldSunBrightness(float partialTicks) {
		float f = this.getOverworldCelestialAngle(partialTicks);
		float f1 = 1.0F - (MathHelper.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.2F);
		f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
		f1 = 1.0F - f1;
		// f1 = (float)((double)f1 * (1.0D - (double)(this.getRainStrength(partialTicks)
		// * 5.0F) / 16.0D));
		// f1 = (float)((double)f1 * (1.0D -
		// (double)(this.getThunderStrength(partialTicks) * 5.0F) / 16.0D));
		return f1 * 0.8F + 0.2F;
	}

	public float getOverworldCelestialAngle(float partialTicks) {
		int i = (int) (this.world.getWorldTime() % 24000L);
		float f = ((float) i + partialTicks) / 24000.0F - 0.25F;

		if (f < 0.0F) {
			++f;
		}

		if (f > 1.0F) {
			--f;
		}

		float f1 = 1.0F - (float) ((Math.cos((double) f * Math.PI) + 1.0D) / 2.0D);
		f = f + (f1 - f) / 3.0F;
		return f;
	}
	
	

	@Override
	protected void generateLightBrightnessTable() {
		if (this.world.isRemote) {
			float configBrightness = FantasyConfig.WORLD_AND_DIMENSION.dimensionBrightness / 100.0F;
			for (int i = 0; i <= 15; i++) {
				float f1 = 1F - (float) Math.pow(i / 15F, 1.1F + 0.35F * (1.0F - configBrightness));
				this.lightBrightnessTable[i] = Math
						.max((1.0F - f1) / (f1 * f1 * (0.75F + configBrightness * 0.6F) + 1.0F)
								* (0.4F + configBrightness * 0.5F) - 0.1F, 0.0F);
			}
		} else {
			super.generateLightBrightnessTable();
		}
	}
	
	@Override
	public void init() {
		this.setDimension(FantasyConfig.WORLD_AND_DIMENSION.dimensionId);
		this.biomeProvider = new BiomeProviderFantasy(this, this.world.getWorldInfo());
		this.hasSkyLight = true;
	}

	
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	
	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}
/*
	public FEnvironmentEventRegistry getEnvironmentEventRegistry() {
		return this.getWorldData().getEnvironmentEventRegistry();
	}
	*/

}
