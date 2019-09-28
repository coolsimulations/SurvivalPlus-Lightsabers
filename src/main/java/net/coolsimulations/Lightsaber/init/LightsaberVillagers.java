package net.coolsimulations.Lightsaber.init;

/**import java.lang.reflect.Method;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.coolsimulations.Lightsaber.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;**/

public class LightsaberVillagers {
	
	/**public static VillagerProfession villagerJedi;
	public static VillagerProfession villagerSith;
	
	public static PointOfInterestType holocron;
	
	public static void registerVillagers(IForgeRegistry<VillagerProfession> registry) {
    	IForgeRegistry<VillagerProfession> villagerProfessions = ForgeRegistries.PROFESSIONS;
    	
		//villagerJedi = new VillagerProfession(Reference.MOD_ID + ":jedi", Reference.MOD_ID + ":textures/entity/jedi.png", Reference.MOD_ID + ":textures/entity/zombie_jedi.png");
    		villagerJedi = new VillagerProfession("jedi", holocron, ImmutableSet.of(), ImmutableSet.of()).setRegistryName(Reference.MOD_ID, "jedi");
    		registry.register(villagerJedi);
		
		//villagerSith = new VillagerProfession(Reference.MOD_ID + ":sith", Reference.MOD_ID + ":textures/entity/sith.png", Reference.MOD_ID + ":textures/entity/zombie_sith.png");
		//villagerProfessions.register(villagerSith);**/
		
		/**VillagerCareer sithCareer = new VillagerCareer(villagerSith, "sith");
		sithCareer.addTrade(1, new SithLevel1());
		sithCareer.addTrade(2, new SithLevel2());
		sithCareer.addTrade(3, new SithLevel3());**/

    /**}
	
	public static void registerPointOfIntrestTypes(IForgeRegistry<PointOfInterestType> registry){
        
		holocron = new PointOfInterestType("holocron", getAllStates(LightsaberBlocks.holocron), 1, (SoundEvent)null, 1).setRegistryName(Reference.MOD_ID, "holocron");
		registry.register(holocron);
		
		try {
			  Method func_221052_a = ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class);
			  func_221052_a.invoke(null, holocron);
			} catch (Exception e) {
			  e.printStackTrace();
			}
		
    }
	
	public static Set<BlockState> getAllStates(Block block) {
        return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
    }

    /**public static class SithLevel1 implements ITradeList {

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
    }**/

}
