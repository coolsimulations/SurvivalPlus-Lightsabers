package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.block.LightsaberSconce;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LightsaberBlocks {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	
	//public static final RegistryObject<Block> holocron = BLOCKS.register("holocron", () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(3.5F)));
	
	//public static final RegistryObject<Item> holocron_item = BLOCK_ITEMS.register("holocron", () -> new BlockItem(holocron.get(), new Item.Properties().tab(SPTabs.tabBlocks)));
	
	public static final RegistryObject<Block> sconce = BLOCKS.register("sconce", () -> new LightsaberSconce());

}
