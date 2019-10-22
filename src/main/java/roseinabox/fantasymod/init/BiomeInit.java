package roseinabox.fantasymod.init;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import roseinabox.fantasymod.world.biome.BaseBiome;
import roseinabox.fantasymod.world.biome.BiomeFantasy;
import roseinabox.fantasymod.world.biome.BiomeSacredPlains;
import roseinabox.fantasymod.world.biome.BiomeSacredPlainsClearing;
import roseinabox.fantasymod.world.biome.BiomeWasteland;

public class BiomeInit 
{
	public static final BiomeFantasy SACRED_PLAINS = new BiomeSacredPlains();
	public static final BiomeFantasy WASTELAND_DIMENSION = new BiomeWasteland();
	public static final BiomeFantasy SACRED_PLAINS_CLEARING = new BiomeSacredPlainsClearing();
	
	public static final List<BiomeFantasy> REGISTERED_BIOMES = new ArrayList<BiomeFantasy>();
	

	private BiomeInit() {
	}
	
	public static void registerBiomes()
	{
		/*initBiome(SACRED_PLAINS, "Sacred Plains", BiomeType.WARM, Type.PLAINS, Type.DRY, Type.DEAD, Type.MAGICAL);
		initBiome(SACRED_PLAINS_CLEARING, "Sacred Plains Clearing", BiomeType.WARM, Type.PLAINS, Type.DRY, Type.DEAD, Type.MAGICAL);
		initBiome(WASTELAND_DIMENSION, "Wasteland", BiomeType.WARM, Type.DEAD, Type.DRY, Type.SPOOKY, Type.MAGICAL);
	*/
	}
	
	
	/*private static BiomeFantasy initBiome(BiomeFantasy biome, String name, BiomeType biomeType, Type... types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		System.out.println("Biome Registered");
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		System.out.println("Biome Added");
		return biome;
	}*/
	@SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        final IForgeRegistry<Biome> registry = event.getRegistry();
        try {
            for (Field f : BiomeInit.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof BiomeFantasy) {
                    BiomeFantasy biome = (BiomeFantasy) obj;
                    registry.register(biome);
                    biome.addTypes();
                    REGISTERED_BIOMES.add(biome);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
