package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.SurvivalPlus.api.SPTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class LightsaberBlocks {
	
	public static Block holocron;
	
	public static void init() {
		
		holocron = new Block(Block.Properties.create(Material.ROCK, MaterialColor.OBSIDIAN).hardnessAndResistance(3.5F)).setRegistryName("holocron");
		
	}
	
	public static void register() {
		
		registerBlock(holocron);
	}
	
	public static void registerBlock(Block block) {
		
		BlockItem BlockItem = new BlockItem(block, new Item.Properties().group(SPTabs.tabBlocks));
		BlockItem.setRegistryName(block.getRegistryName());
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(BlockItem);
	}

}
