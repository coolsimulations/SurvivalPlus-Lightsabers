package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.Reference;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;;

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
		size = Registry.SOUND_EVENT.entrySet().size();
		
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
		
		Registry.register(Registry.SOUND_EVENT, name, e);
		return e;
	}

}
