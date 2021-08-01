package net.coolsimulations.Lightsaber;

import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.coolsimulations.SurvivalPlus.api.events.SPClientPlayerJoinEvent;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance.Attenuation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;

public class LightsaberClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		
		SPClientPlayerJoinEvent.EVENT.register((manager, player, networkManager) -> {
			if(!SPConfig.disableClientAudio) {
				SimpleSoundInstance sound = new SimpleSoundInstance(LightsaberSoundHandler.hello_there.getLocation(), SoundSource.VOICE, 0.25F, 1.0F, false, 0, Attenuation.NONE, 0.0D, 0.0D, 0.0D, true);
				Minecraft.getInstance().getSoundManager().play(sound);
			}
			return InteractionResult.PASS;
		});
	}

}
