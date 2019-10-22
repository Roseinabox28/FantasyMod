package roseinabox.fantasymod.util.handlers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import roseinabox.fantasymod.init.ModBlocks;

public class RenderHandler {
	
	public static void registerCustomMeshesAndStates()
	{
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.ETHER_BLOCK), new ItemMeshDefinition() 
		{
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) 
				{
					return new ModelResourceLocation("fantasy:ether", "fluid");
				}
		});
		
		ModelLoader.setCustomStateMapper(ModBlocks.ETHER_BLOCK, new StateMapperBase() 
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return new ModelResourceLocation("fantasy:ether", "fluid");
			}
		});
		
	}
}
