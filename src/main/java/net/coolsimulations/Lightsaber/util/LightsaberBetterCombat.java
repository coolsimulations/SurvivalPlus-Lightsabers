package net.coolsimulations.Lightsaber.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bettercombat.mod.util.ConfigurationHandler;

public class LightsaberBetterCombat {

	public static void init() {

		String[] itemClassWhitelist = new String[]{"net.minecraft.item.ItemSword",
				"net.minecraft.item.ItemAxe", "net.minecraft.item.ItemSpade", "net.minecraft.item.ItemPickaxe",
				"net.minecraft.item.ItemHoe", "slimeknights.tconstruct.library.tools.SwordCore",
		"slimeknights.tconstruct.library.tools.AoeToolCore"};

		List<String> include = new ArrayList<String>(Arrays.asList(ConfigurationHandler.config.getStringList("Item Class Whitelist", "general", itemClassWhitelist, "Whitelisted item classes for attacking.")));
		
		String name = "net.coolsimulations.Lightsaber.item.ItemLightsaber";
		if(!include.contains(name)) {
			include.add(name);
		}

		ConfigurationHandler.config.get("general", "Item Class Whitelist", itemClassWhitelist, "Whitelisted item classes for attacking.").set(include.toArray(new String[0]));
		ConfigurationHandler.config.save();
	}
}
