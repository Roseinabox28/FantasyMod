package thebetweenlands.common.recipe.censer;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import thebetweenlands.api.block.ICenser;
import thebetweenlands.common.lib.ModInfo;
import thebetweenlands.common.registries.FluidRegistry;

public class CenserRecipeDungeonFog extends AbstractCenserRecipe<Void> {
	private static final ResourceLocation ID = new ResourceLocation(ModInfo.ID, "dungeon_fog");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	public boolean matchesInput(FluidStack stack) {
		return stack.getFluid() == FluidRegistry.FOG;
	}

	@Override
	public boolean isCreatingDungeonFog(Void context, ICenser censer) {
		return true;
	}

	@Override
	public int getConsumptionDuration(Void context, ICenser censer) {
		return 18;
	}
}
