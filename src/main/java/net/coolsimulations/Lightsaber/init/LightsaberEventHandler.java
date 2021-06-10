package net.coolsimulations.Lightsaber.init;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.coolsimulations.SurvivalPlus.api.events.EntityAccessor;
import net.coolsimulations.SurvivalPlus.api.events.SPClientPlayerJoinEvent;
import net.coolsimulations.SurvivalPlus.api.events.SPLivingAttackEvent;
import net.coolsimulations.SurvivalPlus.api.events.SPPlaySoundAtEntityEvent;
import net.coolsimulations.SurvivalPlus.api.events.SPPlayerJoinEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.advancement.Advancement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WetSpongeBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
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
		onLeftClick();
		onDamage();

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
					installInfo.getStyle().setColor(Formatting.GOLD);
					player.sendMessage(installInfo);

				}
			}

			if(LightsaberUpdateHandler.isOld == true && SPConfig.disableUpdateCheck == false) {
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						player.sendMessage(LightsaberUpdateHandler.updateInfo);
						player.sendMessage(LightsaberUpdateHandler.updateVersionInfo);
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
	
	public static void onLeftClick() {
		
		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			
			BlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			
			if(player.getStackInHand(hand).getItem() instanceof ItemLightsaber) {
				if(block instanceof WetSpongeBlock) {
					world.setBlockState(pos, Blocks.SPONGE.getDefaultState(), 3);
					return ActionResult.SUCCESS;
				}
			}
			return ActionResult.PASS;
		});
	}
	
	public static void onDamage() {
		SPLivingAttackEvent.EVENT.register((entity, source, amount) -> {
			
			if(entity instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) entity;
				
				if(player.getActiveItem().getItem() instanceof ItemLightsaber) {
					Item lightsaber = player.getActiveItem().getItem();
					
					if(lightsaber.getUseAction(player.getActiveItem()) == UseAction.BLOCK) {
						if(source == DamageSource.LIGHTNING_BOLT || source == DamageSource.FIREWORKS || source.getAttacker() instanceof GuardianEntity) {
							return ActionResult.FAIL;
						}
					}
				}
			}
			return ActionResult.PASS;
		});
	}

}
