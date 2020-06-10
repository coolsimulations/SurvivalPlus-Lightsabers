package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.Lightsaber.item.ItemLightsaber.LightsaberTier;
import net.coolsimulations.Lightsaber.item.ItemLightsaberHilt;
import net.coolsimulations.SurvivalPlus.api.SPTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

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
	public static Item purple_lightsaber_hilt;
	public static Item white_lightsaber_hilt;
	public static Item darksaber_hilt;

	public static Item red_lightsaber;
	public static Item blue_lightsaber;
	public static Item green_lightsaber;
	public static Item purple_lightsaber;
	public static Item white_lightsaber;
	public static Item darksaber;

	public static void init() {

		pommel_cap = new Item(new Item.Properties().group(SPTabs.tabMaterials)).setRegistryName("pommel_cap");
		focusing_lens = new Item(new Item.Properties().group(SPTabs.tabMaterials)).setRegistryName("focusing_lens");
		blade_emitter = new Item(new Item.Properties().group(SPTabs.tabMaterials)).setRegistryName("blade_emitter");
		emitter_matrix = new Item(new Item.Properties().group(SPTabs.tabMaterials)).setRegistryName("emitter_matrix");
		inert_power_insulator = new Item(new Item.Properties().group(SPTabs.tabMaterials)).setRegistryName("inert_power_insulator");
		activation_stud = new Item(new Item.Properties().group(SPTabs.tabMaterials)).setRegistryName("activation_stud");
		lightsaber_hilt = new Item(new Item.Properties().group(SPTabs.tabMaterials)).setRegistryName("lightsaber_hilt");
		darksaber_lens = new Item(new Item.Properties().group(SPTabs.tabMaterials)).setRegistryName("darksaber_lens");

		red_lightsaber_hilt = new ItemLightsaberHilt(new Item.Properties().group(SPTabs.tabCombat)).setRegistryName("red_lightsaber_hilt");
		blue_lightsaber_hilt = new ItemLightsaberHilt(new Item.Properties().group(SPTabs.tabCombat)).setRegistryName("blue_lightsaber_hilt");
		green_lightsaber_hilt = new ItemLightsaberHilt(new Item.Properties().group(SPTabs.tabCombat)).setRegistryName("green_lightsaber_hilt");
		purple_lightsaber_hilt = new ItemLightsaberHilt(new Item.Properties().group(SPTabs.tabCombat)).setRegistryName("purple_lightsaber_hilt");
		white_lightsaber_hilt = new ItemLightsaberHilt(new Item.Properties().group(SPTabs.tabCombat)).setRegistryName("white_lightsaber_hilt");
		darksaber_hilt = new ItemLightsaberHilt(new Item.Properties().group(SPTabs.tabCombat)).setRegistryName("darksaber_hilt");

		red_lightsaber = new ItemLightsaber(new Item.Properties(), LightsaberTier.Lightsaber).setRegistryName("red_lightsaber");
		blue_lightsaber = new ItemLightsaber(new Item.Properties(), LightsaberTier.Lightsaber).setRegistryName("blue_lightsaber");
		green_lightsaber = new ItemLightsaber(new Item.Properties(), LightsaberTier.Lightsaber).setRegistryName("green_lightsaber");
		purple_lightsaber = new ItemLightsaber(new Item.Properties(), LightsaberTier.Purple_Lightsaber).setRegistryName("purple_lightsaber");
		white_lightsaber = new ItemLightsaber(new Item.Properties(), LightsaberTier.White_Lightsaber).setRegistryName("white_lightsaber");
		darksaber = new ItemLightsaber(new Item.Properties(), LightsaberTier.Darksaber).setRegistryName("darksaber");
	}

	public static void register()
	{

		registerItem(pommel_cap);
		registerItem(focusing_lens);
		registerItem(blade_emitter);
		registerItem(emitter_matrix);
		registerItem(inert_power_insulator);
		registerItem(activation_stud);
		registerItem(lightsaber_hilt);
		registerItem(darksaber_lens);

		registerItem(red_lightsaber_hilt);
		registerItem(blue_lightsaber_hilt);
		registerItem(green_lightsaber_hilt);
		registerItem(purple_lightsaber_hilt);
		registerItem(white_lightsaber_hilt);
		registerItem(darksaber_hilt);

		registerItem(red_lightsaber);
		registerItem(blue_lightsaber);
		registerItem(green_lightsaber);
		registerItem(purple_lightsaber);
		registerItem(white_lightsaber);
		registerItem(darksaber);
	}

	public static void registerItem(Item item) {

		ForgeRegistries.ITEMS.register(item);
	}

}
