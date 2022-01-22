package net.coolsimulations.Lightsaber.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.coolsimulations.Lightsaber.Reference;
import net.coolsimulations.SurvivalPlus.api.SPReference;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LightsaberConfigGUI {

	public static Screen getConfigScreen(Screen parent)
	{
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(new TranslatableComponent("lightsaber.configgui.title"));

		builder.setSavingRunnable(() -> {
			LightsaberConfig.save(LightsaberConfig.getFile(), LightsaberConfig.getObject());
			LightsaberConfig.load(LightsaberConfig.getFile());
		});

		builder.setDefaultBackgroundTexture(new ResourceLocation(SPReference.MOD_ID + ":textures/blocks/titanium_block.png"));

		ConfigCategory values = builder.getOrCreateCategory(new TranslatableComponent(Reference.CONFIG_CATEGORY_VALUES));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		values.addEntry(entryBuilder.startDoubleField(new TranslatableComponent("lightsaber.configgui.general_lightsaber_damage"), LightsaberConfig.generalLightsaberDamage).setDefaultValue(20.0D).setSaveConsumer(newValue->LightsaberConfig.generalLightsaberDamage = newValue).build());
		values.addEntry(entryBuilder.startDoubleField(new TranslatableComponent("lightsaber.configgui.general_lightsaber_speed"), LightsaberConfig.generalLightsaberSpeed).setDefaultValue(2.8D).setSaveConsumer(newValue->LightsaberConfig.generalLightsaberSpeed = newValue).build());
		values.addEntry(entryBuilder.startDoubleField(new TranslatableComponent("lightsaber.configgui.darksaber_damage"), LightsaberConfig.darksaberDamage).setDefaultValue(30.0D).setSaveConsumer(newValue->LightsaberConfig.darksaberDamage = newValue).build());
		values.addEntry(entryBuilder.startDoubleField(new TranslatableComponent("lightsaber.configgui.darksaber_speed"), LightsaberConfig.darksaberSpeed).setDefaultValue(2.8D).setSaveConsumer(newValue->LightsaberConfig.darksaberSpeed = newValue).build());
		values.addEntry(entryBuilder.startDoubleField(new TranslatableComponent("lightsaber.configgui.white_lightsaber_damage"), LightsaberConfig.whiteLightsaberDamage).setDefaultValue(24.0D).setSaveConsumer(newValue->LightsaberConfig.whiteLightsaberDamage = newValue).build());
		values.addEntry(entryBuilder.startDoubleField(new TranslatableComponent("lightsaber.configgui.white_lightsaber_speed"), LightsaberConfig.whiteLightsaberSpeed).setDefaultValue(2.8D).setSaveConsumer(newValue->LightsaberConfig.whiteLightsaberSpeed = newValue).build());
		values.addEntry(entryBuilder.startDoubleField(new TranslatableComponent("lightsaber.configgui.purple_lightsaber_damage"), LightsaberConfig.purpleLightsaberDamage).setDefaultValue(22.0D).setSaveConsumer(newValue->LightsaberConfig.purpleLightsaberDamage = newValue).build());
		values.addEntry(entryBuilder.startDoubleField(new TranslatableComponent("lightsaber.configgui.purple_lightsaber_speed"), LightsaberConfig.purpleLightsaberSpeed).setDefaultValue(2.8D).setSaveConsumer(newValue->LightsaberConfig.purpleLightsaberSpeed = newValue).build());

		return builder.build();
	}
}
