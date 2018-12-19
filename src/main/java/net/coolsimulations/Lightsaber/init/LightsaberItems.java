package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.Lightsaber;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.Lightsaber.item.ItemLightsaber.LightsaberMaterial;
import net.coolsimulations.Lightsaber.item.ItemLightsaberHilt;
import net.coolsimulations.SurvivalPlus.api.SPItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

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
	
	//public static final Item.ToolMaterial lightsaberToolMaterial = EnumHelper.addToolMaterial("lightsaberToolMaterial", 2, 432, 7.0F, 2.5F, 17);

	
	public static void init() {
		
		pommel_cap = new Item().setUnlocalizedName("pommel_cap").setRegistryName("pommel_cap").setCreativeTab(SPItems.bronze_ingot.getCreativeTab());
		focusing_lens = new Item().setUnlocalizedName("focusing_lens").setRegistryName("focusing_lens").setCreativeTab(SPItems.bronze_ingot.getCreativeTab());
		blade_emitter = new Item().setUnlocalizedName("blade_emitter").setRegistryName("blade_emitter").setCreativeTab(SPItems.bronze_ingot.getCreativeTab());
		emitter_matrix = new Item().setUnlocalizedName("emitter_matrix").setRegistryName("emitter_matrix").setCreativeTab(SPItems.bronze_ingot.getCreativeTab());
		inert_power_insulator = new Item().setUnlocalizedName("inert_power_insulator").setRegistryName("inert_power_insulator").setCreativeTab(SPItems.bronze_ingot.getCreativeTab());
		activation_stud = new Item().setUnlocalizedName("activation_stud").setRegistryName("activation_stud").setCreativeTab(SPItems.bronze_ingot.getCreativeTab());
		lightsaber_hilt = new Item().setUnlocalizedName("lightsaber_hilt").setRegistryName("lightsaber_hilt").setCreativeTab(SPItems.bronze_ingot.getCreativeTab());
		darksaber_lens = new Item().setUnlocalizedName("darksaber_lens").setRegistryName("darksaber_lens").setCreativeTab(SPItems.bronze_ingot.getCreativeTab());
		
		red_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("red_lightsaber_hilt").setRegistryName("red_lightsaber_hilt").setCreativeTab(SPItems.bronze_sword.getCreativeTab());
		blue_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("blue_lightsaber_hilt").setRegistryName("blue_lightsaber_hilt").setCreativeTab(SPItems.bronze_sword.getCreativeTab());
		green_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("green_lightsaber_hilt").setRegistryName("green_lightsaber_hilt").setCreativeTab(SPItems.bronze_sword.getCreativeTab());
		purple_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("purple_lightsaber_hilt").setRegistryName("purple_lightsaber_hilt").setCreativeTab(SPItems.bronze_sword.getCreativeTab());
		white_lightsaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("white_lightsaber_hilt").setRegistryName("white_lightsaber_hilt").setCreativeTab(SPItems.bronze_sword.getCreativeTab());
		darksaber_hilt = new ItemLightsaberHilt().setUnlocalizedName("darksaber_hilt").setRegistryName("darksaber_hilt").setCreativeTab(SPItems.bronze_sword.getCreativeTab());
		
		red_lightsaber = new ItemLightsaber(LightsaberMaterial.Lightsaber).setUnlocalizedName("red_lightsaber").setRegistryName("red_lightsaber");
		blue_lightsaber = new ItemLightsaber(LightsaberMaterial.Lightsaber).setUnlocalizedName("blue_lightsaber").setRegistryName("blue_lightsaber");
		green_lightsaber = new ItemLightsaber(LightsaberMaterial.Lightsaber).setUnlocalizedName("green_lightsaber").setRegistryName("green_lightsaber");
		purple_lightsaber = new ItemLightsaber(LightsaberMaterial.Purple_Lightsaber).setUnlocalizedName("purple_lightsaber").setRegistryName("purple_lightsaber");
		white_lightsaber = new ItemLightsaber(LightsaberMaterial.White_Lightsaber).setUnlocalizedName("white_lightsaber").setRegistryName("white_lightsaber");
		darksaber = new ItemLightsaber(LightsaberMaterial.Darksaber).setUnlocalizedName("darksaber").setRegistryName("darksaber");
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
	
	public static void registerItem(Item item) {
		
		Lightsaber.ITEMS.add(item);
	}
	
	public static void registerItems(IForgeRegistry<Item> registry) {
		
	for (Item item : Lightsaber.ITEMS)
    {
        registry.register(item);
    	}
	}
	
	public static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
