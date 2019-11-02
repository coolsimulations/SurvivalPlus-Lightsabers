package net.coolsimulations.Lightsaber.recipes;

import ic2.api.item.IC2Items;
import micdoodle8.mods.galacticraft.core.GCItems;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class LightsaberShapedRecipes {

		public static void loadRecipes()
	    {
	        if(SPCompatibilityManager.isIc2Loaded()) {
	        	
	        	if(!SPCompatibilityManager.isGCLoaded()) {
	        	
	        		addRecipe(new ItemStack(LightsaberItems.pommel_cap), new Object[]{"ICI","IBI","TTT", 'B', IC2Items.getItem("re_battery"), 'I', "plateDenseIron", 'T', "plateDenseTitanium", 'C', "circuitAdvanced"});
	        		addRecipe(new ItemStack(LightsaberItems.emitter_matrix), new Object[]{"CTC","WDW","CTC", 'C', "plateLead", 'W', "circuitBasic", 'T', "plateTitanium", 'D', "gemDiamond"});
	        		addRecipe(new ItemStack(LightsaberItems.blade_emitter), new Object[]{"TGT","BMB","TCT", 'M', LightsaberItems.emitter_matrix, 'T', "plateTitanium", 'G', IC2Items.getItem("glass", "reinforced"), 'B', IC2Items.getItem("re_battery"), 'C', "circuitBasic"});
	        		addRecipe(new ItemStack(LightsaberItems.darksaber_lens), new Object[]{"OSO","QGQ","OEO", 'G', IC2Items.getItem("glass", "reinforced"), 'Q', "gemQuartz", 'O', IC2Items.getItem("crafting", "coal_chunk"), 'E', Blocks.DRAGON_EGG, 'S', "gemSpinel"});
	        	}
	        	
	        	addRecipe(new ItemStack(LightsaberItems.focusing_lens), new Object[]{"CGC","QGQ","CQC", 'G', IC2Items.getItem("glass", "reinforced"), 'Q', "gemQuartz", 'C', "casingTitanium"});
	        	addRecipe(new ItemStack(LightsaberItems.inert_power_insulator), new Object[]{"RBR","RBR","RBR", 'B', IC2Items.getItem("advanced_re_battery"), 'R', IC2Items.getItem("crafting", "rubber")});
	        	addRecipe(new ItemStack(LightsaberItems.activation_stud), new Object[]{"TIT","BCB","TIT", 'B', Blocks.STONE_BUTTON, 'C', "circuitAdvanced", 'I', "plateIron", 'T', "casingTitanium"});
	        	addRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new Object[]{"IEI","NFA","TPT", 'P', LightsaberItems.pommel_cap, 'I', "casingTitanium", 'F', LightsaberItems.focusing_lens, 'T', "plateTitanium", 'E', LightsaberItems.blade_emitter, 'A', LightsaberItems.activation_stud, 'N', LightsaberItems.inert_power_insulator});
        		addRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new Object[]{"IEI","AFN","TPT", 'P', LightsaberItems.pommel_cap, 'I', "casingTitanium", 'F', LightsaberItems.focusing_lens, 'T', "plateTitanium", 'E', LightsaberItems.blade_emitter, 'A', LightsaberItems.activation_stud, 'N', LightsaberItems.inert_power_insulator});
	        	
	        }
	        
	        if(SPCompatibilityManager.isIc2Loaded() && SPCompatibilityManager.isGCLoaded()) {
	        	
	        	addRecipe(new ItemStack(LightsaberItems.pommel_cap), new Object[]{"ICI","IBI","TTT", 'B', IC2Items.getItem("re_battery"), 'I', "compressedSteel", 'T', "compressedMeteoricIron", 'C', "circuitAdvanced"});
        		addRecipe(new ItemStack(LightsaberItems.emitter_matrix), new Object[]{"CTC","WDW","CTC", 'C', "compressedAluminum", 'W', "waferBasic", 'T', "plateTitanium", 'D', "gemDiamond"});
        		addRecipe(new ItemStack(LightsaberItems.blade_emitter), new Object[]{"TGT","BMB","TCT", 'M', LightsaberItems.emitter_matrix, 'T', "plateTitanium", 'G', IC2Items.getItem("glass", "reinforced"), 'B', IC2Items.getItem("re_battery"), 'C', "waferBasic"});
        		addRecipe(new ItemStack(LightsaberItems.darksaber_lens), new Object[]{"OSO","QGQ","OEO", 'G', IC2Items.getItem("glass", "reinforced"), 'Q', "gemQuartz", 'O', "compressedTitanium", 'E', Blocks.DRAGON_EGG, 'S', "gemSpinel"});
	        }
	        
	        if(SPCompatibilityManager.isGCLoaded()) {
	        	
	        	if(!SPCompatibilityManager.isIc2Loaded()) {
	        		
	        		Block glassStrong = Block.REGISTRY.getObject(new ResourceLocation(SPCompatibilityManager.GCCORE_MODID, "space_glass_strong"));
	        		
	        		addRecipe(new ItemStack(LightsaberItems.pommel_cap), new Object[]{"ICI","IBI","TTT", 'B', new ItemStack(GCItems.battery, 1, GCItems.battery.getMaxDamage()), 'I', "compressedSteel", 'T', "compressedMeteoricIron", 'C', "waferAdvanced"});
	        		addRecipe(new ItemStack(LightsaberItems.emitter_matrix), new Object[]{"CTC","WDW","CTC", 'C', "compressedAluminum", 'W', "waferBasic", 'T', "compressedSteel", 'D', "gemDiamond"});
	        		addRecipe(new ItemStack(LightsaberItems.blade_emitter), new Object[]{"TGT","BMB","TCT", 'M', LightsaberItems.emitter_matrix, 'T', "compressedSteel", 'G', glassStrong, 'B', new ItemStack(GCItems.battery, 1, GCItems.battery.getMaxDamage()), 'C', "waferBasic"});
	        		addRecipe(new ItemStack(LightsaberItems.blade_emitter), new Object[]{"TGT","BMB","TCT", 'M', LightsaberItems.emitter_matrix, 'T', "compressedSteel", 'G', new ItemStack(glassStrong, 1, 1), 'B', new ItemStack(GCItems.battery, 1, GCItems.battery.getMaxDamage()), 'C', "waferBasic"});
	        		addRecipe(new ItemStack(LightsaberItems.focusing_lens), new Object[]{"CGC","QGQ","CQC", 'G', glassStrong, 'Q', "gemQuartz", 'C', "compressedAluminum"});
	        		addRecipe(new ItemStack(LightsaberItems.focusing_lens), new Object[]{"CGC","QGQ","CQC", 'G', new ItemStack(glassStrong, 1, 1), 'Q', "gemQuartz", 'C', "compressedAluminum"});
	        		addRecipe(new ItemStack(LightsaberItems.inert_power_insulator), new Object[]{"RBR","SBS","RBR", 'B', new ItemStack(GCItems.battery, 1, GCItems.battery.getMaxDamage()), 'S', "slimeball", 'R', Blocks.WOOL});
	        		addRecipe(new ItemStack(LightsaberItems.activation_stud), new Object[]{"TIT","BCB","TIT", 'B', Blocks.STONE_BUTTON, 'C', "waferAdvanced", 'I', "compressedIron", 'T', "compressedAluminum"});
	        		addRecipe(new ItemStack(LightsaberItems.darksaber_lens), new Object[]{"OSO","QGQ","OEO", 'G', glassStrong, 'Q', "gemQuartz", 'O', "compressedTitanium", 'E', Blocks.DRAGON_EGG, 'S', "gemSpinel"});
	        		addRecipe(new ItemStack(LightsaberItems.darksaber_lens), new Object[]{"OSO","QGQ","OEO", 'G', new ItemStack(glassStrong, 1, 1), 'Q', "gemQuartz", 'O', "compressedTitanium", 'E', Blocks.DRAGON_EGG, 'S', "gemSpinel"});
	        		addRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new Object[]{"IEI","NFA","TPT", 'P', LightsaberItems.pommel_cap, 'I', "compressedAluminum", 'F', LightsaberItems.focusing_lens, 'T', "ingotTitanium", 'E', LightsaberItems.blade_emitter, 'A', LightsaberItems.activation_stud, 'N', LightsaberItems.inert_power_insulator});
	        		addRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new Object[]{"IEI","AFN","TPT", 'P', LightsaberItems.pommel_cap, 'I', "compressedAluminum", 'F', LightsaberItems.focusing_lens, 'T', "ingotTitanium", 'E', LightsaberItems.blade_emitter, 'A', LightsaberItems.activation_stud, 'N', LightsaberItems.inert_power_insulator});

	        	}
	        	
	        }
	        
	        if(!SPCompatibilityManager.isIc2Loaded() && !SPCompatibilityManager.isGCLoaded()) {
	        	
	        	addRecipe(new ItemStack(LightsaberItems.pommel_cap), new Object[]{"ICI","IBI","TTT", 'B', "blockRedstone", 'I', "blockIron", 'T', "ingotTitanium", 'C', Items.COMPARATOR});
	        	addRecipe(new ItemStack(LightsaberItems.emitter_matrix), new Object[]{"CTC","WDW","CTC", 'C', "ingotBronze", 'W', Items.REPEATER, 'T', "ingotTitanium", 'D', "gemDiamond"});
	        	addRecipe(new ItemStack(LightsaberItems.blade_emitter), new Object[]{"TGT","BMB","TCT", 'M', LightsaberItems.emitter_matrix, 'T', "ingotTitanium", 'G', "blockGlass", 'B', "blockRedstone", 'C', Items.REPEATER});
	        	addRecipe(new ItemStack(LightsaberItems.focusing_lens), new Object[]{"CGC","QGQ","CQC", 'G', "blockGlass", 'Q', "gemQuartz", 'C', "ingotTitanium"});
	        	addRecipe(new ItemStack(LightsaberItems.inert_power_insulator), new Object[]{"RBR","SBS","RBR", 'B', "blockRedstone", 'S', "slimeball", 'R', Blocks.WOOL});
	        	addRecipe(new ItemStack(LightsaberItems.activation_stud), new Object[]{"TIT","BCB","TIT", 'B', Blocks.STONE_BUTTON, 'C', Items.COMPARATOR, 'I', "ingotIron", 'T', "ingotTitanium"});
	        	addRecipe(new ItemStack(LightsaberItems.darksaber_lens), new Object[]{"OSO","QGQ","OEO", 'G', "blockGlassBlack", 'Q', "gemQuartz", 'O', "obsidian", 'E', Blocks.DRAGON_EGG, 'S', "gemSpinel"});
	        	addRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new Object[]{"IEI","NFA","TPT", 'P', LightsaberItems.pommel_cap, 'I', "ingotTitanium", 'F', LightsaberItems.focusing_lens, 'T', "blockIron", 'E', LightsaberItems.blade_emitter, 'A', LightsaberItems.activation_stud, 'N', LightsaberItems.inert_power_insulator});
        		addRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new Object[]{"IEI","AFN","TPT", 'P', LightsaberItems.pommel_cap, 'I', "ingotTitanium", 'F', LightsaberItems.focusing_lens, 'T', "blockIron", 'E', LightsaberItems.blade_emitter, 'A', LightsaberItems.activation_stud, 'N', LightsaberItems.inert_power_insulator});
	        }
	    }
	
	public static void addRecipe(ItemStack result, Object[] obj)
    {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(result, obj));
    }
}
