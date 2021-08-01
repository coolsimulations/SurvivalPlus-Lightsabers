package net.coolsimulations.Lightsaber.proxy;

import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientProxy extends CommonProxy{
	@Override
	public void init(){
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onPlayerJoinedServer(ClientPlayerNetworkEvent.LoggedInEvent event) {
		if(!SPConfig.disableClientAudio.get()) {
			SimpleSound sound = new SimpleSound(LightsaberSoundHandler.hello_there.getRegistryName(), SoundCategory.VOICE, 0.25F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F, true);
			Minecraft.getInstance().getSoundHandler().play(sound);
		}
	}

}
