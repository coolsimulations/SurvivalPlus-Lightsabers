package net.coolsimulations.Lightsaber.proxy;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance.Attenuation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void init(){
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::registerProperties);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public static void registerProperties(FMLClientSetupEvent event)
	{
		ItemProperties.register(LightsaberItems.red_lightsaber.get(), new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player, seed) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemProperties.register(LightsaberItems.blue_lightsaber.get(), new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player, seed) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemProperties.register(LightsaberItems.green_lightsaber.get(), new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player, seed) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemProperties.register(LightsaberItems.yellow_lightsaber.get(), new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player, seed) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemProperties.register(LightsaberItems.purple_lightsaber.get(), new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player, seed) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemProperties.register(LightsaberItems.white_lightsaber.get(), new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player, seed) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
		ItemProperties.register(LightsaberItems.darksaber.get(), new ResourceLocation(Reference.MOD_ID, "blocking"), (stack, world, player, seed) -> {
	         return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;});
	}
	
	@SubscribeEvent
	public void onPlayerJoinedServer(ClientPlayerNetworkEvent.LoggingIn event) {
		if(!SPConfig.disableClientAudio.get()) {
			SimpleSoundInstance sound = new SimpleSoundInstance(LightsaberSoundHandler.hello_there.getLocation(), SoundSource.VOICE, 0.25F, 1.0F, SoundInstance.createUnseededRandom(), false, 0, Attenuation.NONE, 0.0D, 0.0D, 0.0D, true);
			Minecraft.getInstance().getSoundManager().play(sound);
		}
	}

}
