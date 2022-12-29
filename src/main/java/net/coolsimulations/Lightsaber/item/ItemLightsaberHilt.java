package net.coolsimulations.Lightsaber.item;

import net.coolsimulations.Lightsaber.block.LightsaberSconce;
import net.coolsimulations.Lightsaber.block.tileentity.TileEntityLightsaberSconce;
import net.coolsimulations.Lightsaber.init.LightsaberBlocks;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPBlocks;
import net.coolsimulations.SurvivalPlus.api.blocks.SPBlockSconce;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLightsaberHilt extends Item{

	public ItemLightsaberHilt(){
		this.maxStackSize = 1;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){

		ItemStack itemStackIn = playerIn.getHeldItem(handIn);
		Item item = itemStackIn.getItem();
		NBTTagCompound tag = item.getNBTShareTag(itemStackIn);

		ItemStack red = new ItemStack(LightsaberItems.red_lightsaber);
		red.setTagCompound(tag);

		ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber);
		blue.setTagCompound(tag);

		ItemStack green = new ItemStack(LightsaberItems.green_lightsaber);
		green.setTagCompound(tag);
		
		ItemStack yellow = new ItemStack(LightsaberItems.yellow_lightsaber);
		yellow.setTagCompound(tag);

		ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber);
		purple.setTagCompound(tag);

		ItemStack white = new ItemStack(LightsaberItems.white_lightsaber);
		white.setTagCompound(tag);

		ItemStack dark = new ItemStack(LightsaberItems.darksaber);
		dark.setTagCompound(tag);

		if(item == LightsaberItems.red_lightsaber_hilt){

			if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
				playerIn.setHeldItem(EnumHand.OFF_HAND, red);
			}
			else
			{
				playerIn.setHeldItem(EnumHand.MAIN_HAND, red);
			}
			worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.blue_lightsaber_hilt){

			if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
				playerIn.setHeldItem(EnumHand.OFF_HAND, blue);
			}
			else
			{
				playerIn.setHeldItem(EnumHand.MAIN_HAND, blue);
			}
			worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.green_lightsaber_hilt){

			if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
				playerIn.setHeldItem(EnumHand.OFF_HAND, green);
			}
			else
			{
				playerIn.setHeldItem(EnumHand.MAIN_HAND, green);
			}
			worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.yellow_lightsaber_hilt){

			if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
				playerIn.setHeldItem(EnumHand.OFF_HAND, yellow);
			}
			else
			{
				playerIn.setHeldItem(EnumHand.MAIN_HAND, yellow);
			}
			worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.purple_lightsaber_hilt){

			if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
				playerIn.setHeldItem(EnumHand.OFF_HAND, purple);
			}
			else
			{
				playerIn.setHeldItem(EnumHand.MAIN_HAND, purple);
			}
			worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.white_lightsaber_hilt){

			if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
				playerIn.setHeldItem(EnumHand.OFF_HAND, white);
			}
			else
			{
				playerIn.setHeldItem(EnumHand.MAIN_HAND, white);
			}
			worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		if(item == LightsaberItems.darksaber_hilt){

			if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
				playerIn.setHeldItem(EnumHand.OFF_HAND, dark);
			}
			else
			{
				playerIn.setHeldItem(EnumHand.MAIN_HAND, dark);
			}
			worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.darksaber_on, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		
		if (block == SPBlocks.sconce && block.hasTileEntity(state)) {
			TileEntity te = worldIn.getTileEntity(pos);
			NBTTagCompound tag = te.getUpdateTag();
			if (tag.hasKey("torch")) {
				int torch = tag.getInteger("torch");
				if (torch == 0) {
					worldIn.removeTileEntity(pos);
					worldIn.setBlockState(pos, LightsaberBlocks.sconce.getDefaultState().withProperty(LightsaberSconce.FACING, state.getValue(SPBlockSconce.FACING)).withProperty(LightsaberSconce.DARKSABER, this == LightsaberItems.darksaber_hilt));
					worldIn.getBlockState(pos).getBlock().createTileEntity(worldIn, worldIn.getBlockState(pos));
					TileEntityLightsaberSconce sconce = (TileEntityLightsaberSconce) worldIn.getTileEntity(pos);
					sconce.setStack(player.getHeldItem(hand).writeToNBT(new NBTTagCompound()));
					worldIn.playSound(null, player.getPosition(), LightsaberBlocks.sconce.getSoundType(worldIn.getBlockState(pos), worldIn, pos, player).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
					if (!player.capabilities.isCreativeMode)
						player.getHeldItem(hand).shrink(1);
					return EnumActionResult.SUCCESS;
				}
			}
		}
		
		return EnumActionResult.PASS;
	}
}
