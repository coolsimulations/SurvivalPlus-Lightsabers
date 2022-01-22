package net.coolsimulations.Lightsaber;

import java.io.File;

import net.coolsimulations.Lightsaber.config.LightsaberConfig;
import net.coolsimulations.Lightsaber.init.LightsaberDispenserBehavior;
import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.Lightsaber.init.LightsaberUpdateHandler;
import net.coolsimulations.SurvivalPlus.api.SPReference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

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
		LightsaberConfig.init(new File(FabricLoader.getInstance().getConfigDir().toFile(), Reference.MOD_ID + ".json"));
		LightsaberUpdateHandler.init();
		LightsaberItems.init();	
		LightsaberItems.register();
		LightsaberSoundHandler.init();
		LightsaberDispenserBehavior.init();
	}

}
