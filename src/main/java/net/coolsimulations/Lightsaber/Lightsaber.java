package net.coolsimulations.Lightsaber;

import java.io.File;

import net.coolsimulations.Lightsaber.block.tileentity.TileEntityLightsaberSconce;
import net.coolsimulations.Lightsaber.config.LightsaberConfig;
import net.coolsimulations.Lightsaber.config.LightsaberConfigGUI;
import net.coolsimulations.Lightsaber.init.LightsaberBlocks;
import net.coolsimulations.Lightsaber.init.LightsaberDispenserBehavior;
import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.Lightsaber.init.LightsaberUpdateHandler;
import net.coolsimulations.Lightsaber.proxy.ClientProxy;
import net.coolsimulations.Lightsaber.proxy.CommonProxy;
import net.coolsimulations.Lightsaber.util.LightsaberSwordBlocking;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.SPItems;
import net.coolsimulations.SurvivalPlus.api.SPReference;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
//import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(value = Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Lightsaber {

	public static CommonProxy proxy = (CommonProxy) DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

	private static Lightsaber instance;

	public static Lightsaber getInstance()
	{
		return instance;
	}
	
	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<TileEntityLightsaberSconce>> SCONCE = TILE_ENTITY_TYPES.register("sconce",
            () -> BlockEntityType.Builder.of(TileEntityLightsaberSconce::new, LightsaberBlocks.sconce.get()).build(null));

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
		LightsaberBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		Lightsaber.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
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
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addToTabs);
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
	
	private void addToTabs(CreativeModeTabEvent.BuildContents event) {
		if (event.getTab().getIconItem().getItem() == SPItems.tin_ingot.get()) {
			event.accept(LightsaberItems.pommel_cap.get());
			event.accept(LightsaberItems.focusing_lens.get());
			event.accept(LightsaberItems.blade_emitter.get());
			event.accept(LightsaberItems.emitter_matrix.get());
			event.accept(LightsaberItems.inert_power_insulator.get());
			event.accept(LightsaberItems.activation_stud.get());
			event.accept(LightsaberItems.lightsaber_hilt.get());
			event.accept(LightsaberItems.darksaber_lens.get());
		}
		if (event.getTab().getIconItem().getItem() == SPItems.titanium_sword.get()) {
			event.accept(LightsaberItems.red_lightsaber_hilt.get());
			event.accept(LightsaberItems.blue_lightsaber_hilt.get());
			event.accept(LightsaberItems.green_lightsaber_hilt.get());
			event.accept(LightsaberItems.yellow_lightsaber_hilt.get());
			event.accept(LightsaberItems.purple_lightsaber_hilt.get());
			event.accept(LightsaberItems.white_lightsaber_hilt.get());
			event.accept(LightsaberItems.darksaber_hilt.get());
		}
	}

}
