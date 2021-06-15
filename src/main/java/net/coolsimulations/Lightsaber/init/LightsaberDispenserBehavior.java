package net.coolsimulations.Lightsaber.init;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.TNTBlock;
import net.minecraft.block.WetSpongeBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class LightsaberDispenserBehavior {
	
	public static void init() {

		for(ResourceLocation location : ForgeRegistries.ITEMS.getKeys()) {
			Item item = ForgeRegistries.ITEMS.getValue(location);
			if(item instanceof ItemLightsaber) {
				DispenserBlock.registerBehavior(item, new OptionalDispenseBehavior() {
			         protected ItemStack execute(IBlockSource source, ItemStack stack) {
			            World world = source.getLevel();
			            this.setSuccess(true);
			            BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
			            BlockState blockstate = world.getBlockState(blockpos);
			            if(blockstate.getBlock() instanceof TNTBlock && blockstate.getProperties().contains(TNTBlock.UNSTABLE) && !blockstate.getValue(TNTBlock.UNSTABLE)) {
			    			try {
			    				((TNTBlock) blockstate.getBlock()).explode(world, blockpos);
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