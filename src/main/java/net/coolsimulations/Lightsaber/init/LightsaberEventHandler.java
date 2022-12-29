package net.coolsimulations.Lightsaber.init;

import java.util.Timer;
import java.util.TimerTask;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.coolsimulations.SurvivalPlus.api.events.EntityAccessor;
import net.coolsimulations.SurvivalPlus.api.events.SPLivingAttackEvent;
import net.coolsimulations.SurvivalPlus.api.events.SPPlaySoundAtEntityEvent;
import net.coolsimulations.SurvivalPlus.api.events.SPPlayerJoinEvent;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.WetSpongeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

public class LightsaberEventHandler {

	public static void init() {

		onplayerLogin();
		onSoundPlay();
		onLeftClick();
		onDamage();

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

					MutableComponent installInfo = Component.translatable("advancements.lightsaber.install.display1");
					installInfo.withStyle(ChatFormatting.GOLD);
					player.sendSystemMessage(installInfo);

				}
			}

			if(LightsaberUpdateHandler.isOld == true && SPConfig.disableUpdateCheck == false) {
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						player.sendSystemMessage(LightsaberUpdateHandler.updateInfo.setStyle(LightsaberUpdateHandler.updateInfo.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("sp.update.display2"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"))));
						player.sendSystemMessage(LightsaberUpdateHandler.updateVersionInfo.setStyle(LightsaberUpdateHandler.updateVersionInfo.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("sp.update.display2"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"))));
					}
				}, 17000);

			}
			return InteractionResult.PASS;
		});
	}


	public static void onSoundPlay() {

		SPPlaySoundAtEntityEvent.EVENT.register((world, entity, pos, sound, category, volume, pitch, seed) -> {

			if(sound.value() == SoundEvents.SHIELD_BLOCK && entity != null) {
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
					world.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
					return InteractionResult.SUCCESS;
				} else if(CampfireBlock.canLight(state) || CandleBlock.canLight(state) || CandleCakeBlock.canLight(state)) {
					world.setBlock(pos, state.setValue(BlockStateProperties.LIT, true), 11);
					world.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
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
						if(source == DamageSource.LIGHTNING_BOLT || source.getDirectEntity() instanceof FireworkRocketEntity || source.getEntity() instanceof Guardian || source == DamageSource.DRAGON_BREATH) {
							return InteractionResult.FAIL;
						}
					}
				}
			}
			return InteractionResult.PASS;
		});
	}

}
