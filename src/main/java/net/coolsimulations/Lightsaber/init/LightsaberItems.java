package net.coolsimulations.Lightsaber.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.coolsimulations.Lightsaber.Lightsaber;
import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.Lightsaber.item.ItemLightsaber.LightsaberMaterial;
import net.coolsimulations.Lightsaber.item.ItemLightsaberHilt;
import net.coolsimulations.SurvivalPlus.api.SPItems;
import net.coolsimulations.SurvivalPlus.api.SPTabs;

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
		
		pommel_cap = new Item().setUnlocalizedName("pommel_cap").setRegistryName("pommel_cap").setCreativeTab(SPTabs.tabMaterials);
		focusing_lens = new Item().setUnlocalizedName("focusing_lens").setRegistryName("focusing_lens").setCreativeTab(SPTabs.tabMaterials);
		blade_emitter = new Item().setUnlocalizedName("blade_emitter").setRegistryName("blade_emitter").setCreativeTab(SPTabs.tabMaterials);
		emitter_matrix = new Item().setUnlocalizedName("emitter_matrix").setRegistryName("emitter_matrix").setCreativeTab(SPTabs.tabMaterials);
		inert_power_insulator = new Item().setUnlocalizedName("inert_power_insulator").setRegistryName("inert_power_insulator").setCreativeTab(SPTabs.tabMaterials);
		activation_stud = new Item().setUnlocalizedName("activation_stud").setRegistryName("activation_stud").setCreativeTab(SPTabs.tabMaterials);
		lightsaber_hilt = new Item().setUnlocalizedName("lightsaber_hilt").setRegistryName("lightsaber_hilt").setCreativeTab(SPTabs.tabMaterials);
		darksaber_lens = new Item().setUnlocalizedName("darksaber_lens").setRegistryName("darksaber_lens").setCreativeTab(SPTabs.tabMaterials);
		
		red_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("red_lightsaber_hilt").setRegistryName("red_lightsaber_hilt").setCreativeTab(SPTabs.tabCombat);
		blue_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("blue_lightsaber_hilt").setRegistryName("blue_lightsaber_hilt").setCreativeTab(SPTabs.tabCombat);
		green_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("green_lightsaber_hilt").setRegistryName("green_lightsaber_hilt").setCreativeTab(SPTabs.tabCombat);
		purple_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("purple_lightsaber_hilt").setRegistryName("purple_lightsaber_hilt").setCreativeTab(SPTabs.tabCombat);
		white_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("white_lightsaber_hilt").setRegistryName("white_lightsaber_hilt").setCreativeTab(SPTabs.tabCombat);
		darksaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("darksaber_hilt").setRegistryName("darksaber_hilt").setCreativeTab(SPTabs.tabCombat);
		
		red_lightsaber = new ItemLightsaber(LightsaberMaterial.Lightsaber).setUnlocalizedName("red_lightsaber").setRegistryName("red_lightsaber");
		blue_lightsaber = new ItemLightsaber(LightsaberMaterial.Lightsaber).setUnlocalizedName("blue_lightsaber").setRegistryName("blue_lightsaber");
		green_lightsaber = new ItemLightsaber(LightsaberMaterial.Lightsaber).setUnlocalizedName("green_lightsaber").setRegistryName("green_lightsaber");
		purple_lightsaber = new ItemLightsaber(LightsaberMaterial.Purple_Lightsaber).setUnlocalizedName("purple_lightsaber").setRegistryName("purple_lightsaber");
		white_lightsaber = new ItemLightsaber(LightsaberMaterial.White_Lightsaber).setUnlocalizedName("white_lightsaber").setRegistryName("white_lightsaber");
		darksaber = new ItemLightsaber(LightsaberMaterial.Darksaber).setUnlocalizedName("darksaber").setRegistryName("darksaber");
	}
	
	public static void register()
	{
		
		GameRegistry.register(pommel_cap);
		GameRegistry.register(focusing_lens);
		GameRegistry.register(blade_emitter);
		GameRegistry.register(emitter_matrix);
		GameRegistry.register(inert_power_insulator);
		GameRegistry.register(activation_stud);
		GameRegistry.register(lightsaber_hilt);
		GameRegistry.register(darksaber_lens);
		
		GameRegistry.register(red_lightsaber_hilt);
		GameRegistry.register(blue_lightsaber_hilt);
		GameRegistry.register(green_lightsaber_hilt);
		GameRegistry.register(purple_lightsaber_hilt);
		GameRegistry.register(white_lightsaber_hilt);
		GameRegistry.register(darksaber_hilt);
		
		GameRegistry.register(red_lightsaber);
		GameRegistry.register(blue_lightsaber);
		GameRegistry.register(green_lightsaber);
		GameRegistry.register(purple_lightsaber);
		GameRegistry.register(white_lightsaber);
		GameRegistry.register(darksaber);
	}
	
	public static void registerRenders()
	{
		registerRender(pommel_cap);
		registerRender(focusing_lens);
		registerRender(blade_emitter);
		registerRender(emitter_matrix);
		registerRender(inert_power_insulator);
		registerRender(activation_stud);
		registerRender(lightsaber_hilt);
		registerRender(darksaber_lens);
		
		registerRender(red_lightsaber_hilt);
		registerRender(blue_lightsaber_hilt);
		registerRender(green_lightsaber_hilt);
		registerRender(purple_lightsaber_hilt);
		registerRender(white_lightsaber_hilt);
		registerRender(darksaber_hilt);
		
		registerRender(red_lightsaber);
		registerRender(blue_lightsaber);
		registerRender(green_lightsaber);
		registerRender(purple_lightsaber);
		registerRender(white_lightsaber);
		registerRender(darksaber);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
