package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.WetSpongeBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LightsaberDispenserBehavior {
	
	public static void init() {

		for(ResourceLocation location : Registry.ITEM.keySet()) {
			Item item = Registry.ITEM.get(location);
			if(item instanceof ItemLightsaber) {
				DispenserBlock.registerBehavior(item, new OptionalDispenseItemBehavior() {
			         protected ItemStack execute(BlockSource source, ItemStack stack) {
			            Level world = source.getLevel();
			            this.setSuccess(true);
			            BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
			            BlockState blockstate = world.getBlockState(blockpos);
			            if(blockstate.getBlock() instanceof TntBlock && blockstate.getProperties().contains(TntBlock.UNSTABLE) && !blockstate.getValue(TntBlock.UNSTABLE)) {
			    			try {
			    				((TntBlock) blockstate.getBlock()).explode(world, blockpos);
			    				world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 11);
			    			} catch(Exception e) {
			    			}
			    		} else if(world.getBlockState(blockpos).getBlock() instanceof WetSpongeBlock) {
			    			world.setBlock(blockpos, Blocks.SPONGE.defaultBlockState(), 3);
			    		} else {
			    			this.setSuccess(false);
			            }

			            return stack;
			         }
			      });
			}
		}

	}
}
