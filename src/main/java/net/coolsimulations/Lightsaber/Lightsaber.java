package net.coolsimulations.Lightsaber;

import java.io.File;

import net.coolsimulations.Lightsaber.block.tileentity.TileEntityLightsaberSconce;
import net.coolsimulations.Lightsaber.config.LightsaberConfig;
import net.coolsimulations.Lightsaber.init.LightsaberBlocks;
import net.coolsimulations.Lightsaber.init.LightsaberDispenserBehavior;
import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.Lightsaber.init.LightsaberUpdateHandler;
import net.coolsimulations.SurvivalPlus.api.SPReference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class Lightsaber implements ModInitializer {

	private static Lightsaber instance;

	public static Lightsaber getInstance()
	{
		return instance;
	}
	
	public static BlockEntityType<TileEntityLightsaberSconce> SCONCE;

	@Override
	public void onInitialize() {

		LightsaberEventHandler.init();
		SPReference.MOD_ADDON_NAMES.add("sp.lightsaber.name");
		LightsaberConfig.init(new File(FabricLoader.getInstance().getConfigDir().toFile(), Reference.MOD_ID + ".json"));
		LightsaberUpdateHandler.init();
		LightsaberItems.init();	
		LightsaberItems.register();
		LightsaberBlocks.init();
		LightsaberBlocks.register();
		SCONCE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Reference.MOD_ID, "sconce"),
				FabricBlockEntityTypeBuilder.create(TileEntityLightsaberSconce::new, LightsaberBlocks.sconce).build());
		LightsaberSoundHandler.init();
		LightsaberDispenserBehavior.init();
	}

}
