package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.BlockTNT;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightsaberDispenserBehavior {

	public static void init() {

		for(ResourceLocation location : Item.REGISTRY.getKeys()) {
			Item item = Item.REGISTRY.getObject(location);
			if(item instanceof ItemLightsaber) {
				BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(item, new Bootstrap.BehaviorDispenseOptional()
				{
					/**
					 * Dispense the specified stack, play the dispense sound and spawn particles.
					 */
					protected ItemStack dispenseStack(IBlockSource source, ItemStack stack)
					{
						World world = source.getWorld();
						this.successful = true;
						BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(BlockDispenser.FACING));

						if (world.getBlockState(blockpos).getBlock() instanceof BlockTNT)
						{
							Block tnt = world.getBlockState(blockpos).getBlock();
							tnt.onBlockDestroyedByPlayer(world, blockpos, tnt.getDefaultState().withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
							world.setBlockToAir(blockpos);
						} else if (world.getBlockState(blockpos).getBlock() instanceof BlockSponge) {
							world.setBlockState(blockpos, world.getBlockState(blockpos).withProperty(BlockSponge.WET, false), 3);
						}
						else
						{
							this.successful = false;
						}

						return stack;
					}
				});
			}
		}

	}
}