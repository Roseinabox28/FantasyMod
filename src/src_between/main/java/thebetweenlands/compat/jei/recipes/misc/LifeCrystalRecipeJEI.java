package thebetweenlands.compat.jei.recipes.misc;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.RecipeRegistry;
import thebetweenlands.compat.jei.BetweenlandsJEIPlugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LifeCrystalRecipeJEI implements ICraftingRecipeWrapper, ICustomCraftingRecipeWrapper {

	private final ICraftingGridHelper craftingGridHelper;

	public LifeCrystalRecipeJEI(IGuiHelper guiHelper) {
		craftingGridHelper = guiHelper.createCraftingGridHelper(1, 0);
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return RecipeRegistry.LIFE_CRYSTAL;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ItemStack inCrystal = new ItemStack(ItemRegistry.LIFE_CRYSTAL);
		ItemStack outCrystal = new ItemStack(ItemRegistry.LIFE_CRYSTAL);
		inCrystal.setItemDamage(inCrystal.getMaxDamage());

		List<List<ItemStack>> inputLists = new ArrayList<>(9);
		List<List<ItemStack>> outputLists = new ArrayList<>(9);
		inputLists.add(0, NonNullList.create());
		outputLists.add(0, NonNullList.create());

		for (int i = 0; i < 8; i++) {
			List<ItemStack> crystalStacks = inputLists.get(0);
			crystalStacks.add(inCrystal);

			List<ItemStack> outputStacks = outputLists.get(0);
			outCrystal.setItemDamage(
					outCrystal.getMaxDamage() - MathHelper.ceil((i + 1) * outCrystal.getMaxDamage() / 8.0F));
			outputStacks.add(outCrystal.copy());

			for (int j = 0; j < 8; j++) {
				List<ItemStack> inputStacks;
				if (inputLists.size() <= j + 1) {
					inputStacks = new ArrayList<>(Collections.nCopies(8, null));
					inputLists.add(j + 1, inputStacks);
				} else
					inputStacks = inputLists.get(j + 1);
				if (j <= i)
					inputStacks.set(i, new ItemStack(ItemRegistry.WIGHT_HEART));
			}
		}

		ingredients.setInputLists(VanillaTypes.ITEM, inputLists);
		ingredients.setOutputLists(VanillaTypes.ITEM, outputLists);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IIngredients ingredients) {
		recipeLayout.setShapeless();

		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
		List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

		guiItemStacks.setOverrideDisplayFocus(null);
		craftingGridHelper.setInputs(guiItemStacks, inputs);
		guiItemStacks.set(0, outputs.get(0));
		BetweenlandsJEIPlugin.addRecipeName(getRegistryName(), guiItemStacks, 0);
	}
}
