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
				DispenserBlock.registerDispenseBehavior(item, new OptionalDispenseBehavior() {
			         protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
			            World world = source.getWorld();
			            this.successful = true;
			            BlockPos blockpos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
			            BlockState blockstate = world.getBlockState(blockpos);
			            if(blockstate.getBlock() instanceof TNTBlock && blockstate.getProperties().contains(TNTBlock.UNSTABLE) && !blockstate.get(TNTBlock.UNSTABLE)) {
			    			try {
			    				((TNTBlock) blockstate.getBlock()).explode(world, blockpos);
			    				world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
			    			} catch(Exception e) {
			    			}
			    		} else if(world.getBlockState(blockpos).getBlock() instanceof WetSpongeBlock) {
			    			world.setBlockState(blockpos, Blocks.SPONGE.getDefaultState(), 3);
			    		} else {
			               this.successful = false;
			            }

			            return stack;
			         }
			      });
			}
		}

	}
}