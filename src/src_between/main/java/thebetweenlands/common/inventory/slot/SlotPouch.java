package thebetweenlands.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thebetweenlands.common.config.BetweenlandsConfig;
import thebetweenlands.common.item.equipment.ItemLurkerSkinPouch;

public class SlotPouch extends Slot {
	public SlotPouch(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return !(stack.getItem() instanceof ItemLurkerSkinPouch)
				&& !BetweenlandsConfig.GENERAL.pouchBlacklist.isListed(stack);
	}
}