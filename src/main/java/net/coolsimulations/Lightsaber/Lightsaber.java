package net.coolsimulations.Lightsaber;

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
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
//import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
		LightsaberUpdateHandler.init();
		LightsaberItems.init();
		LightsaberItems.register();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(Lightsaber::clientLoad);
		//LightsaberBlocks.init(); //temp till forge issue #6112 is resolved
		//LightsaberBlocks.register(); //temp till forge issue #6112 is resolved
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
			ItemModelsProperties.register(LightsaberItems.red_lightsaber, new ResourceLocation("blocking"), (stack, worldIn, entityIn) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemModelsProperties.register(LightsaberItems.blue_lightsaber, new ResourceLocation("blocking"), (stack, worldIn, entityIn) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemModelsProperties.register(LightsaberItems.green_lightsaber, new ResourceLocation("blocking"), (stack, worldIn, entityIn) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemModelsProperties.register(LightsaberItems.purple_lightsaber, new ResourceLocation("blocking"), (stack, worldIn, entityIn) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemModelsProperties.register(LightsaberItems.white_lightsaber, new ResourceLocation("blocking"), (stack, worldIn, entityIn) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
			ItemModelsProperties.register(LightsaberItems.darksaber, new ResourceLocation("blocking"), (stack, worldIn, entityIn) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getUseItem() == stack ? 1.0F : 0.0F;
			});
		}
	}

}
