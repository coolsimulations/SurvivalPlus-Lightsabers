package net.coolsimulations.Lightsaber.config;

import java.util.function.Function;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.coolsimulations.Lightsaber.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;

@Environment(EnvType.CLIENT)
public class LSConfigGUI implements ModMenuApi {

    @Override
    public String getModId() {
        return Reference.MOD_ID;
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return screen -> LightsaberConfigGUI.getConfigScreen(screen);
    }
}

