package net.coolsimulations.Lightsaber;

import java.io.File;

import net.coolsimulations.Lightsaber.config.LightsaberConfig;
import net.coolsimulations.Lightsaber.config.LightsaberConfigGUI;
import net.coolsimulations.Lightsaber.init.LightsaberDispenserBehavior;
import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.Lightsaber.init.LightsaberUpdateHandler;
import net.coolsimulations.Lightsaber.proxy.ClientProxy;
import net.coolsimulations.Lightsaber.proxy.CommonProxy;
import net.coolsimulations.Lightsaber.util.LightsaberSwordBlocking;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.SPReference;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
//import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(value = Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Lightsaber {

	public static CommonProxy proxy = (CommonProxy) DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

	private static Lightsaber instance;

	public static Lightsaber getInstance()
	{
		return instance;
	}

	public Lightsaber() {

		MinecraftForge.EVENT_BUS.register(new LightsaberEventHandler());
		SPReference.MOD_ADDON_NAMES.add("sp.lightsaber.name");
		LightsaberConfig.init(new File(FMLPaths.CONFIGDIR.get().toFile(), Reference.MOD_ID + "-common.json"));
		
		if(SPCompatibilityManager.isClothConfigLoaded()) {
			ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> {
				return LightsaberConfigGUI.getConfigScreen(screen);
			}));
		}

		LightsaberUpdateHandler.init();
		LightsaberItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		FMLJavaModLoadingContext.get().getModEventBus().addListener(Lightsaber::clientLoad);
		//LightsaberBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus()); //temp till forge issue #6112 is resolved
		//LightsaberBlocks.BLOCK_ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus()); //temp till forge issue #6112 is resolved
		//VillagerRegistry.instance().registerVillageCreationHandler(new VillageJediHutHandler()); //temp till forge pull request #6142 is resolved
		//StructureIO.registerStructureComponent(StructureVillageJediHut.class, Reference.MOD_ID+":jediHutStructure"); //temp till forge pull request #6142 is resolved
		//LightsaberVillagers.registerVillagers(); //temp till forge issue #6112 is resolved
		LightsaberSoundHandler.init();
		LightsaberDispenserBehavior.init();
		
		proxy.init();

		if(SPCompatibilityManager.isSwordBlockingLoaded()) {
			LightsaberSwordBlocking.init();
		}
	}
	
	private static void clientLoad(final FMLClientSetupEvent event)
	{
		if(!SPCompatibilityManager.isSwordBlockingLoaded()) {
			ItemProperties.register(LightsaberItems.red_lightsaber.get(), new ResourceLocation("blocking"), (stack, worldIn, entityIn, seed) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemProperties.register(LightsaberItems.blue_lightsaber.get(), new ResourceLocation("blocking"), (stack, worldIn, entityIn, seed) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemProperties.register(LightsaberItems.green_lightsaber.get(), new ResourceLocation("blocking"), (stack, worldIn, entityIn, seed) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemProperties.register(LightsaberItems.purple_lightsaber.get(), new ResourceLocation("blocking"), (stack, worldIn, entityIn, seed) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemProperties.register(LightsaberItems.white_lightsaber.get(), new ResourceLocation("blocking"), (stack, worldIn, entityIn, seed) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemProperties.register(LightsaberItems.darksaber.get(), new ResourceLocation("blocking"), (stack, worldIn, entityIn, seed) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
		}
	}

}
