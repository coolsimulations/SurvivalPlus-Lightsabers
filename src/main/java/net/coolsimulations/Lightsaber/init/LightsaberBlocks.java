package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.block.LightsaberSconce;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class LightsaberBlocks {
	
	public static Block sconce;
	
	public static void init() {
		
		sconce = new LightsaberSconce();
	}
	
	public static void register() {
		
		registerBlock(sconce, "sconce");
	}
	
	public static void registerBlock(Block block, String registryName) {

		Registry.register(Registry.BLOCK, new ResourceLocation(Reference.MOD_ID, registryName), block);
	}

}
