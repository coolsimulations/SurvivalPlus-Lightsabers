package net.coolsimulations.Lightsaber.init;

import java.util.Random;

import net.coolsimulations.Lightsaber.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class LightsaberSoundHandler {
	
	public static int size = 0;
	
	public static SoundEvent lightsaber_on;
	public static SoundEvent lightsaber_off;
	public static SoundEvent lightsaber_swing;
	
	public static SoundEvent darksaber_on;
	public static SoundEvent darksaber_off;
	public static SoundEvent darksaber_swing;
	
	public static SoundEvent hello_there;
	
	public static void init() {
		size = SoundEvent.REGISTRY.getKeys().size();
		
		lightsaber_on = register("item.lightsaber.lightsaber_on");
		lightsaber_off = register("item.lightsaber.lightsaber_off");
		lightsaber_swing = register("item.lightsaber.lightsaber_swing");
		
		darksaber_off = register("item.lightsaber.darksaber_off");
		darksaber_on = register("item.lightsaber.darksaber_on");
		darksaber_swing = register("item.lightsaber.darksaber_swing");
		
		hello_there = register("misc.hello_there");
	}
	
	public static SoundEvent register(String name){
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
		SoundEvent e = new SoundEvent(location);
		
		SoundEvent.REGISTRY.register(size, location, e);
		size++;
		return e;
	}

}
