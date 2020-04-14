package net.coolsimulations.Lightsaber.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fuzs.swordblockingcombat.config.ConfigBuildHandler;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class LightsaberSwordBlocking {

	public static void init() {

		List<String> include = new ArrayList<String>(Arrays.asList(ConfigBuildHandler.swordBlockingConfig.include));
		List<String> exclude = Arrays.asList(ConfigBuildHandler.swordBlockingConfig.exclude);

		for(ResourceLocation location : Item.REGISTRY.getKeys()) {
			Item item = Item.REGISTRY.getObject(location);
			String name = item.getRegistryName().toString();
			if(item instanceof ItemLightsaber && !include.contains(name) && !exclude.contains(name))
				include.add(name);
		}

		ConfigBuildHandler.swordBlockingConfig.include = include.toArray(new String[0]);
	}

}
