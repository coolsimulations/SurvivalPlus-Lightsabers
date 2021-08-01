package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.SurvivalPlus.api.SPTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class LightsaberBlocks {
	
	public static Block holocron;
	
	public static void init() {
		
		holocron = new Block(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(3.5F)).setRegistryName("holocron");
		
	}
	
	public static void register() {
		
		registerBlock(holocron);
	}
	
	public static void registerBlock(Block block) {
		
		BlockItem BlockItem = new BlockItem(block, new Item.Properties().tab(SPTabs.tabBlocks));
		BlockItem.setRegistryName(block.getRegistryName());
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(BlockItem);
	}

}
