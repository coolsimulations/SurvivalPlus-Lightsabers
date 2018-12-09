package net.coolsimulations.Lightsaber.init;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class StructureVillageJediHut extends StructureVillagePieces.Village
{
    
    public StructureVillageJediHut()
    {
    }

     public StructureVillageJediHut(StructureVillagePieces.Start start, int type, Random rand, StructureBoundingBox structurebb, EnumFacing facing)
     {
         super(start, type);
         this.setCoordBaseMode(facing);
         this.boundingBox = structurebb;
     }

     /**
      * (abstract) Helper method to write subclass data to NBT
      */
     protected void writeStructureToNBT(NBTTagCompound tagCompound)
     {
         super.writeStructureToNBT(tagCompound);
     }

     /**
      * (abstract) Helper method to read subclass data from NBT
      */
     protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
     {
         super.readStructureFromNBT(tagCompound);
     }

     public static StructureVillageJediHut createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
     {
         StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 6, 5, 7, facing);
         return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new StructureVillageJediHut(start, p_175858_7_, rand, structureboundingbox, facing);
     }

     /**
      * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
      * Mineshafts at the end, it adds Fences...
      */
     public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
     {
         if (this.averageGroundLvl < 0)
         {
             this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

             if (this.averageGroundLvl < 0)
             {
                 return true;
             }

             this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 5 - 1, 0);
         }

         IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
         IBlockState iblockstate1 = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.NETHERBRICK);
         IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
         IBlockState iblockstate3 = Blocks.COAL_BLOCK.getDefaultState();
         
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 5, 5, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false); //Air
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 5, 0, 6, Blocks.NETHER_BRICK.getDefaultState(), Blocks.NETHER_BRICK.getDefaultState(), false); //Floor
         
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 5, 3, 0, iblockstate3, iblockstate3, false); //Front Wall
         this.setBlockState(worldIn, iblockstate1, 0, 3, 0, structureBoundingBoxIn); //Front Left Top
         this.setBlockState(worldIn, iblockstate1, 5, 3, 0, structureBoundingBoxIn); //Front Right Top
         this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn); //Door Air
         this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn); //Door Air
         this.setBlockState(worldIn, Blocks.STONE_BUTTON.getDefaultState().withProperty(BlockButton.FACING, EnumFacing.SOUTH), 3, 2, -1, structureBoundingBoxIn); //Door Button
         this.setBlockState(worldIn, Blocks.STONE_BUTTON.getDefaultState().withProperty(BlockButton.FACING, EnumFacing.NORTH), 3, 2, 1, structureBoundingBoxIn); //Door Inner Button
         this.func_189927_a(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 0, 3, 4, 0, iblockstate1, iblockstate1, false); //Front Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 6, 5, 3, 6, iblockstate3, iblockstate3, false); //Back Wall
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 6, 3, 4, 6, iblockstate1, iblockstate1, false); //Back Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 6, iblockstate3, iblockstate3, false); //Left Wall
         this.setBlockState(worldIn, iblockstate1, 0, 3, 6, structureBoundingBoxIn); //Back Left Top
         this.setBlockState(worldIn, Blocks.STAINED_GLASS.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BLACK), 0, 2, 2, structureBoundingBoxIn); //Left Back Glass
         this.setBlockState(worldIn, Blocks.STAINED_GLASS.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BLACK), 0, 2, 4, structureBoundingBoxIn); //Back Front Glass
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 2, 0, 4, 4, iblockstate1, iblockstate1, false); //Left Wall Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 1, 5, 3, 6, iblockstate3, iblockstate3, false); //Right Wall
         this.setBlockState(worldIn, iblockstate1, 5, 3, 6, structureBoundingBoxIn); //Back Right Top
         this.setBlockState(worldIn, Blocks.STAINED_GLASS.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BLACK), 5, 2, 2, structureBoundingBoxIn); //Right Back Glass
         this.setBlockState(worldIn, Blocks.STAINED_GLASS.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BLACK), 5, 2, 4, structureBoundingBoxIn); //Right Front Glass
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 2, 5, 4, 4, iblockstate1, iblockstate1, false); //Right Wall Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 4, 4, 5, iblockstate3, iblockstate3, false); //Top Outer
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 2, 3, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false); //Top Inner
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 5, 2, 3, 5, 4, iblockstate1, iblockstate1, false); //Top Top
         this.setBlockState(worldIn, iblockstate1, 1, 4, 1, structureBoundingBoxIn); //Front Left Top
         this.setBlockState(worldIn, iblockstate1, 4, 4, 1, structureBoundingBoxIn); //Front Right Top
         this.setBlockState(worldIn, iblockstate1, 1, 4, 5, structureBoundingBoxIn); //Back Left Top
         this.setBlockState(worldIn, iblockstate1, 4, 4, 5, structureBoundingBoxIn); //Back Right Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 3, 1, 1, 5, iblockstate1.withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP), iblockstate1.withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP), false); //Left Bench
         this.setBlockState(worldIn, Blocks.FURNACE.getDefaultState(), 2, 1, 5, structureBoundingBoxIn); //Left Furnace
         this.setBlockState(worldIn, Blocks.ANVIL.getDefaultState(), 1, 1, 4, structureBoundingBoxIn); //Left Anvil
         this.setBlockState(worldIn, Blocks.FURNACE.getDefaultState(), 3, 1, 5, structureBoundingBoxIn); //Right Furnace
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 3, 4, 1, 5, iblockstate1.withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP), iblockstate1.withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP), false); //Right Bench
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 1, 3, 5, Blocks.GLOWSTONE.getDefaultState(), Blocks.GLOWSTONE.getDefaultState(), false); //Left Light
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 5, 4, 3, 5, Blocks.GLOWSTONE.getDefaultState(), Blocks.GLOWSTONE.getDefaultState(), false); //Right Light
         
         
         /**this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 4, 0, iblockstate, iblockstate, false); //Front Right Coloumn
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 0, 9, 4, 0, iblockstate, iblockstate, false); //Front Left Coloumn
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 12, 0, 4, 12, iblockstate, iblockstate, false); //Back Right Coloumn
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 12, 9, 4, 12, iblockstate, iblockstate, false); //Back Left Coloumn
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 8, 4, 0, Blocks.BRICK_BLOCK.getDefaultState(), Blocks.BRICK_BLOCK.getDefaultState(), false); //Front Wall Brick
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 0, 4, 3, 0, iblockstate3, iblockstate3, false); //Front Right Wooden Window Coloumn
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 0, 7, 3, 0, Blocks.GLASS_PANE.getDefaultState(), Blocks.GLASS_PANE.getDefaultState(), false); //Front Wall Window
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 0, 8, 3, 0, iblockstate3, iblockstate3, false); //Front Left Wooden Window Coloumn**/

         if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
         {
             this.setBlockState(worldIn, iblockstate2, 2, 0, -1, structureBoundingBoxIn);

             if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
             {
                 this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
             }
         }

         //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

         //this.placeTorch(worldIn, EnumFacing.NORTH, 1, 4, 1, structureBoundingBoxIn);

         for (int j = 0; j < 7; ++j) //j < 5 (j = x?)
         {
             for (int i = 0; i < 6; ++i) //i < 5; (5 = z?)
             {
                 this.clearCurrentPositionBlocksUpwards(worldIn, i, 6, j, structureBoundingBoxIn); // i, 6, j 
                 this.replaceAirAndLiquidDownwards(worldIn, Blocks.NETHER_BRICK.getDefaultState(), i, -1, j, structureBoundingBoxIn);
             }
         }

         this.spawnVillagers(worldIn, structureBoundingBoxIn, 3, 1, 4, 1);
         return true;
     }
     
     @Override
 	 protected int chooseProfession(int villagerCount, int currentVillagerProfession)
     {
    	 if(Math.random() < 0.5) {
    		 return VillagerRegistry.getId(LightsaberVillagers.villagerJedi);
    		 
    	 } else {
    		 
    		 return VillagerRegistry.getId(LightsaberVillagers.villagerSith);
    	 }
     }
     
     @Override
     protected void func_189927_a(World p_189927_1_, StructureBoundingBox p_189927_2_, Random p_189927_3_, int p_189927_4_, int p_189927_5_, int p_189927_6_, EnumFacing p_189927_7_)
     {
         if (!this.isZombieInfested)
         {
             this.func_189915_a(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_, EnumFacing.NORTH, Blocks.IRON_DOOR);
         }
     }
}