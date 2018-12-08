package net.coolsimulations.Lightsaber;

import net.coolsimulations.Lightsaber.init.LightsaberAchievements;
import net.coolsimulations.Lightsaber.init.LightsaberEventHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.Lightsaber.init.LightsaberVillagers;
import net.coolsimulations.Lightsaber.init.StructureVillageJediHut;
import net.coolsimulations.Lightsaber.init.VillageJediHutHandler;
import net.coolsimulations.Lightsaber.proxy.CommonProxy;
import net.coolsimulations.Lightsaber.recipes.LightsaberShapedRecipes;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, dependencies = Reference.DEPENDENCIES, updateJSON = "http://coolsimulations.net/mcmods/lightsaber/versionchecker.json")
public class Lightsaber {
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Mod.Instance(Reference.MOD_ID)
	public static Lightsaber instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		System.out.println("Pre Init");
		MinecraftForge.EVENT_BUS.register(new LightsaberEventHandler());
		LightsaberItems.init();
		LightsaberItems.register();
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageJediHutHandler());
		MapGenStructureIO.registerStructureComponent(StructureVillageJediHut.class, Reference.MOD_ID+":jediHutStructure");
		LightsaberVillagers.registerVillagers();
		LightsaberAchievements.regsiterAchievements();
		LightsaberAchievements.registerPage();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		System.out.println("Init");
		proxy.init();
		LightsaberSoundHandler.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		System.out.println("Post Init");
		LightsaberShapedRecipes.loadRecipes();
		
	}
}
