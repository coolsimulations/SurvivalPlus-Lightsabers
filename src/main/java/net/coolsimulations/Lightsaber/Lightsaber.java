package net.coolsimulations.Lightsaber;

import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.Lightsaber.init.LightsaberUpdateHandler;
import net.coolsimulations.Lightsaber.init.LightsaberVillagers;
import net.coolsimulations.Lightsaber.init.StructureVillageJediHut;
import net.coolsimulations.Lightsaber.init.VillageJediHutHandler;
import net.coolsimulations.Lightsaber.proxy.ClientProxy;
import net.coolsimulations.Lightsaber.proxy.CommonProxy;
import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

@Mod(value = Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Lightsaber {
	
	public static CommonProxy proxy = (CommonProxy) DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
	private static Lightsaber instance;
	
	public static Lightsaber getInstance()
	    {
	        return instance;
	    }
	
	public Lightsaber() {
		
		MinecraftForge.EVENT_BUS.register(new LightsaberEventHandler());
		LightsaberUpdateHandler.init();
		LightsaberItems.init();
		LightsaberItems.register();
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageJediHutHandler());
		StructureIO.registerStructureComponent(StructureVillageJediHut.class, Reference.MOD_ID+":jediHutStructure");
		LightsaberVillagers.registerVillagers();
		LightsaberSoundHandler.init();
	}
	
}
