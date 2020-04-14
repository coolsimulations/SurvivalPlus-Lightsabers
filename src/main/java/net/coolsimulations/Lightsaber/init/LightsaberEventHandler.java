package net.coolsimulations.Lightsaber.init;

import java.io.InputStream;

import net.coolsimulations.Lightsaber.Lightsaber;
import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

@SuppressWarnings("restriction")
public class LightsaberEventHandler {

	@SubscribeEvent
	public void onPlayerJoinedServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		Minecraft.getMinecraft().addScheduledTask(new Runnable() {
			@Override
			public void run() {
				if(!SPConfig.disableSunAudio) {
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
	public void onplayerLogin(PlayerLoggedInEvent event)
	{
		EntityPlayerMP player = (EntityPlayerMP) event.player;
		NBTTagCompound entityData = player.getEntityData();

		AdvancementManager manager = player.getServer().getAdvancementManager();
		Advancement install = manager.getAdvancement(new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID + "/install"));

		boolean isDone = false;

		if(install !=null && player.getAdvancements().getProgress(install).hasProgress()) {
			isDone = true;
		}


		if(!entityData.getBoolean("lightsaber.firstJoin") && !isDone && !SPConfig.disableThanks) {

			entityData.setBoolean("lightsaber.firstJoin", true);

			if(!player.world.isRemote) {

				TextComponentTranslation installInfo = new TextComponentTranslation("advancements.lightsaber.install.display1");
				installInfo.getStyle().setColor(TextFormatting.GOLD);
				player.sendMessage(installInfo);

			}
		}

		if(LightsaberUpdateHandler.isOld == true && SPConfig.disableUpdateCheck == false) {
			player.sendMessage(LightsaberUpdateHandler.updateInfo);
			player.sendMessage(LightsaberUpdateHandler.updateVersionInfo);
		}
	}

	@SubscribeEvent
	public void onSoundPlay(PlaySoundAtEntityEvent event) {
		if(event.getSound() == SoundEvents.ITEM_SHIELD_BLOCK && event.getEntity() != null) {
			if(event.getEntity() instanceof EntityLivingBase && ((EntityLivingBase) event.getEntity()).getActiveItemStack().getItem() instanceof ItemLightsaber) {
				if(((EntityLivingBase) event.getEntity()).getActiveItemStack().getItem() == LightsaberItems.darksaber) {
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
		NBTTagCompound tag = event.getItemStack().getItem().getNBTShareTag(itemStackIn);
		EntityPlayer playerIn = event.getEntityPlayer();
		World worldIn = event.getWorld();

		if(SPCompatibilityManager.isSwordBlockingLoaded()) {
			if(event.getItemStack().getItem() instanceof ItemLightsaber && event.getEntityPlayer().isSneaking()) {
				ItemStack red = new ItemStack(LightsaberItems.red_lightsaber_hilt);
				red.setTagCompound(tag);

				ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber_hilt);
				blue.setTagCompound(tag);

				ItemStack green = new ItemStack(LightsaberItems.green_lightsaber_hilt);
				green.setTagCompound(tag);

				ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber_hilt);
				purple.setTagCompound(tag);

				ItemStack white = new ItemStack(LightsaberItems.white_lightsaber_hilt);
				white.setTagCompound(tag);

				ItemStack dark = new ItemStack(LightsaberItems.darksaber_hilt);
				dark.setTagCompound(tag);

				if(event.getItemStack().getItem() == LightsaberItems.red_lightsaber){
					System.out.print("ASDF Reached Red");

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(EnumHand.OFF_HAND, red);
					}
					else
					{
						playerIn.setHeldItem(EnumHand.MAIN_HAND, red);
					}
					worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.blue_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(EnumHand.OFF_HAND, blue);
					}
					else
					{
						playerIn.setHeldItem(EnumHand.MAIN_HAND, blue);
					}
					worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.green_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(EnumHand.OFF_HAND, green);
					}
					else
					{
						playerIn.setHeldItem(EnumHand.MAIN_HAND, green);
					}
					worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.purple_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(EnumHand.OFF_HAND, purple);
					}
					else
					{
						playerIn.setHeldItem(EnumHand.MAIN_HAND, purple);
					}
					worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.white_lightsaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(EnumHand.OFF_HAND, white);
					}
					else
					{
						playerIn.setHeldItem(EnumHand.MAIN_HAND, white);
					}
					worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
				if(event.getItemStack().getItem() == LightsaberItems.darksaber){

					if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
					{
						playerIn.setHeldItem(EnumHand.OFF_HAND, dark);
					}
					else
					{
						playerIn.setHeldItem(EnumHand.MAIN_HAND, dark);
					}
					worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.darksaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
			}
		}
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event)
	{
		LightsaberItems.registerItems(event.getRegistry());
	}

	@SubscribeEvent
	public void onModelRegistry(ModelRegistryEvent event)
	{
		for(Item item : Lightsaber.ITEMS) {
			LightsaberItems.registerRenders();
		}
	}

	@SubscribeEvent
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
	}


}
