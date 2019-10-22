package roseinabox.fantasymod.init;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import roseinabox.fantasymod.config.FantasyConfig;
import roseinabox.fantasymod.world.dimension.wasteland.DimensionWasteland;

public class DimensionInit
{
	public static final DimensionType WASTELAND = DimensionType.register("Wasteland", "_wasteland", FantasyConfig.WORLD_AND_DIMENSION.dimensionId, DimensionWasteland.class, false);
	
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(FantasyConfig.WORLD_AND_DIMENSION.dimensionId, WASTELAND);
	}
}
