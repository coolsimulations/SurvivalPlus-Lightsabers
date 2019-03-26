package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.SurvivalPlus.api.SPAchievements;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class LightsaberAchievements {
	
	public static AchievementPage lightsaberPage;
	public static Achievement achievementInstall;
	public static Achievement achievementLightsaber;
	public static Achievement achievementDarksaber;
	
	public static void regsiterAchievements() {
		
		achievementInstall = new Achievement("achievement.lightsaber.install", "lightsaber.install", -3, 1, new ItemStack(LightsaberItems.lightsaber_hilt), SPAchievements.achievementInstall).setSpecial();
		achievementInstall.registerStat();
		
		achievementLightsaber = new Achievement("achievement.lightsaber.lightsaber", "lightsaber.lightsaber", -2, 2, new ItemStack(LightsaberItems.red_lightsaber_hilt), achievementInstall).registerStat();
		achievementDarksaber = new Achievement("achievement.lightsaber.darksaber", "lightsaber.darksaber", -2, -1, new ItemStack(LightsaberItems.darksaber_hilt), achievementInstall).registerStat();
		
		SPAchievements.spPage.getAchievements().add(achievementInstall);
		SPAchievements.spPage.getAchievements().add(achievementLightsaber);
		SPAchievements.spPage.getAchievements().add(achievementDarksaber);
	}

}
