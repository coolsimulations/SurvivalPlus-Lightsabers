package net.coolsimulations.Lightsaber.util;

import java.util.ArrayList;
import java.util.List;

import com.fuzs.swordblockingcombat.config.ConfigBuildHandler;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class LightsaberSwordBlocking {

	public static void init() {

		List<String> include = new ArrayList<String>(ConfigBuildHandler.BLOCKING_INCLUDE.get());
		List<String> exclude = new ArrayList<String>(ConfigBuildHandler.BLOCKING_EXCLUDE.get());

		for(ResourceLocation location : ForgeRegistries.ITEMS.getKeys()) {
			Item item = ForgeRegistries.ITEMS.getValue(location);
			String name = item.getRegistryName().toString();
			if(item instanceof ItemLightsaber && !include.contains(name) && !exclude.contains(name))
				//include.add(name);
				ConfigBuildHandler.BLOCKING_INCLUDE.get().add(name);
		}
	}

}
