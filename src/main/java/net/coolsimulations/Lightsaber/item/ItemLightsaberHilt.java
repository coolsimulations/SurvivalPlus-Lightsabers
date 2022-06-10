package net.coolsimulations.Lightsaber.item;

import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class ItemLightsaberHilt extends Item{

	public ItemLightsaberHilt(FabricItemSettings properties){
		super(properties.maxCount(1));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

		ItemStack itemStackIn = playerIn.getItemInHand(handIn);
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

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, red);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, red);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_on, SoundSource.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.blue_lightsaber_hilt){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, blue);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, blue);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_on, SoundSource.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.green_lightsaber_hilt){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, green);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, green);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_on, SoundSource.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.yellow_lightsaber_hilt){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, yellow);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, yellow);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_on, SoundSource.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.purple_lightsaber_hilt){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, purple);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, purple);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_on, SoundSource.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.white_lightsaber_hilt){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, white);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, white);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_on, SoundSource.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.darksaber_hilt){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, dark);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, dark);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.darksaber_on, SoundSource.HOSTILE, 1.0F, 1.0F);
		}
		worldIn.gameEvent(playerIn, GameEvent.EQUIP, playerIn.position());
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, itemStackIn);
	}
}
