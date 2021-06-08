package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.TntBlock;
import net.minecraft.block.WetSpongeBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class LightsaberDispenserBehavior {
	
	public static void init() {

		for(Identifier location : Registry.ITEM.getIds()) {
			Item item = Registry.ITEM.get(location);
			if(item instanceof ItemLightsaber) {
				DispenserBlock.registerBehavior(item, new FallibleItemDispenserBehavior() {
			         protected ItemStack dispenseSilently(BlockPointer source, ItemStack stack) {
			            World world = source.getWorld();
			            this.success = true;
			            BlockPos blockpos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
			            BlockState blockstate = world.getBlockState(blockpos);
			            if(blockstate.getBlock() instanceof TntBlock && blockstate.getProperties().contains(TntBlock.UNSTABLE) && !blockstate.get(TntBlock.UNSTABLE)) {
			    			try {
			    				((TntBlock) blockstate.getBlock()).primeTnt(world, blockpos);
			    				world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
			    			} catch(Exception e) {
			    			}
			    		} else if(world.getBlockState(blockpos).getBlock() instanceof WetSpongeBlock) {
			    			world.setBlockState(blockpos, Blocks.SPONGE.getDefaultState(), 3);
			    		} else {
			               this.success = false;
			            }

			            return stack;
			         }
			      });
			}
		}

	}
}