package net.coolsimulations.Lightsaber.util;

import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;

public class LightsaberEMI implements EmiPlugin {
	
	@Override
    public void register(EmiRegistry registry) {

		EmiApi.getIndexStacks().stream().filter(stack->stack.getItemStack().getItem() instanceof ItemLightsaber).forEach(stack->registry.removeEmiStacks(stack));
    }

}
