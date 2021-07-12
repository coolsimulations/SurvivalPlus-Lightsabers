package net.coolsimulations.Lightsaber.init;

import micdoodle8.mods.galacticraft.core.GCItems;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.compat.SPCompatRecipeManager;
import net.minecraft.item.ItemStack;

public class LightsaberFutureMCRecipes {

	public static void init() {

		SPCompatRecipeManager.futureRecipeManager.addOreDictionarySmithingRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), "gemSapphire", new ItemStack(LightsaberItems.blue_lightsaber_hilt));
		SPCompatRecipeManager.futureRecipeManager.addOreDictionarySmithingRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), "gemEmerald", new ItemStack(LightsaberItems.green_lightsaber_hilt));
		SPCompatRecipeManager.futureRecipeManager.addOreDictionarySmithingRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), "gemTopaz", new ItemStack(LightsaberItems.yellow_lightsaber_hilt));
		SPCompatRecipeManager.futureRecipeManager.addOreDictionarySmithingRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), "gemPearl", new ItemStack(LightsaberItems.white_lightsaber_hilt));
		SPCompatRecipeManager.futureRecipeManager.addOreDictionarySmithingRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), "gemAmethyst", new ItemStack(LightsaberItems.purple_lightsaber_hilt));
		SPCompatRecipeManager.futureRecipeManager.addOreDictionarySmithingRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), "gemRuby", new ItemStack(LightsaberItems.red_lightsaber_hilt));
		SPCompatRecipeManager.futureRecipeManager.addSmithingRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(LightsaberItems.darksaber_lens), new ItemStack(LightsaberItems.darksaber_hilt));

		if (SPCompatibilityManager.isGCLoaded())
		{
			SPCompatRecipeManager.futureRecipeManager.addSmithingRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(GCItems.itemBasicMoon, 1, 2), new ItemStack(LightsaberItems.blue_lightsaber_hilt));
		}
	}

}
