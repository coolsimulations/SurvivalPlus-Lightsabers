package net.coolsimulations.Lightsaber.util;

import java.io.File;

import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class LightsaberLighting {

	public static void initSmoothEntityLight(FMLPreInitializationEvent event) {

		String itemListDefault = "torch,glowstone=12,glowstone_dust=10,lit_pumpkin,lava_bucket,redstone_torch=10,redstone=10,golden_helmet=14,easycoloredlights:easycoloredlightsCLStone=-1";

		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "sel.cfg"));

		Property itemsList = config.get(Configuration.CATEGORY_GENERAL, "Light Items", itemListDefault);
		itemsList.setComment("Comma separated list of items that shine light when dropped in the World or held in player's or mob's hands.");

		String itemsListString = itemsList.getString();

		if(!itemsListString.contains(LightsaberItems.red_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.red_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.blue_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.blue_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.green_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.green_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.purple_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.purple_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.white_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.white_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.darksaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.darksaber.getRegistryName();
		}


		config.get(Configuration.CATEGORY_GENERAL, "Light Items", itemListDefault).set(itemsListString);
		config.save();

	}

	public static void initDynamicLights(FMLPreInitializationEvent event) {

		thePlayers(event);
		otherPlayers(event);
		mobEquipment(event);
		dropItems(event);
	}

	private static void otherPlayers(FMLPreInitializationEvent event) {

		String itemListDefault = "torch,glowstone=12,glowstone_dust=10,lit_pumpkin,lava_bucket,redstone_torch=10,redstone=10,golden_helmet=14";

		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "dynamiclights_otherplayers.cfg"));

		Property itemsList = config.get(Configuration.CATEGORY_GENERAL, "LightItems", itemListDefault);
		itemsList.setComment("Item IDs that shine light while held. Armor Items also work when worn. [ONLY ON OTHERS] Syntax: ItemID[-MetaValue]:LightValue, seperated by commas");

		String itemsListString = itemsList.getString();

		if(!itemsListString.contains(LightsaberItems.red_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.red_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.blue_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.blue_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.green_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.green_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.purple_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.purple_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.white_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.white_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.darksaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.darksaber.getRegistryName();
		}


		config.get(Configuration.CATEGORY_GENERAL, "LightItems", itemListDefault).set(itemsListString);
		config.save();

	}

	private static void thePlayers(FMLPreInitializationEvent event) {

		String itemListDefault = "torch,glowstone=12,glowstone_dust=10,lit_pumpkin,lava_bucket,redstone_torch=10,redstone=10,golden_helmet=14,golden_horse_armor=15";

		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "dynamiclights_theplayer.cfg"));

		Property itemsList = config.get(Configuration.CATEGORY_GENERAL, "LightItems", itemListDefault);
		itemsList.setComment("Item IDs that shine light while held. Armor Items also work when worn. [ONLY ON YOURSELF]");

		String itemsListString = itemsList.getString();

		if(!itemsListString.contains(LightsaberItems.red_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.red_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.blue_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.blue_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.green_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.green_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.purple_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.purple_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.white_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.white_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.darksaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.darksaber.getRegistryName();
		}


		config.get(Configuration.CATEGORY_GENERAL, "LightItems", itemListDefault).set(itemsListString);
		config.save();

	}

	private static void mobEquipment(FMLPreInitializationEvent event) {

		String itemListDefault = "torch,glowstone=12,glowstone_dust=10,lit_pumpkin,lava_bucket,redstone_torch=10,redstone=10,golden_helmet=14";

		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "dynamiclights_mobequipment.cfg"));

		Property itemsList = config.get(Configuration.CATEGORY_GENERAL, "LightItems", itemListDefault);
		itemsList.setComment("Item and Armor IDs that shine light when found on any EntityLiving. Syntax: ItemID:LightValue, seperated by commas");

		String itemsListString = itemsList.getString();

		if(!itemsListString.contains(LightsaberItems.red_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.red_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.blue_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.blue_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.green_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.green_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.purple_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.purple_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.white_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.white_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.darksaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.darksaber.getRegistryName();
		}


		config.get(Configuration.CATEGORY_GENERAL, "LightItems", itemListDefault).set(itemsListString);
		config.save();

	}

	private static void dropItems(FMLPreInitializationEvent event) {

		String itemListDefault = "torch,glowstone=12,glowstone_dust=10,lit_pumpkin,lava_bucket,redstone_torch=10,redstone=10,golden_helmet=14";

		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "dynamiclights_dropitems.cfg"));

		Property itemsList = config.get(Configuration.CATEGORY_GENERAL, "LightItems", itemListDefault);
		itemsList.setComment("Item IDs that shine light when dropped in the World.");

		String itemsListString = itemsList.getString();

		if(!itemsListString.contains(LightsaberItems.red_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.red_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.blue_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.blue_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.green_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.green_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.purple_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.purple_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.white_lightsaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.white_lightsaber.getRegistryName();
		}
		if(!itemsListString.contains(LightsaberItems.darksaber.getRegistryName().toString())) {
			itemsListString += "," + LightsaberItems.darksaber.getRegistryName();
		}


		config.get(Configuration.CATEGORY_GENERAL, "LightItems", itemListDefault).set(itemsListString);
		config.save();

	}

}
