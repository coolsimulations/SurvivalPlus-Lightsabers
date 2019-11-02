package net.coolsimulations.Lightsaber.init;

import java.io.InputStream;

import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

@SuppressWarnings("restriction")
public class LightsaberEventHandler {
	
	@SubscribeEvent
	public void onplayerLogin(PlayerLoggedInEvent event)
    {
		EntityPlayerMP player = (EntityPlayerMP) event.player;
		
		try
    	{
			InputStream sound = getClass().getClassLoader().getResourceAsStream("assets/" + Reference.MOD_ID + "/sounds/misc/hello_there.wav");
	     	AudioStream audioStream = new AudioStream(sound);
	     	AudioPlayer.player.start(audioStream);
	   	}
	    	catch (Exception e)
	   	{
	    	System.err.println(e);
	    }
		
		if (!player.hasAchievement(LightsaberAchievements.achievementInstall))
        {
            player.addStat(LightsaberAchievements.achievementInstall);
            
            if(!player.world.isRemote) {
            	
            	TextComponentTranslation installInfo = new TextComponentTranslation("achievement.lightsaber.install.display1");
            	installInfo.getStyle().setColor(TextFormatting.GOLD);
				player.sendMessage(installInfo);

            }
        }
        
        if(LightsaberUpdateHandler.isOld == true && SPConfig.disableUpdateCheck == false) {
        	player.sendMessage(LightsaberUpdateHandler.updateInfo);
        	player.sendMessage(LightsaberUpdateHandler.updateVersionInfo);
        }
    }
	
	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event)
	{
		EntityPlayer player = (EntityPlayer) event.player;
		Item item = event.crafting.getItem();
		
		if(item == LightsaberItems.lightsaber_hilt) {
			
			if(!player.hasAchievement(LightsaberAchievements.achievementLightsaber)) {
				player.addStat(LightsaberAchievements.achievementLightsaber);
			}
		}
	}


}
