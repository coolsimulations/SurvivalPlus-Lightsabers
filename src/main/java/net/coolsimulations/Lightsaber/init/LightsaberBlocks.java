package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.Lightsaber;
import net.coolsimulations.Lightsaber.block.LightsaberSconce;
import net.coolsimulations.SurvivalPlus.api.SPTabs;
import net.coolsimulations.SurvivalPlus.api.blocks.SPBlockSconce;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.registries.IForgeRegistry;

public class LightsaberBlocks {
	
	public static Block holocron;
	public static Block sconce;
	
	public static void init() {
		//holocron = new Block(Material.ROCK, MapColor.BLACK).setUnlocalizedName("holocron").setRegistryName("holocron").setCreativeTab(SPTabs.tabBlocks);
		sconce = new LightsaberSconce().setUnlocalizedName("sconce").setRegistryName("sconce").setCreativeTab(SPTabs.tabBlocks);
	}
	
	public static void register() {
		//registerBlock(holocron);
		registerBlock(sconce);
	}
	
	public static void registerRenders() {
		//registerRender(holocron);
		registerRender(sconce);
	}
	
	public static void registerBlock(Block block) {

		if (!(block instanceof SPBlockSconce))
			Lightsaber.ITEMS.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		Lightsaber.BLOCKS.add(block);
	}

	public static void registerBlocks(IForgeRegistry<Block> registry) {

		for (Block block: Lightsaber.BLOCKS)
		{
			registry.register(block);
		}
	}

	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
