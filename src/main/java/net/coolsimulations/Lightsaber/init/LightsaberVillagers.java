package net.coolsimulations.Lightsaber.init;

import java.util.Iterator;
import java.util.Random;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.SPTags;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class LightsaberVillagers {
	
	public static VillagerRegistry.VillagerProfession villagerJedi;
	public static VillagerRegistry.VillagerProfession villagerSith;
	
	public static void registerVillagers() {
    	IForgeRegistry<VillagerRegistry.VillagerProfession> villagerProfessions = ForgeRegistries.VILLAGER_PROFESSIONS;
    	
		villagerJedi = new VillagerRegistry.VillagerProfession(Reference.MOD_ID + ":jedi", Reference.MOD_ID + ":textures/entity/jedi.png", Reference.MOD_ID + ":textures/entity/zombie_jedi.png");
		villagerProfessions.register(villagerJedi);
		
		villagerSith = new VillagerRegistry.VillagerProfession(Reference.MOD_ID + ":sith", Reference.MOD_ID + ":textures/entity/sith.png", Reference.MOD_ID + ":textures/entity/zombie_sith.png");
		villagerProfessions.register(villagerSith);
		
		VillagerRegistry.VillagerCareer jediCareer = new VillagerRegistry.VillagerCareer(villagerJedi, "jedi");
		jediCareer.addTrade(1, new JediLevel1());
		jediCareer.addTrade(2, new JediLevel2());
		jediCareer.addTrade(3, new JediLevel3());
		
		VillagerRegistry.VillagerCareer sithCareer = new VillagerRegistry.VillagerCareer(villagerSith, "sith");
		sithCareer.addTrade(1, new SithLevel1());
		sithCareer.addTrade(2, new SithLevel2());
		sithCareer.addTrade(3, new SithLevel3());

    }

    public static class JediLevel1 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		for(Iterator<Item> iter = SPTags.Items.GEMS_AMETHYST.getAllElements().iterator(); iter.hasNext(); ) {
    			recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.blue_lightsaber_hilt)));
    		}
    		
    		for(Iterator<Item> iter = SPTags.Items.GEMS_TOPAZ.getAllElements().iterator(); iter.hasNext(); ) {
    			recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.green_lightsaber_hilt)));
    		}
    		
    		for(Iterator<Item> iter = SPTags.Items.GEMS_PEARL.getAllElements().iterator(); iter.hasNext(); ) {
    			recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.white_lightsaber_hilt)));
    		}
    		
    		for(Iterator<Item> iter = SPTags.Items.GEMS_SAPPHIRE.getAllElements().iterator(); iter.hasNext(); ) {
    			recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.purple_lightsaber_hilt)));
    		}
    		
    		if (SPCompatibilityManager.isGCLoaded())
            {
    			//recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(GCItems.itemBasicMoon, 1, 2), new ItemStack(LightsaberItems.purple_lightsaber_hilt)));
            }
    	}
    }
    
    public static class JediLevel2 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 13), new ItemStack(Items.BOOK, 7), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.SWIFTNESS)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 19), new ItemStack(Items.BOOK, 12), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.LONG_LEAPING)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 21), new ItemStack(Items.BOOK, 14), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.HEALING)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 24), new ItemStack(Items.BOOK, 10), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.REGENERATION)));
    		
    	}
    }
    
    public static class JediLevel3 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 30), new ItemStack(Items.BOOK, 20), new ItemStack(Items.TOTEM_OF_UNDYING)));
    		
    	}
    }
    
    public static class SithLevel1 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		for(Iterator<Item> iter = SPTags.Items.GEMS_RUBY.getAllElements().iterator(); iter.hasNext(); ) {
    			recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.red_lightsaber_hilt)));
    		}
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(LightsaberItems.darksaber_lens), new ItemStack(LightsaberItems.darksaber_hilt)));
    		 
    	}
    }
    
    public static class SithLevel2 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 8), new ItemStack(Blocks.TNT, 12), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.FIRE_RESISTANCE)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 11), new ItemStack(Blocks.TNT, 15), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.LONG_INVISIBILITY)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 13), new ItemStack(Blocks.TNT, 13), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.LONG_STRENGTH)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 18), new ItemStack(Blocks.TNT, 20), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.STRONG_POISON)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 17), new ItemStack(Blocks.TNT, 19), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.STRONG_HARMING)));
    		
    	}
    }
    
    public static class SithLevel3 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 25), new ItemStack(Blocks.TNT, 30), new ItemStack(Items.DRAGON_BREATH)));
    		
    	}
    }

}
