package roseinabox.fantasymod;


import net.minecraft.creativetab.CreativeTabs;
//import BetweenlandsConfig.Debug;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Ignore;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import roseinabox.fantasymod.proxy.CommonProxy;
import roseinabox.fantasymod.tabs.FantasyTab;
import roseinabox.fantasymod.util.ModInfo;
import roseinabox.fantasymod.util.handlers.RegistryHandler;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class Main {
	
	
	@Instance
	public static Main instance;
	
	public static final CreativeTabs fantasytab = new FantasyTab("fantasytab");
	
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	static {
		FluidRegistry.enableUniversalBucket();
	}
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) 
	{
		
		
		RegistryHandler.preInitRegistries();
		proxy.preInit();
		RegistryHandler.otherRegistries();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) 
	{
		proxy.init();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit();
	}
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event) 
	{
		RegistryHandler.serverRegistries(event);
	}
	
}
