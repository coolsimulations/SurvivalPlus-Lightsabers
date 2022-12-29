package net.coolsimulations.Lightsaber.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.coolsimulations.Lightsaber.block.tileentity.TileEntityLightsaberSconce;
import net.coolsimulations.SurvivalPlus.api.SPBlocks;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.coolsimulations.SurvivalPlus.api.blocks.SPBlockSconce;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thedarkcolour.futuremc.registry.FSounds;

public class LightsaberSconce extends SPBlockSconce implements ITileEntityProvider {

	protected static final AxisAlignedBB SCONCE_LIGHTSABER_NORTH_AABB = new AxisAlignedBB(0.36875D, 0.113172D, 0.536247D, 0.63125D, 0.909678D, 1.0D);
	protected static final AxisAlignedBB SCONCE_LIGHTSABER_SOUTH_AABB = new AxisAlignedBB(0.36875D, 0.113172D, 0.0D, 0.63125D, 0.909678D, 0.463753D);
	protected static final AxisAlignedBB SCONCE_LIGHTSABER_WEST_AABB = new AxisAlignedBB(0.536247D, 0.113172D, 0.36875D, 1.0D, 0.909678D, 0.63125D);
	protected static final AxisAlignedBB SCONCE_LIGHTSABER_EAST_AABB = new AxisAlignedBB(0.0D, 0.113172D, 0.36875D, 0.463753D, 0.909678D, 0.63125D);

	protected static final AxisAlignedBB SCONCE_DARKSABER_NORTH_AABB = new AxisAlignedBB(0.36875D, 0.113172D, 0.589839D, 0.63125D, 0.796562D, 1.0D);
	protected static final AxisAlignedBB SCONCE_DARKSABER_SOUTH_AABB = new AxisAlignedBB(0.36875D, 0.113172D, 0.0D, 0.63125D, 0.796562D, 0.410161D);
	protected static final AxisAlignedBB SCONCE_DARKSABER_WEST_AABB = new AxisAlignedBB(0.589839D, 0.113172D, 0.36875D, 1.0D, 0.796562D, 0.63125D);
	protected static final AxisAlignedBB SCONCE_DARKSABER_EAST_AABB = new AxisAlignedBB(0.0D, 0.113172D, 0.36875D, 0.410161D, 0.796562D, 0.63125D);

	public static final PropertyBool DARKSABER = PropertyBool.create("darksaber");

	public LightsaberSconce() {
		if (SPCompatibilityManager.isFutureMCLoaded())
			this.blockSoundType = FSounds.INSTANCE.getLANTERN();
		this.setLightOpacity(1);
		this.setLightLevel(0.0F);
		this.translucent = true;
		this.setDefaultState(this.getDefaultState().withProperty(DARKSABER, false));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		if (state.getValue(DARKSABER)) {
			switch ((EnumFacing)state.getValue(FACING))
			{
			case EAST:
				return SCONCE_DARKSABER_EAST_AABB;
			case WEST:
				return SCONCE_DARKSABER_WEST_AABB;
			case SOUTH:
				return SCONCE_DARKSABER_SOUTH_AABB;
			case NORTH:
				return SCONCE_DARKSABER_NORTH_AABB;
			default:
				return SCONCE_DARKSABER_EAST_AABB;
			}
		} else {
			switch ((EnumFacing)state.getValue(FACING))
			{
			case EAST:
				return SCONCE_LIGHTSABER_EAST_AABB;
			case WEST:
				return SCONCE_LIGHTSABER_WEST_AABB;
			case SOUTH:
				return SCONCE_LIGHTSABER_SOUTH_AABB;
			case NORTH:
				return SCONCE_LIGHTSABER_NORTH_AABB;
			default:
				return SCONCE_LIGHTSABER_EAST_AABB;
			}
		}
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(SPBlocks.sconce);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		super.getDrops(drops, world, pos, state, fortune);
		TileEntityLightsaberSconce te = world.getTileEntity(pos) instanceof TileEntityLightsaberSconce ? (TileEntityLightsaberSconce)world.getTileEntity(pos) : null;
		if (te != null) {
			if (te.getStack() != ItemStack.EMPTY) 
				drops.add(te.getStack());
		}
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
	{
		if (willHarvest)
			return true;
		return super.removedByPlayer(state, world, pos, player, willHarvest);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack tool)
	{
		super.harvestBlock(world, player, pos, state, te, tool);
		world.setBlockToAir(pos);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityLightsaberSconce();
	}

	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing;

		switch (meta & 3)
		{
		case 0:
			enumfacing = EnumFacing.EAST;
			break;
		case 1:
			enumfacing = EnumFacing.WEST;
			break;
		case 2:
			enumfacing = EnumFacing.SOUTH;
			break;
		case 3:
			enumfacing = EnumFacing.NORTH;
			break;
		default:
			enumfacing = EnumFacing.EAST;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(DARKSABER, Boolean.valueOf((meta & 4) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;

		switch ((EnumFacing)state.getValue(FACING))
		{
		case EAST:
			i = 0;
			break;
		case WEST:
			i = 1;
			break;
		case SOUTH:
			i = 2;
			break;
		case NORTH:
			i = 3;
			break;
		default:
			i = 3;
		}
		
		if ((state.getValue(DARKSABER)).booleanValue())
		{
			i |= 4;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {FACING, DARKSABER});
	}
}
