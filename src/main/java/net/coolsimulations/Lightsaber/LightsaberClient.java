package net.coolsimulations.Lightsaber;

import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.fabricmc.api.ClientModInitializer;

public class LightsaberClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		
		LightsaberEventHandler.onPlayerJoinedServer();
	}

}
