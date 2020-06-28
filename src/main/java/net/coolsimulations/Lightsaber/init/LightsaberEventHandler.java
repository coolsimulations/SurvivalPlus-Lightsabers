package net.coolsimulations.Lightsaber.init;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

@SuppressWarnings("restriction")
public class LightsaberEventHandler {

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onPlayerJoinedServer(ClientPlayerNetworkEvent.LoggedInEvent event) {
		Minecraft.getInstance().deferTask(new Runnable() {
			@Override
			public void run() {
				if(!SPConfig.disableSunAudio.get() && Minecraft.getInstance().gameSettings.getSoundLevel(SoundCategory.MASTER) != 0.0F && Minecraft.getInstance().gameSettings.getSoundLevel(SoundCategory.VOICE) != 0.0F) {
					try
					{
						InputStream sound = getClass().getClassLoader().getResourceAsStream("assets/" + Reference.MOD_ID + "/sounds/misc/hello_there.wav");
						AudioStream audioStream = new AudioStream(sound);
						AudioPlayer.player.start(audioStream);
					}
					catch (Exception e)
					{
						System.err.println(e);
					}
				}
			}
		});
	}

	@SubscribeEvent
	public void onplayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
		CompoundNBT entityData = player.getPersistentData();

		AdvancementManager manager = player.getServer().getAdvancementManager();
		Advancement install = manager.getAdvancement(new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID + "/install"));

		boolean isDone = false;
		
		Timer timer = new Timer();

		if(install !=null && player.getAdvancements().getProgress(install).hasProgress()) {
			isDone = true;
		}

		if(!entityData.getBoolean("lightsaber.firstJoin") && !isDone && SPConfig.disableThanks.get()) {

			entityData.putBoolean("lightsaber.firstJoin", true);

			if(!player.world.isRemote) {

				TranslationTextComponent installInfo = new TranslationTextComponent("advancements.lightsaber.install.display1");
				installInfo.func_240699_a_(TextFormatting.GOLD);
				player.func_241151_a_(installInfo, ChatType.SYSTEM, Util.field_240973_b_);

			}
		}

		if(LightsaberUpdateHandler.isOld == true && SPConfig.disableUpdateCheck.get() == false) {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					player.func_241151_a_(LightsaberUpdateHandler.updateInfo.func_240700_a_((style) -> {return style.func_240716_a_(new HoverEvent(HoverEvent.Action.field_230550_a_, new TranslationTextComponent("sp.update.display2"))).func_240715_a_(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers"));}), ChatType.SYSTEM, Util.field_240973_b_);
					player.func_241151_a_(LightsaberUpdateHandler.updateVersionInfo.func_240700_a_((style) -> {return style.func_240716_a_(new HoverEvent(HoverEvent.Action.field_230550_a_, new TranslationTextComponent("sp.update.display2"))).func_240715_a_(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers"));}), ChatType.SYSTEM, Util.field_240973_b_);

				}
			}, 17000);
		}

	}
	
	@SubscribeEvent
	public void onSoundPlay(PlaySoundAtEntityEvent event) {
		if(event.getSound() == SoundEvents.ITEM_SHIELD_BLOCK && event.getEntity() != null) {
			if(event.getEntity() instanceof LivingEntity && ((LivingEntity) event.getEntity()).getActiveItemStack().getItem() instanceof ItemLightsaber) {
				if(((LivingEntity) event.getEntity()).getActiveItemStack().getItem() == LightsaberItems.darksaber) {
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
		CompoundNBT tag = event.getItemStack().getItem().getShareTag(itemStackIn);
		PlayerEntity playerIn = event.getPlayer();
		World worldIn = event.getWorld();

		if(SPCompatibilityManager.isSwordBlockingLoaded()) {
			if(event.getItemStack().getItem() instanceof ItemLightsaber && event.getPlayer().isSneaking()) {
				ItemStack red = new ItemStack(LightsaberItems.red_lightsaber_hilt);
				red.setTag(tag);

				ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber_hilt);
				blue.setTag(tag);

				ItemStack green = new ItemStack(LightsaberItems.green_lightsaber_hilt);
				green.setTag(tag);

				ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber_hilt);
				purple.setTag(tag);

				ItemStack white = new ItemStack(LightsaberItems.white_lightsaber_hilt);
				white.setTag(tag);

				ItemStack dark = new ItemStack(LightsaberItems.darksaber_hilt);
				dark.setTag(tag);

				if(event.getItemStack().getItem() == LightsaberItems.red_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(Hand.OFF_HAND, red);
					}
					else
					{
						playerIn.setHeldItem(Hand.MAIN_HAND, red);
					}
					worldIn.playSound(playerIn, playerIn.func_233580_cy_(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.blue_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(Hand.OFF_HAND, blue);
					}
					else
					{
						playerIn.setHeldItem(Hand.MAIN_HAND, blue);
					}
					worldIn.playSound(playerIn, playerIn.func_233580_cy_(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.green_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(Hand.OFF_HAND, green);
					}
					else
					{
						playerIn.setHeldItem(Hand.MAIN_HAND, green);
					}
					worldIn.playSound(playerIn, playerIn.func_233580_cy_(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.purple_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(Hand.OFF_HAND, purple);
					}
					else
					{
						playerIn.setHeldItem(Hand.MAIN_HAND, purple);
					}
					worldIn.playSound(playerIn, playerIn.func_233580_cy_(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.white_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(Hand.OFF_HAND, white);
					}
					else
					{
						playerIn.setHeldItem(Hand.MAIN_HAND, white);
					}
					worldIn.playSound(playerIn, playerIn.func_233580_cy_(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.darksaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(Hand.OFF_HAND, dark);
					}
					else
					{
						playerIn.setHeldItem(Hand.MAIN_HAND, dark);
					}
					worldIn.playSound(playerIn, playerIn.func_233580_cy_(), LightsaberSoundHandler.darksaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
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
