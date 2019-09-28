package net.coolsimulations.Lightsaber.init;

/**import java.util.List;
import java.util.Random;

import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.VillagePieces;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.fml.common.registry.VillagerRegistry;**/

public class StructureVillageJediHut //extends VillagePieces.Village
{
    
    /**public StructureVillageJediHut()
    {
    }

     public StructureVillageJediHut(VillagePieces.Start start, int p_i45569_2_, Random rand, MutableBoundingBox p_i45569_4_, EnumFacing facing)
     {
    	 super(start, p_i45569_2_);
         this.setCoordBaseMode(facing);
         this.boundingBox = p_i45569_4_;
     }**/

     /**
      * (abstract) Helper method to write subclass data to NBT
      */
     /**protected void writeStructureToNBT(NBTTagCompound tagCompound)
     {
         super.writeStructureToNBT(tagCompound);
     }**/

     /**
      * (abstract) Helper method to read subclass data from NBT
      */
     /**protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
     {
         super.readStructureFromNBT(tagCompound, p_143011_2_);
     }

     public static StructureVillageJediHut createPiece(VillagePieces.Start start, List<StructurePiece> p_175852_1_, Random rand, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_)
     {
    	 	MutableBoundingBox structureboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 6, 5, 7, facing);
         return canVillageGoDeeper(structureboundingbox) && StructurePiece.findIntersecting(p_175852_1_, structureboundingbox) == null ? new StructureVillageJediHut(start, p_175852_7_, rand, structureboundingbox, facing) : null;
     }**/

     /**
      * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
      * Mineshafts at the end, it adds Fences...
      */
     /**public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos pos)
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
         IBlockState iblockstate1 = Blocks.NETHER_BRICK_SLAB.getDefaultState().with(BlockSlab.TYPE, SlabType.BOTTOM);
         IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE_STAIRS.getDefaultState().with(BlockStairs.FACING, EnumFacing.NORTH));
         IBlockState iblockstate3 = Blocks.COAL_BLOCK.getDefaultState();
         
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 5, 5, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false); //Air
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 5, 0, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false); //Floor
         
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 5, 3, 0, iblockstate3, iblockstate3, false); //Front Wall
         this.setBlockState(worldIn, iblockstate1, 0, 3, 0, structureBoundingBoxIn); //Front Left Top
         this.setBlockState(worldIn, iblockstate1, 5, 3, 0, structureBoundingBoxIn); //Front Right Top
         this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn); //Door Air
         this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn); //Door Air
         this.setBlockState(worldIn, Blocks.STONE_BUTTON.getDefaultState().with(BlockButton.HORIZONTAL_FACING, EnumFacing.SOUTH), 3, 2, -1, structureBoundingBoxIn); //Door Button
         this.setBlockState(worldIn, Blocks.STONE_BUTTON.getDefaultState().with(BlockButton.HORIZONTAL_FACING, EnumFacing.NORTH), 3, 2, 1, structureBoundingBoxIn); //Door Inner Button
         this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 0, 3, 4, 0, iblockstate1, iblockstate1, false); //Front Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 6, 5, 3, 6, iblockstate3, iblockstate3, false); //Back Wall
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 6, 3, 4, 6, iblockstate1, iblockstate1, false); //Back Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 6, iblockstate3, iblockstate3, false); //Left Wall
         this.setBlockState(worldIn, iblockstate1, 0, 3, 6, structureBoundingBoxIn); //Back Left Top
         this.setBlockState(worldIn, Blocks.BLACK_STAINED_GLASS.getDefaultState(), 0, 2, 2, structureBoundingBoxIn); //Left Back Glass
         this.setBlockState(worldIn, Blocks.BLACK_STAINED_GLASS.getDefaultState(), 0, 2, 4, structureBoundingBoxIn); //Back Front Glass
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 2, 0, 4, 4, iblockstate1, iblockstate1, false); //Left Wall Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 1, 5, 3, 6, iblockstate3, iblockstate3, false); //Right Wall
         this.setBlockState(worldIn, iblockstate1, 5, 3, 6, structureBoundingBoxIn); //Back Right Top
         this.setBlockState(worldIn, Blocks.BLACK_STAINED_GLASS.getDefaultState(), 5, 2, 2, structureBoundingBoxIn); //Right Back Glass
         this.setBlockState(worldIn, Blocks.BLACK_STAINED_GLASS.getDefaultState(), 5, 2, 4, structureBoundingBoxIn); //Right Front Glass
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 2, 5, 4, 4, iblockstate1, iblockstate1, false); //Right Wall Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 4, 4, 5, iblockstate3, iblockstate3, false); //Top Outer
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 2, 3, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false); //Top Inner
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 5, 2, 3, 5, 4, iblockstate1, iblockstate1, false); //Top Top
         this.setBlockState(worldIn, iblockstate1, 1, 4, 1, structureBoundingBoxIn); //Front Left Top
         this.setBlockState(worldIn, iblockstate1, 4, 4, 1, structureBoundingBoxIn); //Front Right Top
         this.setBlockState(worldIn, iblockstate1, 1, 4, 5, structureBoundingBoxIn); //Back Left Top
         this.setBlockState(worldIn, iblockstate1, 4, 4, 5, structureBoundingBoxIn); //Back Right Top
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 3, 1, 1, 5, iblockstate1.with(BlockSlab.TYPE, SlabType.TOP), iblockstate1.with(BlockSlab.TYPE, SlabType.TOP), false); //Left Bench
         this.setBlockState(worldIn, Blocks.FURNACE.getDefaultState().with(BlockFurnace.FACING, EnumFacing.SOUTH), 2, 1, 5, structureBoundingBoxIn); //Left Furnace
         this.setBlockState(worldIn, Blocks.ANVIL.getDefaultState(), 1, 1, 4, structureBoundingBoxIn); //Left Anvil
         this.setBlockState(worldIn, Blocks.FURNACE.getDefaultState().with(BlockFurnace.FACING, EnumFacing.SOUTH), 3, 1, 5, structureBoundingBoxIn); //Right Furnace
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 3, 4, 1, 5, iblockstate1.with(BlockSlab.TYPE, SlabType.TOP), iblockstate1.with(BlockSlab.TYPE, SlabType.TOP), false); //Right Bench
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 1, 3, 5, Blocks.GLOWSTONE.getDefaultState(), Blocks.GLOWSTONE.getDefaultState(), false); //Left Light
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 5, 4, 3, 5, Blocks.GLOWSTONE.getDefaultState(), Blocks.GLOWSTONE.getDefaultState(), false); //Right Light

         if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
         {
             this.setBlockState(worldIn, iblockstate2, 2, 0, -1, structureBoundingBoxIn);

             if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
             {
                 this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
             }
         }

         for (int j = 0; j < 7; ++j)
         {
             for (int i = 0; i < 6; ++i)
             {
                 this.clearCurrentPositionBlocksUpwards(worldIn, i, 6, j, structureBoundingBoxIn); 
                 this.replaceAirAndLiquidDownwards(worldIn, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn);
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
     protected void createVillageDoor(IWorld p_189927_1_, MutableBoundingBox p_189927_2_, Random p_189927_3_, int p_189927_4_, int p_189927_5_, int p_189927_6_, EnumFacing p_189927_7_)
     {
         if (!this.isZombieInfested)
         {
             this.generateDoor(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_, EnumFacing.NORTH, (BlockDoor) Blocks.IRON_DOOR);
         }
     }**/
}