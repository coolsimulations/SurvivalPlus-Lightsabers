package net.coolsimulations.Lightsaber.init;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.GCItems;
import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.oredict.OreDictionary;

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
    		
    		if(OreDictionary.getOres("gemSapphire").size() > 0) {
    			for(int i = 0; i < OreDictionary.getOres("gemSapphire").size(); i++) {
   					recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), OreDictionary.getOres("gemSapphire").get(i), new ItemStack(LightsaberItems.blue_lightsaber_hilt)));
   				}
    		}
    		
    		if(OreDictionary.getOres("gemTopaz").size() > 0) {
    			for(int i = 0; i < OreDictionary.getOres("gemTopaz").size(); i++) {
   					recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), OreDictionary.getOres("gemTopaz").get(i), new ItemStack(LightsaberItems.green_lightsaber_hilt)));
   				}
    		}
    		
    		if(OreDictionary.getOres("gemPearl").size() > 0) {
    			for(int i = 0; i < OreDictionary.getOres("gemPearl").size(); i++) {
   					recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), OreDictionary.getOres("gemPearl").get(i), new ItemStack(LightsaberItems.white_lightsaber_hilt)));
   				}
    		}
    		
    		if(OreDictionary.getOres("gemAmethyst").size() > 0) {
    			for(int i = 0; i < OreDictionary.getOres("gemAmethyst").size(); i++) {
   					recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), OreDictionary.getOres("gemAmethyst").get(i), new ItemStack(LightsaberItems.purple_lightsaber_hilt)));
   				}
    		}
    		
    		if (SPCompatibilityManager.isGCLoaded())
            {
    			recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(GCItems.itemBasicMoon, 1, 2), new ItemStack(LightsaberItems.blue_lightsaber_hilt)));
            }
    	}
    }
    
    public static class JediLevel2 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 13), new ItemStack(Items.BOOK, 7), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.SWIFTNESS)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 19), new ItemStack(Items.BOOK, 12), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_LEAPING)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 21), new ItemStack(Items.BOOK, 14), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.HEALING)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 24), new ItemStack(Items.BOOK, 10), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.REGENERATION)));
    		
    	}
    }
    
    public static class JediLevel3 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 30), new ItemStack(Items.BOOK, 20), new ItemStack(Items.TOTEM)));
    		
    	}
    }
    
    public static class SithLevel1 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		if(OreDictionary.getOres("gemRuby").size() > 0) {
    			for(int i = 0; i < OreDictionary.getOres("gemRuby").size(); i++) {
   					recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), OreDictionary.getOres("gemRuby").get(i), new ItemStack(LightsaberItems.red_lightsaber_hilt)));
   				}
    		}
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(LightsaberItems.darksaber_lens), new ItemStack(LightsaberItems.darksaber_hilt)));
    		 
    	}
    }
    
    public static class SithLevel2 implements ITradeList {

    	@Override
    	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
    		
    		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
    		
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 8), new ItemStack(Blocks.TNT, 12), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.FIRE_RESISTANCE)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 11), new ItemStack(Blocks.TNT, 15), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_INVISIBILITY)));
    		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 13), new ItemStack(Blocks.TNT, 13), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_STRENGTH)));
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
