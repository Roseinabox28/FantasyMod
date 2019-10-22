package roseinabox.fantasymod.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import roseinabox.fantasymod.fluids.FluidLiquid;

public class FluidInit {
    public static final Fluid ETHER_FLUID = new FluidLiquid("ether", new ResourceLocation("fantasy:blocks/ether_still"), new ResourceLocation("fantasy:blocks/ether_flow"));

    public static void registerFluids()
    {
    	registerFluid(ETHER_FLUID);
    }
    
    public static void registerFluid(Fluid fluid) {
    	FluidRegistry.registerFluid(fluid);
    	FluidRegistry.addBucketForFluid(fluid);
    }
}
