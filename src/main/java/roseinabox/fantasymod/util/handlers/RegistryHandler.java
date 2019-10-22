package roseinabox.fantasymod.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import roseinabox.fantasymod.commands.CommandDimensionTeleport;
import roseinabox.fantasymod.init.BiomeInit;
import roseinabox.fantasymod.init.DimensionInit;
import roseinabox.fantasymod.init.FluidInit;
import roseinabox.fantasymod.init.ModBlocks;
import roseinabox.fantasymod.init.ModItems;
import roseinabox.fantasymod.util.IHasModel;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
		
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));	
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ModItems.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		
		
		for(Block block : ModBlocks.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
		
		
	}
	public static void otherRegistries()
	{
		
	}
	public static void preInitRegistries()
	{
		FluidInit.registerFluids();//before everything
		
		
		BiomeInit.registerBiomes();
		DimensionInit.registerDimensions();
		RenderHandler.registerCustomMeshesAndStates();
	}
	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandDimensionTeleport());
	}
}
