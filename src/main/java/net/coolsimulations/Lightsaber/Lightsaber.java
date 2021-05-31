package net.coolsimulations.Lightsaber;

import java.util.ArrayList;
import java.util.List;

import net.coolsimulations.Lightsaber.init.LightsaberDispenserBehavior;
import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.Lightsaber.init.LightsaberVillagers;
import net.coolsimulations.Lightsaber.init.StructureVillageJediHut;
import net.coolsimulations.Lightsaber.init.VillageJediHutHandler;
import net.coolsimulations.Lightsaber.proxy.CommonProxy;
import net.coolsimulations.Lightsaber.util.LightsaberBetterCombat;
import net.coolsimulations.Lightsaber.util.LightsaberLighting;
import net.coolsimulations.Lightsaber.util.LightsaberSkills;
import net.coolsimulations.Lightsaber.util.LightsaberSwordBlocking;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.SPReference;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, dependencies = Reference.DEPENDENCIES, updateJSON = "https://coolsimulations.net/mcmods/lightsaber/versionchecker.json")
public class Lightsaber {
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Mod.Instance(Reference.MOD_ID)
	public static Lightsaber instance;
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		System.out.println("Pre Init");
		SPReference.MOD_ADDON_NAMES.add("sp.lightsaber.name");
		MinecraftForge.EVENT_BUS.register(new LightsaberEventHandler());
		LightsaberItems.init();
		LightsaberItems.register(); 
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageJediHutHandler());
		MapGenStructureIO.registerStructureComponent(StructureVillageJediHut.class, Reference.MOD_ID+":jediHutStructure");
		LightsaberVillagers.registerVillagers();
		
		if(SPCompatibilityManager.isDynamicLightsLoaded()) {
			LightsaberLighting.initDynamicLights(event);
		}
		
		if(SPCompatibilityManager.isSELLoaded()) {
			LightsaberLighting.initSmoothEntityLight(event);
		}
		
		if(SPCompatibilityManager.isReskillableLoaded()) {
			LightsaberSkills.initReskillable(event);
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		System.out.println("Init");
		proxy.init();
		LightsaberSoundHandler.init();
		LightsaberDispenserBehavior.init();
		
		if(SPCompatibilityManager.isSwordBlockingLoaded()) {
			LightsaberSwordBlocking.init();
		}
		
		if(SPCompatibilityManager.isBetterCombatLoaded()) {
			LightsaberBetterCombat.init();
		}
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		System.out.println("Post Init");
		
	}
}
