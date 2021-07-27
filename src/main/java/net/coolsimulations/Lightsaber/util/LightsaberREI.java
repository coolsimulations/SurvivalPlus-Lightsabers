package net.coolsimulations.Lightsaber.util;

import me.shedaniel.rei.api.EntryRegistry;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.util.version.VersionParsingException;
import net.minecraft.resources.ResourceLocation;

public class LightsaberREI implements REIPluginV0 {

	@Override
	public ResourceLocation getPluginIdentifier() {
		return new ResourceLocation(Reference.MOD_ID, "rer_plugin");
	}
	
	@Override
    public SemanticVersion getMinimumVersion() throws VersionParsingException {
        return SemanticVersion.parse("0.1.6");
    }
	
	@Override
    public void postRegister() {
		
		EntryRegistry.getInstance().getEntryStacks().filter(stack->stack.getItem() instanceof ItemLightsaber).forEach(stack->EntryRegistry.getInstance().removeEntry(stack));
    }

}
