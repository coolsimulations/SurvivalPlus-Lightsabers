package net.coolsimulations.Lightsaber.item;

import java.util.Random;

import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ItemLightsaberHilt extends Item{
	
	public ItemLightsaberHilt(Item.Properties properties){
		super(properties.maxStackSize(1));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		
		
		ItemStack itemStackIn = playerIn.getHeldItem(handIn);
    	Item item = itemStackIn.getItem();
    	CompoundNBT tag = item.getShareTag(itemStackIn);
    	
    	ItemStack red = new ItemStack(LightsaberItems.red_lightsaber);
    	red.setTag(tag);
    	
    	ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber);
    	blue.setTag(tag);
    	
    	ItemStack green = new ItemStack(LightsaberItems.green_lightsaber);
    	green.setTag(tag);
    	
    	ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber);
    	purple.setTag(tag);
    	
    	ItemStack white = new ItemStack(LightsaberItems.white_lightsaber);
    	white.setTag(tag);
    	
    	ItemStack dark = new ItemStack(LightsaberItems.darksaber);
    	dark.setTag(tag);

    	if(item == LightsaberItems.red_lightsaber_hilt){
    		
    		if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
    			playerIn.setHeldItem(Hand.OFF_HAND, red);
			}
			else
			{
    			playerIn.setHeldItem(Hand.MAIN_HAND, red);
			}
		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.BLOCKS, 1.0F, 1.0F);
    	}
    	if(item == LightsaberItems.blue_lightsaber_hilt){

    		if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
    			playerIn.setHeldItem(Hand.OFF_HAND, blue);
			}
			else
			{
    			playerIn.setHeldItem(Hand.MAIN_HAND, blue);
			}
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    	if(item == LightsaberItems.green_lightsaber_hilt){

    		if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
    			playerIn.setHeldItem(Hand.OFF_HAND, green);
			}
			else
			{
    			playerIn.setHeldItem(Hand.MAIN_HAND, green);
			}
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    	if(item == LightsaberItems.purple_lightsaber_hilt){

    		if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
    			playerIn.setHeldItem(Hand.OFF_HAND, purple);
			}
			else
			{
    			playerIn.setHeldItem(Hand.MAIN_HAND, purple);
			}
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    	if(item == LightsaberItems.white_lightsaber_hilt){

    		if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
    			playerIn.setHeldItem(Hand.OFF_HAND, white);
			}
			else
			{
    			playerIn.setHeldItem(Hand.MAIN_HAND, white);
			}
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    	if(item == LightsaberItems.darksaber_hilt){

    		if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
    			playerIn.setHeldItem(Hand.OFF_HAND, dark);
			}
			else
			{
    			playerIn.setHeldItem(Hand.MAIN_HAND, dark);
			}
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.darksaber_on, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStackIn);
	}
}
