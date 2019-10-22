package thebetweenlands.common.herblore.aspect.type;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import thebetweenlands.api.aspect.IAspectType;
import thebetweenlands.common.lib.ModInfo;

public class AspectOrdaniis implements IAspectType {
	@Override
	public String getName() {
		return "Ordaniis";
	}

	@Override
	public String getType() {
		return I18n.translateToLocal("manual.enhance");
	}

	@Override
	public String getDescription() {
		return "Needs new decription";
	}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(ModInfo.ID, "textures/items/strictly_herblore/misc/aspect_ordaniis.png");
	}

	@Override
	public int getColor() {
		return 0xFF64EF99;
	}
}
