package net.coolsimulations.Lightsaber.item;

import net.coolsimulations.Lightsaber.block.LightsaberSconce;
import net.coolsimulations.Lightsaber.block.tileentity.TileEntityLightsaberSconce;
import net.coolsimulations.Lightsaber.init.LightsaberBlocks;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPBlocks;
import net.coolsimulations.SurvivalPlus.api.blocks.SPBlockSconce;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

public class ItemLightsaberHilt extends Item{

	public ItemLightsaberHilt(Item.Properties properties){
		super(properties.stacksTo(1));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn){


		ItemStack itemStackIn = playerIn.getItemInHand(handIn);
		Item item = itemStackIn.getItem();
		CompoundTag tag = item.getShareTag(itemStackIn);

		ItemStack red = new ItemStack(LightsaberItems.red_lightsaber.get());
		red.setTag(tag);

		ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber.get());
		blue.setTag(tag);

		ItemStack green = new ItemStack(LightsaberItems.green_lightsaber.get());
		green.setTag(tag);

		ItemStack yellow = new ItemStack(LightsaberItems.yellow_lightsaber.get());
		yellow.setTag(tag);

		ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber.get());
		purple.setTag(tag);

		ItemStack white = new ItemStack(LightsaberItems.white_lightsaber.get());
		white.setTag(tag);

		ItemStack dark = new ItemStack(LightsaberItems.darksaber.get());
		dark.setTag(tag);

		if(item == LightsaberItems.red_lightsaber_hilt.get()){

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
		if(item == LightsaberItems.blue_lightsaber_hilt.get()){

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
		if(item == LightsaberItems.green_lightsaber_hilt.get()){

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
		if(item == LightsaberItems.yellow_lightsaber_hilt.get()){

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
		if(item == LightsaberItems.purple_lightsaber_hilt.get()){

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
		if(item == LightsaberItems.white_lightsaber_hilt.get()){

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
		if(item == LightsaberItems.darksaber_hilt.get()){

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
		worldIn.gameEvent(playerIn, GameEvent.EQUIP, playerIn.blockPosition());
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, itemStackIn);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();
		InteractionHand hand = context.getHand();
		BlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		
		if (block == SPBlocks.sconce.get() && state.hasBlockEntity()) {
			BlockEntity te = worldIn.getBlockEntity(pos);
			CompoundTag tag = te.getUpdateTag();
			if (tag.contains("torch")) {
				int torch = tag.getInt("torch");
				if (torch == 0) {
					worldIn.removeBlockEntity(pos);
					worldIn.setBlock(pos, LightsaberBlocks.sconce.get().defaultBlockState().setValue(LightsaberSconce.FACING, state.getValue(SPBlockSconce.FACING)).setValue(LightsaberSconce.DARKSABER, this == LightsaberItems.darksaber_hilt.get()).setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)), 3);
					((LightsaberSconce) worldIn.getBlockState(pos).getBlock()).newBlockEntity(pos, worldIn.getBlockState(pos));
					TileEntityLightsaberSconce sconce = (TileEntityLightsaberSconce) worldIn.getBlockEntity(pos);
					sconce.setStack(player.getItemInHand(hand).save(new CompoundTag()));
					worldIn.playSound(null, pos, LightsaberBlocks.sconce.get().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player).getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
					if (!player.isCreative())
						player.getItemInHand(hand).shrink(1);
					return InteractionResult.sidedSuccess(worldIn.isClientSide);
				}
			}
		}
		
		return InteractionResult.PASS;

	}
}
