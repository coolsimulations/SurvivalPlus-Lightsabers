package net.coolsimulations.Lightsaber;

import net.coolsimulations.Lightsaber.init.LightsaberDispenserBehavior;
import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.Lightsaber.init.LightsaberUpdateHandler;
import net.coolsimulations.SurvivalPlus.api.SPReference;
import net.fabricmc.api.ModInitializer;

public class Lightsaber implements ModInitializer {

	private static Lightsaber instance;

	public static Lightsaber getInstance()
	{
		return instance;
	}

	@Override
	public void onInitialize() {

		LightsaberEventHandler.init();
		SPReference.MOD_ADDON_NAMES.add("sp.lightsaber.name");
		LightsaberUpdateHandler.init();
		LightsaberItems.init();	
		LightsaberItems.register();
		LightsaberSoundHandler.init();
		LightsaberDispenserBehavior.init();
	}

}
