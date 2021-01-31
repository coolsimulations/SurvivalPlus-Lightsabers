package net.coolsimulations.Lightsaber.init;

import me.crimsondawn45.fabricshieldlib.util.FabricShieldLibRegistry;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class LightsaberShieldLib {
	
	public static void init() {
		for(Item item : Registry.ITEM) {
			if(item instanceof ItemLightsaber)
				FabricShieldLibRegistry.registerShield(item);
		}
	}

}
