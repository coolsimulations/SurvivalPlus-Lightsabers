package net.coolsimulations.Lightsaber.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LightsaberConfig {
	
	public static double generalLightsaberDamage;
	public static double generalLightsaberSpeed;
	
	public static double darksaberDamage;
	public static double darksaberSpeed;
	
	public static double whiteLightsaberDamage;
	public static double whiteLightsaberSpeed;
	
	public static double purpleLightsaberDamage;
	public static double purpleLightsaberSpeed;
	
	static File file;
	static JsonObject object;
	
	public static void init(File fileSrc) {

		generalLightsaberDamage = 20.0D;
		generalLightsaberSpeed = 2.8D;
		
		darksaberDamage = 30.0D;
		darksaberSpeed = 2.8D;
		
		whiteLightsaberDamage = 24.0D;
		whiteLightsaberSpeed = 2.8D;
		
		purpleLightsaberDamage = 22.0D;
		purpleLightsaberSpeed = 2.8D;

		JsonObject jsonObject = setJsonObject(new JsonObject());

		if(!fileSrc.exists() || fileSrc.length() <= 2) {
			save(fileSrc, jsonObject);
		} else {
			load(fileSrc);
		}
		
		file = fileSrc;
		object = jsonObject;

	}
	
	public static void save(File fileSrc, JsonObject object) {
		try {
			FileWriter file = new FileWriter(fileSrc);
			setJsonObject(object);
			file.write(new GsonBuilder().setPrettyPrinting().create().toJson(object));
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load(File fileSrc) {
		JsonParser parser = new JsonParser();
		try {
			Object obj = parser.parse(new FileReader(fileSrc));
			JsonObject jsonObjectRead = (JsonObject) obj;;
			generalLightsaberDamage = jsonObjectRead.get("generalLightsaberDamage").getAsDouble();
			generalLightsaberSpeed = jsonObjectRead.get("generalLightsaberSpeed").getAsDouble();
			
			darksaberDamage = jsonObjectRead.get("darksaberDamage").getAsDouble();
			darksaberSpeed = jsonObjectRead.get("darksaberSpeed").getAsDouble();
			
			whiteLightsaberDamage = jsonObjectRead.get("whiteLightsaberDamage").getAsDouble();
			whiteLightsaberSpeed = jsonObjectRead.get("whiteLightsaberSpeed").getAsDouble();
			
			purpleLightsaberDamage = jsonObjectRead.get("purpleLightsaberDamage").getAsDouble();
			purpleLightsaberSpeed = jsonObjectRead.get("purpleLightsaberSpeed").getAsDouble();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static JsonObject setJsonObject(JsonObject jsonObject) {
		
		jsonObject.addProperty("generalLightsaberDamage", generalLightsaberDamage);
		jsonObject.addProperty("generalLightsaberSpeed", generalLightsaberSpeed);
		
		jsonObject.addProperty("darksaberDamage", darksaberDamage);
		jsonObject.addProperty("darksaberSpeed", darksaberSpeed);
		
		jsonObject.addProperty("whiteLightsaberDamage", whiteLightsaberDamage);
		jsonObject.addProperty("whiteLightsaberSpeed", whiteLightsaberSpeed);
		
		jsonObject.addProperty("purpleLightsaberDamage", purpleLightsaberDamage);
		jsonObject.addProperty("purpleLightsaberSpeed", purpleLightsaberSpeed);
		
		return jsonObject;
	}
	
	public static File getFile() {
		return file;
	}
	
	public static JsonObject getObject() {
		return object;
	}

}
