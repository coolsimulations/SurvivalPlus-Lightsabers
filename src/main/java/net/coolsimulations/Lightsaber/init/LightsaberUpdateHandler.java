package net.coolsimulations.Lightsaber.init;

import java.net.URL;
import java.util.Scanner;

import net.coolsimulations.Lightsaber.Reference;
import net.minecraft.SharedConstants;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class LightsaberUpdateHandler {
	
	private static String latestVersion;
	private static String latestVersionInfo;
	public static boolean isOld = false;
	public static TranslatableText updateInfo = null;
	public static LiteralText updateVersionInfo = null;
	
	public static void init() {
		
		try {
            URL url = new URL("https://coolsimulations.net/mcmods/lightsaber-fabric/versionchecker116.txt");
            Scanner s = new Scanner(url.openStream());
            latestVersion = s.next();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		try {
            URL url = new URL("https://coolsimulations.net/mcmods/lightsaber-fabric/updateinfo116.txt");
            Scanner s = new Scanner(url.openStream());
            latestVersionInfo = s.nextLine();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		if(latestVersion != null) {
			
			if(latestVersion.equals("ended")) {
				
				isOld = true;
				
				TranslatableText lightsaber = new TranslatableText("sp.lightsaber.name");
				lightsaber.formatted(Formatting.BLUE);
				
				LiteralText MCVersion = new LiteralText(SharedConstants.getGameVersion().getName());
				MCVersion.formatted(Formatting.BLUE);
				
				updateInfo = new TranslatableText("sp.update.display3", new Object[] {lightsaber, MCVersion});
				updateInfo.formatted(Formatting.YELLOW);
				
				//updateInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("sp.update.display2")));
				//updateInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"));
				
			}
			
			if(!latestVersion.equals(Reference.VERSION) && !latestVersion.equals("ended")) {
				
				isOld = true;
				
				TranslatableText lightsaber = new TranslatableText("sp.lightsaber.name");
				lightsaber.formatted(Formatting.BLUE);
				
				LiteralText version = new LiteralText(latestVersion);
				version.formatted(Formatting.BLUE);
				
				updateInfo = new TranslatableText("sp.update.display1", new Object[] {lightsaber, version});
				updateInfo.formatted(Formatting.YELLOW);
				
				//updateInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("sp.update.display2")));
				//updateInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"));
				
				if(latestVersionInfo != null) {
					
					updateVersionInfo = new LiteralText(latestVersionInfo);
					updateVersionInfo.formatted(Formatting.DARK_AQUA, Formatting.BOLD);
					
					//updateVersionInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("sp.update.display2")));
					//updateVersionInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://curseforge.com/minecraft/mc-mods/survivalplus-lightsabers-fabric"));
					
				}
				
			}
			
		}
	}

}
