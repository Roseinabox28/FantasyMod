package thebetweenlands.common.herblore.aspect;

import net.minecraft.item.ItemStack;

public interface IItemStackMatcher {
	/**
	 * Returns whether the specified item stack matches
	 * 
	 * @param original The original stack
	 * @param stack    The stack that has to be checked if it matches to the
	 *                 original stack
	 * @return
	 */
	public boolean matches(ItemStack original, ItemStack stack);
}
