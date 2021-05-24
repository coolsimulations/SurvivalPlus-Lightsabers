package net.coolsimulations.Lightsaber.init;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.coolsimulations.SurvivalPlus.api.events.EntityAccessor;
import net.coolsimulations.SurvivalPlus.api.events.SPClientPlayerJoinEvent;
import net.coolsimulations.SurvivalPlus.api.events.SPPlaySoundAtEntityEvent;
import net.coolsimulations.SurvivalPlus.api.events.SPPlayerJoinEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.advancement.Advancement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.MessageType;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class LightsaberEventHandler {

	public static void init() {

		ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
			if(!server.isRemote())
				onPlayerJoinedServer();
		});
		onplayerLogin();
		onSoundPlay();

	}

	@Environment(EnvType.CLIENT)
	public static void onPlayerJoinedServer() {

		SPClientPlayerJoinEvent.EVENT.register((manager, player, networkManager) -> {
			MinecraftClient.getInstance().submit(new Runnable() {
				@Override
				public void run() {
					if(!SPConfig.disableSunAudio && MinecraftClient.getInstance().options.getSoundVolume(SoundCategory.MASTER) != 0.0F && MinecraftClient.getInstance().options.getSoundVolume(SoundCategory.VOICE) != 0.0F) {
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
			return ActionResult.PASS;
		});
	}

	public static void onplayerLogin()
	{

		SPPlayerJoinEvent.EVENT.register((player, server) -> {

			CompoundTag entityData = ((EntityAccessor) player).getPersistentData();

			ServerAdvancementLoader manager = server.getAdvancementLoader();
			Advancement install = manager.get(new Identifier(Reference.MOD_ID, Reference.MOD_ID + "/install"));

			boolean isDone = false;

			Timer timer = new Timer();

			if(install !=null && player.getAdvancementTracker().getProgress(install).isAnyObtained()) {
				isDone = true;
			}

			if(!entityData.getBoolean("lightsaber.firstJoin") && !isDone && !SPConfig.disableThanks) {

				entityData.putBoolean("lightsaber.firstJoin", true);

				if(!player.world.isClient) {

					TranslatableText installInfo = new TranslatableText("advancements.lightsaber.install.display1");
					installInfo.formatted(Formatting.GOLD);
					player.sendMessage(installInfo, MessageType.SYSTEM, Util.NIL_UUID);

				}
			}

			if(LightsaberUpdateHandler.isOld == true && SPConfig.disableUpdateCheck == false) {
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						player.sendMessage(LightsaberUpdateHandler.updateInfo.setStyle(LightsaberUpdateHandler.updateInfo.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("sp.update.display2"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"))), MessageType.SYSTEM, Util.NIL_UUID);
						player.sendMessage(LightsaberUpdateHandler.updateVersionInfo.setStyle(LightsaberUpdateHandler.updateVersionInfo.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("sp.update.display2"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"))), MessageType.SYSTEM, Util.NIL_UUID);
					}
				}, 17000);

			}
			return ActionResult.PASS;
		});
	}


	public static void onSoundPlay() {

		SPPlaySoundAtEntityEvent.EVENT.register((world, entity, pos, sound, category, volume, pitch) -> {

			if(sound == SoundEvents.ITEM_SHIELD_BLOCK && entity != null) {
				if(entity instanceof LivingEntity && ((LivingEntity) entity).getActiveItem().getItem() instanceof ItemLightsaber) {
					if(((LivingEntity) entity).getActiveItem().getItem() == LightsaberItems.darksaber) {
						world.playSound((PlayerEntity) entity, entity.getBlockPos(), LightsaberSoundHandler.darksaber_hit, category, volume, pitch);
						return ActionResult.FAIL;
					} else {
						world.playSound((PlayerEntity) entity, entity.getBlockPos(), LightsaberSoundHandler.lightsaber_hit, category, volume, pitch);
						return ActionResult.FAIL;
					}
				}
			}
			return ActionResult.PASS;
		});
	}

}
