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
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WetSpongeBlock;
import net.minecraft.world.level.block.state.BlockState;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class LightsaberEventHandler {

	public static void init() {

		onplayerLogin();
		onSoundPlay();
		onLeftClick();
		onDamage();

	}

	@Environment(EnvType.CLIENT)
	public static void onPlayerJoinedServer() {

		SPClientPlayerJoinEvent.EVENT.register((manager, player, networkManager) -> {
			Minecraft.getInstance().submit(new Runnable() {
				@Override
				public void run() {
					if(!SPConfig.disableSunAudio && Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.MASTER) != 0.0F && Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.VOICE) != 0.0F) {
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
			return InteractionResult.PASS;
		});
	}

	public static void onplayerLogin()
	{

		SPPlayerJoinEvent.EVENT.register((player, server) -> {

			CompoundTag entityData = ((EntityAccessor) player).getPersistentData();

			ServerAdvancementManager manager = server.getAdvancements();
			Advancement install = manager.getAdvancement(new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID + "/install"));

			boolean isDone = false;

			Timer timer = new Timer();

			if(install !=null && player.getAdvancements().getOrStartProgress(install).hasProgress()) {
				isDone = true;
			}

			if(!entityData.getBoolean("lightsaber.firstJoin") && !isDone && !SPConfig.disableThanks) {

				entityData.putBoolean("lightsaber.firstJoin", true);

				if(!player.level.isClientSide) {

					TranslatableComponent installInfo = new TranslatableComponent("advancements.lightsaber.install.display1");
					installInfo.withStyle(ChatFormatting.GOLD);
					player.sendMessage(installInfo, ChatType.SYSTEM, Util.NIL_UUID);

				}
			}

			if(LightsaberUpdateHandler.isOld == true && SPConfig.disableUpdateCheck == false) {
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						player.sendMessage(LightsaberUpdateHandler.updateInfo.setStyle(LightsaberUpdateHandler.updateInfo.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("sp.update.display2"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"))), ChatType.SYSTEM, Util.NIL_UUID);
						player.sendMessage(LightsaberUpdateHandler.updateVersionInfo.setStyle(LightsaberUpdateHandler.updateVersionInfo.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("sp.update.display2"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"))), ChatType.SYSTEM, Util.NIL_UUID);
					}
				}, 17000);

			}
			return InteractionResult.PASS;
		});
	}


	public static void onSoundPlay() {

		SPPlaySoundAtEntityEvent.EVENT.register((world, entity, pos, sound, category, volume, pitch) -> {

			if(sound == SoundEvents.SHIELD_BLOCK && entity != null) {
				if(entity instanceof LivingEntity && ((LivingEntity) entity).getUseItem().getItem() instanceof ItemLightsaber) {
					if(((LivingEntity) entity).getUseItem().getItem() == LightsaberItems.darksaber) {
						world.playSound((Player) entity, entity.blockPosition(), LightsaberSoundHandler.darksaber_hit, category, volume, pitch);
						return InteractionResult.FAIL;
					} else {
						world.playSound((Player) entity, entity.blockPosition(), LightsaberSoundHandler.lightsaber_hit, category, volume, pitch);
						return InteractionResult.FAIL;
					}
				}
			}
			return InteractionResult.PASS;
		});
	}
	
	public static void onLeftClick() {
		
		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			
			BlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			
			if(player.getItemInHand(hand).getItem() instanceof ItemLightsaber) {
				if(block instanceof WetSpongeBlock) {
					world.setBlock(pos, Blocks.SPONGE.defaultBlockState(), 3);
					return InteractionResult.SUCCESS;
				}
			}
			return InteractionResult.PASS;
		});
	}
	
	public static void onDamage() {
		SPLivingAttackEvent.EVENT.register((entity, source, amount) -> {
			
			if(entity instanceof Player) {
				Player player = (Player) entity;
				
				if(player.getUseItem().getItem() instanceof ItemLightsaber) {
					Item lightsaber = player.getUseItem().getItem();
					
					if(lightsaber.getUseAnimation(player.getUseItem()) == UseAnim.BLOCK) {
						if(source == DamageSource.LIGHTNING_BOLT || source.getDirectEntity() instanceof FireworkRocketEntity || source.getEntity() instanceof Guardian) {
							return InteractionResult.FAIL;
						}
					}
				}
			}
			return InteractionResult.PASS;
		});
	}

}
