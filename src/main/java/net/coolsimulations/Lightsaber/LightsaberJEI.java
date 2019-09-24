/**package net.coolsimulations.Lightsaber;

import javax.annotation.Nonnull;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class LightsaberJEI extends BlankModPlugin{
	
public static void loadBlacklist(IIngredientBlacklist jeiHidden){
		
		if (SPCompatibilityManager.isJeiLoaded()){
			
			jeiHidden.addIngredientToBlacklist(new ItemStack(LightsaberItems.red_lightsaber, 1, 0));
			jeiHidden.addIngredientToBlacklist(new ItemStack(LightsaberItems.blue_lightsaber, 1, 0));
			jeiHidden.addIngredientToBlacklist(new ItemStack(LightsaberItems.green_lightsaber, 1, 0));
			jeiHidden.addIngredientToBlacklist(new ItemStack(LightsaberItems.purple_lightsaber, 1, 0));
			jeiHidden.addIngredientToBlacklist(new ItemStack(LightsaberItems.white_lightsaber, 1, 0));
			jeiHidden.addIngredientToBlacklist(new ItemStack(LightsaberItems.darksaber, 1, 0));
		}
	}
	
	@Override
    public void register(@Nonnull IModRegistry registry)
    {
		LightsaberJEI.loadBlacklist(registry.getJeiHelpers().getIngredientBlacklist());
    }
	
	

}
**/