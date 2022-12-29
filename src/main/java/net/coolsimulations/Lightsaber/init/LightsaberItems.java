package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.Lightsaber.item.ItemLightsaber.LightsaberTier;
import net.coolsimulations.Lightsaber.item.ItemLightsaberHilt;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"unused"})
public class LightsaberItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

	public static final RegistryObject<Item> pommel_cap = ITEMS.register("pommel_cap", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> focusing_lens = ITEMS.register("focusing_lens", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> blade_emitter = ITEMS.register("blade_emitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> emitter_matrix = ITEMS.register("emitter_matrix", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> inert_power_insulator = ITEMS.register("inert_power_insulator", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> activation_stud = ITEMS.register("activation_stud", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> lightsaber_hilt = ITEMS.register("lightsaber_hilt", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> darksaber_lens = ITEMS.register("darksaber_lens", () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> red_lightsaber_hilt = ITEMS.register("red_lightsaber_hilt", () -> new ItemLightsaberHilt(new Item.Properties()));
	public static final RegistryObject<Item> blue_lightsaber_hilt = ITEMS.register("blue_lightsaber_hilt", () -> new ItemLightsaberHilt(new Item.Properties()));
	public static final RegistryObject<Item> green_lightsaber_hilt = ITEMS.register("green_lightsaber_hilt", () -> new ItemLightsaberHilt(new Item.Properties()));
	public static final RegistryObject<Item> yellow_lightsaber_hilt = ITEMS.register("yellow_lightsaber_hilt", () -> new ItemLightsaberHilt(new Item.Properties()));
	public static final RegistryObject<Item> purple_lightsaber_hilt = ITEMS.register("purple_lightsaber_hilt", () -> new ItemLightsaberHilt(new Item.Properties()));
	public static final RegistryObject<Item> white_lightsaber_hilt = ITEMS.register("white_lightsaber_hilt", () -> new ItemLightsaberHilt(new Item.Properties()));
	public static final RegistryObject<Item> darksaber_hilt = ITEMS.register("darksaber_hilt", () -> new ItemLightsaberHilt(new Item.Properties().fireResistant()));

	public static final RegistryObject<Item> red_lightsaber = ITEMS.register("red_lightsaber", () -> new ItemLightsaber(new Item.Properties(), LightsaberTier.Lightsaber));
	public static final RegistryObject<Item> blue_lightsaber = ITEMS.register("blue_lightsaber", () -> new ItemLightsaber(new Item.Properties(), LightsaberTier.Lightsaber));
	public static final RegistryObject<Item> green_lightsaber = ITEMS.register("green_lightsaber", () -> new ItemLightsaber(new Item.Properties(), LightsaberTier.Lightsaber));
	public static final RegistryObject<Item> yellow_lightsaber = ITEMS.register("yellow_lightsaber", () -> new ItemLightsaber(new Item.Properties(), LightsaberTier.Lightsaber));
	public static final RegistryObject<Item> purple_lightsaber = ITEMS.register("purple_lightsaber", () -> new ItemLightsaber(new Item.Properties(), LightsaberTier.Purple_Lightsaber));
	public static final RegistryObject<Item> white_lightsaber = ITEMS.register("white_lightsaber", () -> new ItemLightsaber(new Item.Properties(), LightsaberTier.White_Lightsaber));
	public static final RegistryObject<Item> darksaber = ITEMS.register("darksaber", () -> new ItemLightsaber(new Item.Properties().fireResistant(), LightsaberTier.Darksaber));

}