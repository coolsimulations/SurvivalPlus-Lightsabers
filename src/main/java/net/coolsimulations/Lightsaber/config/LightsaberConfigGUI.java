package net.coolsimulations.Lightsaber.config;

import java.util.Set;

import net.coolsimulations.Lightsaber.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

public class LightsaberConfigGUI implements IModGuiFactory {
	
	public static class LSConfigGUI extends GuiConfig
    {
        public LSConfigGUI(GuiScreen parent)
        {
            super(parent, LightsaberConfig.getConfigElements(), Reference.MOD_ID, false, false, I18n.translateToLocal("lightsaber.configgui.title"));
        }
    }

    @Override
    public void initialize(Minecraft minecraftInstance)
    {

    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

	public GuiScreen createConfigGui(GuiScreen arg0)
	{
		// TODO  Forge 2282 addition!
		return new LSConfigGUI(arg0);
	}

	public boolean hasConfigGui()
	{
		return true;
	}

}
