package net.coolsimulations.Lightsaber.util;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.world.item.ItemStack;

public class LightsaberREI implements REIClientPlugin {
	
	@Override
    public void registerEntries(EntryRegistry registry) {
		
		EntryRegistry.getInstance().getEntryStacks().filter(stack->((ItemStack) stack.getValue()).getItem().asItem() instanceof ItemLightsaber).forEach(stack->EntryRegistry.getInstance().removeEntry(stack));
    }

}
