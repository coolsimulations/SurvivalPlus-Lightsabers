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
		ItemModelsProperties.registerProperty(LightsaberItems.red_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isHandActive() && player.getActiveItemStack() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.registerProperty(LightsaberItems.blue_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isHandActive() && player.getActiveItemStack() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.registerProperty(LightsaberItems.green_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isHandActive() && player.getActiveItemStack() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.registerProperty(LightsaberItems.yellow_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isHandActive() && player.getActiveItemStack() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.registerProperty(LightsaberItems.purple_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isHandActive() && player.getActiveItemStack() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.registerProperty(LightsaberItems.white_lightsaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isHandActive() && player.getActiveItemStack() == stack ? 1.0F : 0.0F;});
		ItemModelsProperties.registerProperty(LightsaberItems.darksaber, new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player) -> {
	         return player != null && player.isHandActive() && player.getActiveItemStack() == stack ? 1.0F : 0.0F;});
	}

}
