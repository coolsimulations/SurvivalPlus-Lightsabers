package net.coolsimulations.Lightsaber;

import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.coolsimulations.SurvivalPlus.api.events.SPClientPlayerJoinEvent;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance.AttenuationType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;

public class LightsaberClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		
		SPClientPlayerJoinEvent.EVENT.register((manager, player, networkManager) -> {
			if(!SPConfig.disableClientAudio) {
				PositionedSoundInstance sound = new PositionedSoundInstance(LightsaberSoundHandler.hello_there.getId(), SoundCategory.VOICE, 0.25F, 1.0F, false, 0, AttenuationType.NONE, 0.0F, 0.0F, 0.0F, true);
				MinecraftClient.getInstance().getSoundManager().play(sound);
			}
			return ActionResult.PASS;
		});
	}

}
