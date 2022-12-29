package net.coolsimulations.Lightsaber.proxy;

import net.coolsimulations.Lightsaber.init.LightsaberBlocks;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class ClientProxy implements CommonProxy{
	@Override
	public void init(){
		LightsaberBlocks.registerRenders();
		LightsaberItems.registerRenders();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onPlayerJoinedServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		if(!SPConfig.disableClientAudio) {
			PositionedSoundRecord sound = new PositionedSoundRecord(LightsaberSoundHandler.hello_there.getRegistryName(), SoundCategory.VOICE, 0.25F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
			Minecraft.getMinecraft().getSoundHandler().playSound(sound);
		}
	}

}
