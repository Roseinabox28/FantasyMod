package roseinabox.fantasymod.config;

import java.io.File;


import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Ignore;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import roseinabox.fantasymod.config.properties.StringSetProperty;
import roseinabox.fantasymod.util.*;

@Config(modid = ModInfo.ID, category = "", name = ModInfo.ID + "/config")
public class FantasyConfig {
	@Ignore
	public static File configDir;

	@Ignore
	private static final String LANG_PREFIX = "config." + ModInfo.ID + ".";
	
	@Name("world_and_dimension")
	@LangKey(LANG_PREFIX + "world_and_dimension")
	public static final WorldAndDimension WORLD_AND_DIMENSION = new WorldAndDimension();
	public static class WorldAndDimension {
		@Name("dimension_id")
		@LangKey(LANG_PREFIX + "dimension_id")
		@RequiresMcRestart
		public int dimensionId = 28;
		
		@Name("dimension_brightness")
		@LangKey(LANG_PREFIX + "dimension_brightness")
		@RangeInt(min = 0, max = 100)
		public int dimensionBrightness = 75;
		
		@Name("portal_unsafe_biomes")
		@LangKey(LANG_PREFIX + "portal_unsafe_biomes")
		@Comment("A list of unsafe biomes for the portal to try avoid generating in")
		public String[] portalUnsafeBiomes = { "minecraft:ocean", "minecraft:river", "minecraft:frozen_ocean",
				"minecraft:frozen_river", "minecraft:mushroom_island_shore", "minecraft:beaches",
				"minecraft:deep_ocean", "minecraft:stone_beach", "minecraft:cold_beach" };
		@Ignore
		public final StringSetProperty portalUnsafeBiomesSet = new StringSetProperty(() -> this.portalUnsafeBiomes);

		@Name("portal_biome_search_range")
		@LangKey(LANG_PREFIX + "portal_biome_search_range")
		@Comment("The biome search range used to find a suitable biome when a portal is generated. If you find that a suitable biome isn't found reliably enough this can be increased at the cost of taking more time to generate portals")
		@RangeInt(min = 16, max = Integer.MAX_VALUE)
		public int portalBiomeSearchRange = 256;

	}
	
	@Name("mob_spawning")
	@LangKey(LANG_PREFIX + "mob_spawning")
	public static final MobSpawning MOB_SPAWNING = new MobSpawning();

	public static class MobSpawning {
		@Name("max_entities_per_loaded_area")
		@LangKey(LANG_PREFIX + "max_entities_per_loaded_area")
		@Comment("The maximum amount of naturally spawned entities per loaded area (in most cases this means per player)")
		@RangeInt(min = 0)
		public int maxEntitiesPerLoadedArea = 250;

		@Name("hard_entity_limit")
		@LangKey(LANG_PREFIX + "hard_entity_limit")
		@Comment("The maximum amount of naturally spawned entities in Dimensions per world")
		@RangeInt(min = 0)
		public int hardEntityLimit = 600;
	}
	
	

}
