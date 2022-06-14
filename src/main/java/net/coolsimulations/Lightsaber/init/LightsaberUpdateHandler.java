package net.coolsimulations.Lightsaber.init;

import java.net.URL;
import java.util.Scanner;

import net.coolsimulations.Lightsaber.Reference;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.versions.mcp.MCPVersion;

public class LightsaberUpdateHandler {
	
	private static String latestVersion;
	private static String latestVersionInfo;
	public static boolean isOld = false;
	public static MutableComponent updateInfo = null;
	public static MutableComponent updateVersionInfo = null;
	
	public static void init() {
		
		try {
            URL url = new URL("https://coolsimulations.net/mcmods/lightsaber/versionchecker119.txt");
            Scanner s = new Scanner(url.openStream());
            latestVersion = s.next();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		try {
            URL url = new URL("https://coolsimulations.net/mcmods/lightsaber/updateinfo119.txt");
            Scanner s = new Scanner(url.openStream());
            latestVersionInfo = s.nextLine();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		if(latestVersion != null) {
			
			if(latestVersion.equals("ended")) {
				
				isOld = true;
				
				MutableComponent lightsaber = Component.translatable("sp.lightsaber.name");
				lightsaber.withStyle(ChatFormatting.BLUE);
				
				MutableComponent MCVersion = Component.literal(MCPVersion.getMCVersion());
				MCVersion.withStyle(ChatFormatting.BLUE);
				
				updateInfo = Component.translatable("sp.update.display3", new Object[] {lightsaber, MCVersion});
				updateInfo.withStyle(ChatFormatting.YELLOW);
				
				//updateInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("sp.update.display2")));
				//updateInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers"));
				
			}
			
			if(!latestVersion.equals(Reference.VERSION) && !latestVersion.equals("ended")) {
				
				isOld = true;
				
				MutableComponent lightsaber = Component.translatable("sp.lightsaber.name");
				lightsaber.withStyle(ChatFormatting.BLUE);
				
				MutableComponent version = Component.literal(latestVersion);
				version.withStyle(ChatFormatting.BLUE);
				
				updateInfo = Component.translatable("sp.update.display1", new Object[] {lightsaber, version});
				updateInfo.withStyle(ChatFormatting.YELLOW);
				
				//updateInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("sp.update.display2")));
				//updateInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers"));
				
				if(latestVersionInfo != null) {
					
					updateVersionInfo = Component.literal(latestVersionInfo);
					updateVersionInfo.withStyle(ChatFormatting.DARK_AQUA);
					updateVersionInfo.withStyle(ChatFormatting.BOLD);
					
					//updateVersionInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("sp.update.display2")));
					//updateVersionInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://curseforge.com/minecraft/mc-mods/survivalplus-lightsabers"));
					
				}
				
			}
			
		}
	}

}
