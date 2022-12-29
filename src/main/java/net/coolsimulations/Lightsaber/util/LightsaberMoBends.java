package net.coolsimulations.Lightsaber.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import goblinbob.mobends.standard.main.ModConfig;
import net.coolsimulations.Lightsaber.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class LightsaberMoBends {
	
	public static Configuration config;
	
	public static void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(new File(event.getModConfigurationDirectory(), "mobends.cfg"));
	}
	
	public static void init() {
		String[] attack = config.getStringList("itemAttackClassifications", Configuration.CATEGORY_GENERAL, new String[0], null);
		List<String> includeAttack = new ArrayList<String>(Arrays.asList(attack));
		boolean hasAttack = false;
		
		String[] held = config.getStringList("itemUseClassifications", Configuration.CATEGORY_GENERAL, new String[0], null);
		List<String> includeHeld = new ArrayList<String>(Arrays.asList(held));
		boolean hasHeld = false;
		
		String nameAttack = "lightsaber:*saber=sword";
		for (String entry : includeAttack)
			if (entry.contains(Reference.MOD_ID))
				hasAttack = true;
		
		if(!hasAttack) {
			includeAttack.add(nameAttack);
			String[] newAttack = includeAttack.toArray(new String[0]);
			config.get(Configuration.CATEGORY_GENERAL, "itemAttackClassifications", new String[0]).set(newAttack);
			ModConfig.itemAttackClassifications = newAttack;
		}
		
		String nameHeld = "lightsaber:*saber=shield";
		for (String entry : includeHeld)
			if (entry.contains(Reference.MOD_ID))
				hasHeld = true;
				
		if(!hasHeld) {
			includeHeld.add(nameHeld);
			String[] newHeld = includeHeld.toArray(new String[0]);
			config.get(Configuration.CATEGORY_GENERAL, "itemUseClassifications", new String[0]).set(includeHeld.toArray(new String[0]));
			ModConfig.itemUseClassifications = newHeld;
		}
				
		config.save();
	}

}
