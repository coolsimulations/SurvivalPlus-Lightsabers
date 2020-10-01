package net.coolsimulations.Lightsaber.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import codersafterdark.reskillable.base.LevelLockHandler;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.coolsimulations.Lightsaber.item.ItemLightsaberHilt;
import net.coolsimulations.SurvivalPlus.api.SPItems;
import net.coolsimulations.SurvivalPlus.api.item.SPItemAxe;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class LightsaberSkills {
	
	protected static List<String> skills = new ArrayList<String>();
	public static boolean isNotBlacklist;
	
	public static void initReskillable(FMLPreInitializationEvent event) {
		
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "reskillable.cfg"));
		
		String desc = "Set requirements for items in this list. Each entry is composed of the item key and the requirements\nThe item key is in the simple mod:item_id format. Optionally, it can be in mod:item_id:metadata, if you want to match metadata.\nThe requirements are in a comma separated list, each in a key|value format. For example, to make an iron pickaxe require 5 mining\nand 5 building, you'd use the following string:\n\"minecraft:iron_pickaxe=mining|5,building|5\"\n\nItem usage can also be locked behind an advancement, by using adv|id. For example, to make the elytra require the \"Acquire Hardware.\" advancement\nyou'd use the following string:\n\"minecraft:elytra=adv|minecraft:story/smelt_iron\"\n\nSkill requirements and advancements can be mixed and matched, so you can make an item require both, if you want.\nYou can also lock placed blocks from being used or broken, in the same manner.\n\nLocks defined here apply to all the following cases: Right clicking an item, placing a block, breaking a block, using a block that's placed,\nleft clicking an item, using an item to break any block, and equipping an armor item.\n\nYou can lock entire mods by just using their name as the left argument. You can then specify specific items to not be locked,\nby defining their lock in the normal way. If you want an item to not be locked in this way, use \"none\" after the =";
		Property itemsList = config.get(Configuration.CATEGORY_GENERAL, "Skill Locks", LevelLockHandler.DEFAULT_SKILL_LOCKS, desc);
		
		List<String> locksList = new ArrayList<String>(Arrays.asList(itemsList.getStringList()));
		skills = locksList;
		
		Property blacklist = config.get(Configuration.CATEGORY_GENERAL, "Enable Automatic SurvivalPlus Entries", true, "SurvivalPlus automatically adds it's values based on the set Iron ones, turn this off to remove SurvivalPlus entries from the Skill Locks list");
		isNotBlacklist = blacklist.getBoolean(true);

		addItemDynamic(locksList, LightsaberItems.red_lightsaber, "attack", 4, "magic", "diamond", 20);
		addItemDynamic(locksList, LightsaberItems.blue_lightsaber, "attack", 4, "magic", "diamond", 20);
		addItemDynamic(locksList, LightsaberItems.green_lightsaber, "attack", 4, "magic", "diamond", 20);
		addItemDynamic(locksList, LightsaberItems.yellow_lightsaber, "attack", 4, "magic", "diamond", 20);
		addItemDynamic(locksList, LightsaberItems.purple_lightsaber, "attack", 6, "magic", "diamond", 22);
		addItemDynamic(locksList, LightsaberItems.white_lightsaber, "attack", 8, "magic", "diamond", 24);
		addItemDynamic(locksList, LightsaberItems.darksaber, "attack", 12, "magic", "diamond", 28);
		
		addItemDynamic(locksList, LightsaberItems.red_lightsaber_hilt, "attack", 4, "magic", "diamond", 20);
		addItemDynamic(locksList, LightsaberItems.blue_lightsaber_hilt, "attack", 4, "magic", "diamond", 20);
		addItemDynamic(locksList, LightsaberItems.green_lightsaber_hilt, "attack", 4, "magic", "diamond", 20);
		addItemDynamic(locksList, LightsaberItems.yellow_lightsaber_hilt, "attack", 4, "magic", "diamond", 20);
		addItemDynamic(locksList, LightsaberItems.purple_lightsaber_hilt, "attack", 6, "magic", "diamond", 22);
		addItemDynamic(locksList, LightsaberItems.white_lightsaber_hilt, "attack", 8, "magic", "diamond", 24);
		addItemDynamic(locksList, LightsaberItems.darksaber_hilt, "attack", 12, "magic", "diamond", 28);
		
		itemsList.set(skills.toArray(new String[0]));;

		config.save();
	}
	
	protected static void addItem(List<String> originalList, Item item, String typeOne, int levelOne, String typeTwo, int levelTwo) {
		
		if(!checkList(originalList, item) && isNotBlacklist) {
			skills.add(item.getRegistryName() + ":*=reskillable:" + typeOne + "|" + levelOne + ",reskillable:" + typeTwo + "|" + levelTwo);
		}	
	}
	
	protected static void addItemDynamic(List<String> originalList, Item item, String typeOne, int levelDifference, String typeTwo, String material, int backupDefault) {
		
		int originalLevel = getIntFromMaterial(originalList, item, material);
		int finalInt;
		
		if((originalLevel + levelDifference) < 0)
			finalInt = backupDefault;
		else
			finalInt = originalLevel + levelDifference;
		
		addItem(originalList, item, typeOne, finalInt, typeTwo, finalInt);
	}
	
	protected static boolean checkList(List<String> list, Item item) {
		
		for(int i = 0; i < list.size(); i++ ) {
			
			if(list.get(i).contains(item.getRegistryName().toString()))
				return true;
		}
		
		return false;
	}
	
	protected static String checkListString(List<String> list, Item item) {
		
		for(int i = 0; i < list.size(); i++ ) {
			
			if(list.get(i).contains(item.getRegistryName().toString()))
				return list.get(i);
		}
		
		return "";
	}
	
	protected static int getIntFromString(String skill, Item checkedItem) {
		
		if(StringUtils.countMatches(skill, "reskillable") == 1) {
			String edited = removeItem(removeSkill(removePrefix(skill)), checkedItem);
			return Integer.parseInt(edited);
		}
		
		if(StringUtils.countMatches(skill, "reskillable") == 2) {
			String edited = removeItem(removeSkill(removePrefix(skill)), checkedItem);
			if(edited.length() == 2) {
				int first = Character.getNumericValue((edited.charAt(0)));
				int second = Character.getNumericValue((edited.charAt(1)));
				if(first == second)
					return first;
				else
					return first < second ? second : second < first ? first : 1;
			} else if(edited.length() == 3) {
				int first = Character.getNumericValue(edited.charAt(0)) + Character.getNumericValue(edited.charAt(1));
				int second = Character.getNumericValue(edited.charAt(1)) + Character.getNumericValue(edited.charAt(2));
				if(first == second)
					return first;
				else
					return first < second ? second : second < first ? first : 1;
			} else if(edited.length() == 4) {
				int first = Character.getNumericValue(edited.charAt(0)) + Character.getNumericValue(edited.charAt(1));
				int second = Character.getNumericValue(edited.charAt(2)) + Character.getNumericValue(edited.charAt(3));
				if(first == second)
					return first;
				else
					return first < second ? second : second < first ? first : 1;
			}
		}
		
		return 1;
	}
	
	protected static String removePrefix(String skill) {
		
		String edited = skill;
		
		edited = edited.replace(":*=reskillable:", "");
		edited = edited.replace("|", "");
		if(edited.contains(",reskillable:"))
			edited = edited.replace(",reskillable:", "");
		
		return edited;
	}
	
	protected static String removeItem(String skill, Item item) {
		
		String edited = skill;
		
		edited = edited.replace(item.getRegistryName().toString(), "");
		
		return edited;
	}
	
	protected static String removeSkill(String skill) {
		
		String edited = skill;
		
		if(edited.contains("gathering"))
			edited = edited.replace("gathering", "");
		if(edited.contains("mining"))
			edited = edited.replace("mining", "");
		if(edited.contains("farming"))
			edited = edited.replace("farming", "");
		if(edited.contains("attack"))
			edited = edited.replace("attack", "");
		if(edited.contains("defense"))
			edited = edited.replace("defense", "");
		if(edited.contains("agility"))
			edited = edited.replace("agility", "");
		if(edited.contains("magic"))
			edited = edited.replace("magic", "");
		if(edited.contains("building"))
			edited = edited.replace("building", "");
		
		return edited;
	}
	
	protected static int getIntFromMaterial(List<String> list, Item item, String material) {
		
		if(material.toLowerCase() == "diamond") {
			if(item instanceof ItemLightsaber || item instanceof ItemLightsaberHilt && checkList(list, Items.DIAMOND_SWORD)) {
				String sword = checkListString(list, Items.DIAMOND_SWORD);
				return getIntFromString(sword, Items.DIAMOND_SWORD);
			}
		}
		
		return 1;
	}


}
