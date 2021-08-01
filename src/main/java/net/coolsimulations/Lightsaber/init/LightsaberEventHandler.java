package net.coolsimulations.Lightsaber.init;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.advancements.Advancement;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WetSpongeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LightsaberEventHandler {

	@SubscribeEvent
	public void onplayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		ServerPlayer player = (ServerPlayer) event.getPlayer();
		CompoundTag entityData = player.getPersistentData();

		ServerAdvancementManager manager = player.getServer().getAdvancements();
		Advancement install = manager.getAdvancement(new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID + "/install"));

		boolean isDone = false;

		Timer timer = new Timer();

		if(install !=null && player.getAdvancements().getOrStartProgress(install).hasProgress()) {
			isDone = true;
		}

		if(!entityData.getBoolean("lightsaber.firstJoin") && !isDone && SPConfig.disableThanks.get()) {

			entityData.putBoolean("lightsaber.firstJoin", true);

			if(!player.level.isClientSide) {

				TranslatableComponent installInfo = new TranslatableComponent("advancements.lightsaber.install.display1");
				installInfo.withStyle(ChatFormatting.GOLD);
				player.sendMessage(installInfo, ChatType.SYSTEM, Util.NIL_UUID);

			}
		}

		if(LightsaberUpdateHandler.isOld == true && SPConfig.disableUpdateCheck.get() == false) {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					player.sendMessage(LightsaberUpdateHandler.updateInfo.withStyle((style) -> {return style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("sp.update.display2"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers"));}), ChatType.SYSTEM, Util.NIL_UUID);
					player.sendMessage(LightsaberUpdateHandler.updateVersionInfo.withStyle((style) -> {return style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("sp.update.display2"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers"));}), ChatType.SYSTEM, Util.NIL_UUID);

				}
			}, 17000);
		}

	}

	@SubscribeEvent
	public void onSoundPlay(PlaySoundAtEntityEvent event) {
		if(event.getSound() == SoundEvents.SHIELD_BLOCK && event.getEntity() != null) {
			if(event.getEntity() instanceof LivingEntity && ((LivingEntity) event.getEntity()).getUseItem().getItem() instanceof ItemLightsaber) {
				if(((LivingEntity) event.getEntity()).getUseItem().getItem() == LightsaberItems.darksaber) {
					event.setSound(LightsaberSoundHandler.darksaber_hit);
				} else {
					event.setSound(LightsaberSoundHandler.lightsaber_hit);
				}
			}
		}
	}

	@SubscribeEvent
	public void onRightClick(RightClickItem event) {
		ItemStack itemStackIn = event.getItemStack();
		CompoundTag tag = event.getItemStack().getItem().getShareTag(itemStackIn);
		Player playerIn = event.getPlayer();
		Level worldIn = event.getWorld();

		if(SPCompatibilityManager.isSwordBlockingLoaded()) {
			if(event.getItemStack().getItem() instanceof ItemLightsaber && event.getPlayer().isCrouching()) {
				ItemStack red = new ItemStack(LightsaberItems.red_lightsaber_hilt);
				red.setTag(tag);

				ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber_hilt);
				blue.setTag(tag);

				ItemStack green = new ItemStack(LightsaberItems.green_lightsaber_hilt);
				green.setTag(tag);

				ItemStack yellow = new ItemStack(LightsaberItems.yellow_lightsaber_hilt);
				yellow.setTag(tag);

				ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber_hilt);
				purple.setTag(tag);

				ItemStack white = new ItemStack(LightsaberItems.white_lightsaber_hilt);
				white.setTag(tag);

				ItemStack dark = new ItemStack(LightsaberItems.darksaber_hilt);
				dark.setTag(tag);

				if(event.getItemStack().getItem() == LightsaberItems.red_lightsaber){

					if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
					{
						playerIn.setItemInHand(InteractionHand.OFF_HAND, red);
					}
					else
					{
						playerIn.setItemInHand(InteractionHand.MAIN_HAND, red);
					}
					worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.blue_lightsaber){

					if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
					{
						playerIn.setItemInHand(InteractionHand.OFF_HAND, blue);
					}
					else
					{
						playerIn.setItemInHand(InteractionHand.MAIN_HAND, blue);
					}
					worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.green_lightsaber){

					if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
					{
						playerIn.setItemInHand(InteractionHand.OFF_HAND, green);
					}
					else
					{
						playerIn.setItemInHand(InteractionHand.MAIN_HAND, green);
					}
					worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.yellow_lightsaber){

					if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
					{
						playerIn.setItemInHand(InteractionHand.OFF_HAND, yellow);
					}
					else
					{
						playerIn.setItemInHand(InteractionHand.MAIN_HAND, yellow);
					}
					worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.purple_lightsaber){

					if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
					{
						playerIn.setItemInHand(InteractionHand.OFF_HAND, purple);
					}
					else
					{
						playerIn.setItemInHand(InteractionHand.MAIN_HAND, purple);
					}
					worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.white_lightsaber){

					if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
					{
						playerIn.setItemInHand(InteractionHand.OFF_HAND, white);
					}
					else
					{
						playerIn.setItemInHand(InteractionHand.MAIN_HAND, white);
					}
					worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.darksaber){

					if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
					{
						playerIn.setItemInHand(InteractionHand.OFF_HAND, dark);
					}
					else
					{
						playerIn.setItemInHand(InteractionHand.MAIN_HAND, dark);
					}
					worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.darksaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLeftClick(LeftClickBlock event) {

		BlockState state = event.getWorld().getBlockState(event.getPos());
		Block block = state.getBlock();

		if(event.getItemStack().getItem() instanceof ItemLightsaber) {
			if(block instanceof WetSpongeBlock) {
				event.getWorld().setBlock(event.getPos(), Blocks.SPONGE.defaultBlockState(), 3);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onEntityDeath(LivingDropsEvent event) {
		if(event.getSource().getEntity() instanceof Player) {
			Player player = (Player) event.getSource().getEntity();
			if(player.getMainHandItem().getItem() instanceof ItemLightsaber) {
				List<ItemEntity> newList = new ArrayList<ItemEntity>();

				for(Iterator<ItemEntity> iter = event.getDrops().iterator(); iter.hasNext();) {
					newList.add(iter.next());
				}

				for(int i = 0; i < newList.size(); i++) {
					ItemStack itemstack = newList.get(i).getItem();
					int count = itemstack.getCount();

					List<SmokingRecipe> recipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.SMOKING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

					if(recipe.isEmpty()) {
						for(SmokingRecipe smokeingList : recipe) {
							ItemStack result = smokeingList.getResultItem();
							result.setCount(count);
							event.getDrops().remove(newList.get(i));
							event.getDrops().add(new ItemEntity(event.getEntity().getCommandSenderWorld(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), result));
						}
					} else {

						List<CampfireCookingRecipe> campfireRecipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.CAMPFIRE_COOKING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

						if(campfireRecipe.isEmpty()) {
							for(CampfireCookingRecipe campfireList : campfireRecipe) {
								ItemStack result = campfireList.getResultItem();
								result.setCount(count);
								event.getDrops().remove(newList.get(i));
								event.getDrops().add(new ItemEntity(event.getEntity().getCommandSenderWorld(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), result));
							}
						} else {

							List<SmeltingRecipe> furnaceRecipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.SMELTING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

							if(furnaceRecipe.isEmpty()) {
								for(SmeltingRecipe furnaceList : furnaceRecipe) {
									ItemStack result = furnaceList.getResultItem();
									result.setCount(count);
									if(result.getItem().isEdible()) {
										event.getDrops().remove(newList.get(i));
										event.getDrops().add(new ItemEntity(event.getEntity().getCommandSenderWorld(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), result));
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onDamage(LivingAttackEvent event) {

		if(event.getEntityLiving() instanceof Player) {
			Player player = (Player) event.getEntityLiving();

			if(player.getUseItem().getItem() instanceof ItemLightsaber) {
				Item lightsaber = player.getUseItem().getItem();

				if(lightsaber.getUseAnimation(player.getUseItem()) == UseAnim.BLOCK) {
					if(event.getSource() == DamageSource.LIGHTNING_BOLT || event.getSource().getDirectEntity() instanceof FireworkRocketEntity || event.getSource().getEntity() instanceof Guardian) {
						event.setCanceled(true);
					}
				}
			}
		}
	}

	/**@SubscribeEvent
    public static void onPointsOfInterestTypeRegistry(final RegistryEvent.Register<PointOfInterestType> event){
        LightsaberVillagers.registerPointOfIntrestTypes(event.getRegistry());
    }

    @SubscribeEvent
    public static void onVillagerProfessionRegistry(final RegistryEvent.Register<VillagerProfession> event){
        LightsaberVillagers.registerVillagers(event.getRegistry());
    }

    @SubscribeEvent
	public void villagerTrades(VillagerTradesEvent event) {
		Int2ObjectMap<List<ITrade>> trades = event.getTrades();
		List<ITrade> jediLevel1 = new ArrayList<>();
		List<ITrade> jediLevel2 = new ArrayList<>();
		List<ITrade> jediLevel3 = new ArrayList<>();
		List<ITrade> jediLevel4 = new ArrayList<>();
		List<ITrade> jediLevel5 = new ArrayList<>();

		for(Iterator<Item> iter = SPTags.Items.GEMS_AMETHYST.getAllElements().iterator(); iter.hasNext(); ) {
			jediLevel1.add(new BasicTrade(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.blue_lightsaber_hilt), 10, 2, 0.05F));
		}

		for(Iterator<Item> iter = SPTags.Items.GEMS_TOPAZ.getAllElements().iterator(); iter.hasNext(); ) {
			jediLevel1.add(new BasicTrade(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.green_lightsaber_hilt), 10, 2, 0.05F));
		}

		for(Iterator<Item> iter = SPTags.Items.GEMS_PEARL.getAllElements().iterator(); iter.hasNext(); ) {
			jediLevel1.add(new BasicTrade(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.white_lightsaber_hilt), 10, 2, 0.005F));
		}

		for(Iterator<Item> iter = SPTags.Items.GEMS_SAPPHIRE.getAllElements().iterator(); iter.hasNext(); ) {
			jediLevel1.add(new BasicTrade(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(iter.next()), new ItemStack(LightsaberItems.purple_lightsaber_hilt), 10, 2, 0.05F));
		}

		if (SPCompatibilityManager.isGCLoaded())
        {
			//recipeList.add(new MerchantRecipe(new ItemStack(LightsaberItems.lightsaber_hilt), new ItemStack(GCItems.itemBasicMoon, 1, 2), new ItemStack(LightsaberItems.purple_lightsaber_hilt)));
        }

		jediLevel2.add(new BasicTrade(new ItemStack(Items.EMERALD, 13), new ItemStack(Items.BOOK, 7), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.SWIFTNESS), 16, 5, 0.2F));
		jediLevel2.add(new BasicTrade(new ItemStack(Items.EMERALD, 19), new ItemStack(Items.BOOK, 12), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LONG_LEAPING), 16, 5, 0.2F));

		jediLevel3.add(new BasicTrade(new ItemStack(Items.EMERALD, 21), new ItemStack(Items.BOOK, 14), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), Potions.HEALING), 16, 10, 0.3F));
		jediLevel3.add(new BasicTrade(new ItemStack(Items.EMERALD, 24), new ItemStack(Items.BOOK, 10), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), Potions.REGENERATION), 16, 10, 0.3F));

		jediLevel4.add(new BasicTrade(new ItemStack(Items.EMERALD, 25), new ItemStack(Items.BOOK, 15), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), 16, 15, 0.3F));

		jediLevel5.add(new BasicTrade(new ItemStack(Items.EMERALD, 30), new ItemStack(Items.BOOK, 20), new ItemStack(Items.TOTEM_OF_UNDYING), 16, 20, 0.4F));

		if(event.getType() == LightsaberVillagers.villagerJedi) {
			trades.put(1, jediLevel1);
			trades.put(2, jediLevel2);
			trades.put(3, jediLevel3);
			trades.put(4, jediLevel4);
			trades.put(5, jediLevel5);
		}
    }**/

	/**@SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
		IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();

		if(!SPCompatibilityManager.isIc2Loaded()) { //Removes IC2 Recipes

			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "pommel_cap_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "emitter_matrix_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "blade_emitter_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "darksaber_lens_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "focusing_lens_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "inert_power_insulator_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "activation_stud_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "lightsaber_hilt_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "lightsaber_hilt_ic2_alt"));

		}

		if(!SPCompatibilityManager.isGCLoaded()) { //Removes GC Recipes

			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "pommel_cap_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "emitter_matrix_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "blade_emitter_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "focusing_lens_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "inert_power_insulator_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "activation_stud_gc"));
        	modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "darksaber_lens_gc"));
        	modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "lightsaber_hilt_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "lightsaber_hilt_gc_alt"));
		}

		if(SPCompatibilityManager.isIc2Loaded() && SPCompatibilityManager.isGCLoaded()) { //Removes the GC and IC2 Recipes that are replaced with the combined ones 

			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "pommel_cap_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "emitter_matrix_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "blade_emitter_ic2"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "darksaber_lens_ic2"));

			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "pommel_cap_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "emitter_matrix_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "blade_emitter_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "focusing_lens_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "inert_power_insulator_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "activation_stud_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "darksaber_lens_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "lightsaber_hilt_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "lightsaber_hilt_gc_alt"));
		}

		if(!SPCompatibilityManager.isIc2Loaded() && !SPCompatibilityManager.isGCLoaded()) { //Removes the combined recipes

			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "pommel_cap_ic2_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "emitter_matrix_ic2_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "blade_emitter_ic2_gc"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "darksaber_lens_ic2_gc"));
		}

		if(SPCompatibilityManager.isIc2Loaded() || SPCompatibilityManager.isGCLoaded()) { //Removes vanilla recipes

			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "pommel_cap"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "emitter_matrix"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "blade_emitter"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "focusing_lens"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "inert_power_insulator"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "activation_stud"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "darksaber_lens"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "lightsaber_hilt"));
			modRegistry.remove(new ResourceLocation(Reference.MOD_ID + ":" + "lightsaber_hilt_alt"));

		}
    }**/


}
