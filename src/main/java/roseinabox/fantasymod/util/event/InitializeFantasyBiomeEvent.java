package roseinabox.fantasymod.util.event;

import java.util.List;

import net.minecraftforge.fml.common.eventhandler.Event;
import roseinabox.fantasymod.util.entity.spawning.ICustomSpawnEntry;
import roseinabox.fantasymod.world.biome.BiomeFantasy;

public class InitializeFantasyBiomeEvent extends Event {
	private final BiomeFantasy biome;
	private final List<ICustomSpawnEntry> spawnEntries;

	public InitializeFantasyBiomeEvent(BiomeFantasy biome, List<ICustomSpawnEntry> spawnEntries) {
		this.biome = biome;
		this.spawnEntries = spawnEntries;
	}

	/**
	 * Returns the biome
	 * 
	 * @return
	 */
	public BiomeFantasy getBiome() {
		return this.biome;
	}

	/**
	 * Returns a modifiable list of the biome's entity spawn entries
	 * 
	 * @return
	 */
	public List<ICustomSpawnEntry> getModifiableSpawnEntries() {
		return this.spawnEntries;
	}
}
