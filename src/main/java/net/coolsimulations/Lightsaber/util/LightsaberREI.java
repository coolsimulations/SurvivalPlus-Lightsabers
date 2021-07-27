package net.coolsimulations.Lightsaber.util;

import me.shedaniel.rei.api.EntryRegistry;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.util.version.VersionParsingException;
import net.minecraft.util.Identifier;

public class LightsaberREI implements REIPluginV0 {

	@Override
	public Identifier getPluginIdentifier() {
		return new Identifier(Reference.MOD_ID, "rer_plugin");
	}
	
	@Override
    public SemanticVersion getMinimumVersion() throws VersionParsingException {
        return SemanticVersion.parse("0.1.6");
    }
	
	@Override
    public void registerEntries(EntryRegistry entryRegistry) {
		for(EntryStack stack : entryRegistry.getStacksList()) {
			if(stack.getItem() instanceof ItemLightsaber) {
				entryRegistry.getStacksList().remove(stack);
			}
		}
	}

}
