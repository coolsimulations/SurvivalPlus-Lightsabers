package net.coolsimulations.Lightsaber.proxy;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void init(){
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::registerProperties);
	}
	
	@SubscribeEvent
	public static void registerProperties(FMLClientSetupEvent event)
	{
		ItemModelsProperties.register(LightsaberItems.red_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.register(LightsaberItems.blue_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.register(LightsaberItems.green_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.register(LightsaberItems.yellow_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.register(LightsaberItems.purple_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.register(LightsaberItems.white_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.register(LightsaberItems.darksaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
	}

}
