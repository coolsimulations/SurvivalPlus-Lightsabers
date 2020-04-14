package net.coolsimulations.Lightsaber.init;

import java.util.Random;

import net.coolsimulations.Lightsaber.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class LightsaberSoundHandler {
	
	public static int size = 0;
	
	public static SoundEvent lightsaber_on;
	public static SoundEvent lightsaber_off;
	public static SoundEvent lightsaber_swing;
	public static SoundEvent lightsaber_hit;
	
	public static SoundEvent darksaber_on;
	public static SoundEvent darksaber_off;
	public static SoundEvent darksaber_swing;
	public static SoundEvent darksaber_hit;
	
	public static SoundEvent hello_there;
	
	public static void init() {
		size = SoundEvent.REGISTRY.getKeys().size();
		
		lightsaber_on = register("item.lightsaber.lightsaber_on");
		lightsaber_off = register("item.lightsaber.lightsaber_off");
		lightsaber_swing = register("item.lightsaber.lightsaber_swing");
		lightsaber_hit = register("item.lightsaber.lightsaber_hit");
		
		darksaber_off = register("item.lightsaber.darksaber_off");
		darksaber_on = register("item.lightsaber.darksaber_on");
		darksaber_swing = register("item.lightsaber.darksaber_swing");
		darksaber_hit = register("item.lightsaber.darksaber_hit");
		
		hello_there = register("misc.hello_there");
	}	
	
	public static SoundEvent register(String name){
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
		SoundEvent e = new SoundEvent(location);
		e.setRegistryName(name);
		
		ForgeRegistries.SOUND_EVENTS.register(e);
		return e;
	}

}
