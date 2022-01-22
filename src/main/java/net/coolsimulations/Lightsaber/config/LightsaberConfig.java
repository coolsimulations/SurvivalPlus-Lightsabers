package net.coolsimulations.Lightsaber.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import net.coolsimulations.Lightsaber.Reference;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.relauncher.FMLRelaunchLog;

public class LightsaberConfig {
	
	public static double generalLightsaberDamage;
	public static double generalLightsaberSpeed;
	
	public static double darksaberDamage;
	public static double darksaberSpeed;
	
	public static double whiteLightsaberDamage;
	public static double whiteLightsaberSpeed;
	
	public static double purpleLightsaberDamage;
	public static double purpleLightsaberSpeed;

	static Configuration config;

	public static void init(File file)
	{
		config = new Configuration(file);
		syncConfig(true);
	}

	public static void forceSave()
	{
		config.save();
	}

	public static void syncConfig(boolean load)
	{
		List<String> propOrder = new ArrayList<String>();

		try
		{
			Property prop;

			if (!config.isChild)
			{
				if (load)
				{
					config.load();
				}
			}


			prop = config.get(Reference.CONFIG_CATEGORY_VALUES, "General Lightsaber Damage", 20.0D);
			prop.setComment("The Attack Damage of Red, Blue, Green, and Yellow Lightsabers.");
			prop.setLanguageKey("lightsaber.configgui.general_lightsaber_damage");
			generalLightsaberDamage = prop.getDouble(20.0D);
			propOrder.add(prop.getName());
			
			prop = config.get(Reference.CONFIG_CATEGORY_VALUES, "General Lightsaber Speed", 2.8D);
			prop.setComment("The Attack Speed of Red, Blue, Green, and Yellow Lightsabers.");
			prop.setLanguageKey("lightsaber.configgui.general_lightsaber_speed");
			generalLightsaberSpeed = prop.getDouble(2.8D);
			
			prop = config.get(Reference.CONFIG_CATEGORY_VALUES, "Darksaber Damage", 30.0D);
			prop.setComment("The Attack Damage of Darksabers.");
			prop.setLanguageKey("lightsaber.configgui.darksaber_damage");
			darksaberDamage = prop.getDouble(30.0D);
			propOrder.add(prop.getName());
			
			prop = config.get(Reference.CONFIG_CATEGORY_VALUES, "Darksaber Speed", 2.8D);
			prop.setComment("The Attack Speed of Darksabers.");
			prop.setLanguageKey("lightsaber.configgui.darksaber_speed");
			darksaberSpeed = prop.getDouble(2.8D);
			propOrder.add(prop.getName());
			
			prop = config.get(Reference.CONFIG_CATEGORY_VALUES, "White Lightsaber Damage", 24.0D);
			prop.setComment("The Attack Damage of White Lightsabers.");
			prop.setLanguageKey("lightsaber.configgui.white_lightsaber_damage");
			whiteLightsaberDamage = prop.getDouble(24.0D);
			propOrder.add(prop.getName());
			
			prop = config.get(Reference.CONFIG_CATEGORY_VALUES, "White Lightsaber Speed", 2.8D);
			prop.setComment("The Attack Speed of White Lightsabers.");
			prop.setLanguageKey("lightsaber.configgui.white_lightsaber_speed");
			whiteLightsaberSpeed = prop.getDouble(2.8D);
			propOrder.add(prop.getName());
			
			prop = config.get(Reference.CONFIG_CATEGORY_VALUES, "Purple Lightsaber Damage", 22.0D);
			prop.setComment("The Attack Damage of Purple Lightsabers.");
			prop.setLanguageKey("lightsaber.configgui.purple_lightsaber_damage");
			purpleLightsaberDamage = prop.getDouble(22.0D);
			propOrder.add(prop.getName());
			
			prop = config.get(Reference.CONFIG_CATEGORY_VALUES, "Purple Lightsaber Speed", 2.8D);
			prop.setComment("The Attack Speed of Purple Lightsabers.");
			prop.setLanguageKey("lightsaber.configgui.purple_lightsaber_speed");
			purpleLightsaberSpeed = prop.getDouble(2.8D);
			propOrder.add(prop.getName());

			if (config.hasChanged())
			{
				config.save();
			}

		}
		catch (final Exception e)
		{
			FMLRelaunchLog.log(Reference.MOD_NAME, Level.ERROR, ("Problem loading Lightsaber config (\"Lightsaber.conf\")"));
			e.printStackTrace();
		}
	}
	
	 public static List<IConfigElement> getConfigElements()
	    {
	        List<IConfigElement> list = new ArrayList<IConfigElement>();
	        list.addAll(new ConfigElement(config.getCategory(Reference.CONFIG_CATEGORY_VALUES)).getChildElements());
	        return list;
	    }

}
