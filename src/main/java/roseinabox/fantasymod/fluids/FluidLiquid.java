package roseinabox.fantasymod.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidLiquid extends Fluid {

	public FluidLiquid(String name, ResourceLocation still, ResourceLocation flow) {
		super(name, still, flow);
		this.setUnlocalizedName(name);
		// TODO Auto-generated constructor stub
	}

}
