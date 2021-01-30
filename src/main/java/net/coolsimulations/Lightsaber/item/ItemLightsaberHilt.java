package net.coolsimulations.Lightsaber.item;

import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemLightsaberHilt extends Item{

	public ItemLightsaberHilt(FabricItemSettings properties){
		super(properties.maxCount(1));
	}

	@Override
	public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

		ItemStack itemStackIn = playerIn.getStackInHand(handIn);
		Item item = itemStackIn.getItem();
		CompoundTag tag = itemStackIn.getTag();

		ItemStack red = new ItemStack(LightsaberItems.red_lightsaber);
		red.setTag(tag);

		ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber);
		blue.setTag(tag);

		ItemStack green = new ItemStack(LightsaberItems.green_lightsaber);
		green.setTag(tag);
		
		ItemStack yellow = new ItemStack(LightsaberItems.yellow_lightsaber);
		yellow.setTag(tag);

		ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber);
		purple.setTag(tag);

		ItemStack white = new ItemStack(LightsaberItems.white_lightsaber);
		white.setTag(tag);

		ItemStack dark = new ItemStack(LightsaberItems.darksaber);
		dark.setTag(tag);

		if(item == LightsaberItems.red_lightsaber_hilt){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, red);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, red);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.blue_lightsaber_hilt){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, blue);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, blue);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.green_lightsaber_hilt){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, green);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, green);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.yellow_lightsaber_hilt){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, yellow);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, yellow);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.purple_lightsaber_hilt){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, purple);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, purple);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.white_lightsaber_hilt){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, white);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, white);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.darksaber_hilt){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, dark);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, dark);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.darksaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemStackIn);
	}
}
