package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.Lightsaber.item.ItemLightsaber.LightsaberTier;
import net.coolsimulations.Lightsaber.item.ItemLightsaberHilt;
import net.coolsimulations.SurvivalPlus.api.SPTabs;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class LightsaberItems {

	public static Item pommel_cap;
	public static Item focusing_lens;
	public static Item blade_emitter;
	public static Item emitter_matrix;
	public static Item inert_power_insulator;
	public static Item activation_stud;
	public static Item lightsaber_hilt;
	public static Item darksaber_lens;

	public static Item red_lightsaber_hilt;
	public static Item blue_lightsaber_hilt;
	public static Item green_lightsaber_hilt;
	public static Item yellow_lightsaber_hilt;
	public static Item purple_lightsaber_hilt;
	public static Item white_lightsaber_hilt;
	public static Item darksaber_hilt;

	public static Item red_lightsaber;
	public static Item blue_lightsaber;
	public static Item green_lightsaber;
	public static Item yellow_lightsaber;
	public static Item purple_lightsaber;
	public static Item white_lightsaber;
	public static Item darksaber;

	public static void init() {

		pommel_cap = new Item(new FabricItemSettings().group(SPTabs.tabMaterials));
		focusing_lens = new Item(new FabricItemSettings().group(SPTabs.tabMaterials));
		blade_emitter = new Item(new FabricItemSettings().group(SPTabs.tabMaterials));
		emitter_matrix = new Item(new FabricItemSettings().group(SPTabs.tabMaterials));
		inert_power_insulator = new Item(new FabricItemSettings().group(SPTabs.tabMaterials));
		activation_stud = new Item(new FabricItemSettings().group(SPTabs.tabMaterials));
		lightsaber_hilt = new Item(new FabricItemSettings().group(SPTabs.tabMaterials));
		darksaber_lens = new Item(new FabricItemSettings().group(SPTabs.tabMaterials));

		red_lightsaber_hilt = new ItemLightsaberHilt(new FabricItemSettings().group(SPTabs.tabCombat));
		blue_lightsaber_hilt = new ItemLightsaberHilt(new FabricItemSettings().group(SPTabs.tabCombat));
		green_lightsaber_hilt = new ItemLightsaberHilt(new FabricItemSettings().group(SPTabs.tabCombat));
		yellow_lightsaber_hilt = new ItemLightsaberHilt(new FabricItemSettings().group(SPTabs.tabCombat));
		purple_lightsaber_hilt = new ItemLightsaberHilt(new FabricItemSettings().group(SPTabs.tabCombat));
		white_lightsaber_hilt = new ItemLightsaberHilt(new FabricItemSettings().group(SPTabs.tabCombat));
		darksaber_hilt = new ItemLightsaberHilt(new FabricItemSettings().group(SPTabs.tabCombat));

		red_lightsaber = new ItemLightsaber(new FabricItemSettings(), LightsaberTier.Lightsaber);
		blue_lightsaber = new ItemLightsaber(new FabricItemSettings(), LightsaberTier.Lightsaber);
		green_lightsaber = new ItemLightsaber(new FabricItemSettings(), LightsaberTier.Lightsaber);
		yellow_lightsaber = new ItemLightsaber(new FabricItemSettings(), LightsaberTier.Lightsaber);
		purple_lightsaber = new ItemLightsaber(new FabricItemSettings(), LightsaberTier.Purple_Lightsaber);
		white_lightsaber = new ItemLightsaber(new FabricItemSettings(), LightsaberTier.White_Lightsaber);
		darksaber = new ItemLightsaber(new FabricItemSettings(), LightsaberTier.Darksaber);
	}

	public static void register()
	{

		registerItem(pommel_cap, "pommel_cap");
		registerItem(focusing_lens, "focusing_lens");
		registerItem(blade_emitter, "blade_emitter");
		registerItem(emitter_matrix, "emitter_matrix");
		registerItem(inert_power_insulator, "inert_power_insulator");
		registerItem(activation_stud, "activation_stud");
		registerItem(lightsaber_hilt, "lightsaber_hilt");
		registerItem(darksaber_lens, "darksaber_lens");

		registerItem(red_lightsaber_hilt, "red_lightsaber_hilt");
		registerItem(blue_lightsaber_hilt, "blue_lightsaber_hilt");
		registerItem(green_lightsaber_hilt, "green_lightsaber_hilt");
		registerItem(yellow_lightsaber_hilt, "yellow_lightsaber_hilt");
		registerItem(purple_lightsaber_hilt, "purple_lightsaber_hilt");
		registerItem(white_lightsaber_hilt, "white_lightsaber_hilt");
		registerItem(darksaber_hilt, "darksaber_hilt");

		registerItem(red_lightsaber, "red_lightsaber");
		registerItem(blue_lightsaber, "blue_lightsaber");
		registerItem(green_lightsaber, "green_lightsaber");
		registerItem(yellow_lightsaber, "yellow_lightsaber");
		registerItem(purple_lightsaber, "purple_lightsaber");
		registerItem(white_lightsaber, "white_lightsaber");
		registerItem(darksaber, "darksaber");
	}

	public static void registerItem(Item item, String registryName) {

		Registry.register(Registry.ITEM, new ResourceLocation(Reference.MOD_ID, registryName), item);
	}

}
