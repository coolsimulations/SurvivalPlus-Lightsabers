package net.coolsimulations.Lightsaber.block;

import java.util.List;

import net.coolsimulations.Lightsaber.Lightsaber;
import net.coolsimulations.Lightsaber.block.tileentity.TileEntityLightsaberSconce;
import net.coolsimulations.SurvivalPlus.api.blocks.SPBlockSconce;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LightsaberSconce extends SPBlockSconce implements EntityBlock, SimpleWaterloggedBlock {

	protected static final VoxelShape SCONCE_LIGHTSABER_NORTH_AABB = Block.box(5.9D, 1.810752D, 8.579952D, 10.1D, 14.554848D, 16.0D);
	protected static final VoxelShape SCONCE_LIGHTSABER_SOUTH_AABB = Block.box(5.9D, 1.810752D, 0.0D, 10.1D, 14.554848D, 7.420048D);
	protected static final VoxelShape SCONCE_LIGHTSABER_WEST_AABB = Block.box(8.579952D, 1.810752D, 5.9D, 16D, 14.554848D, 10.1D);
	protected static final VoxelShape SCONCE_LIGHTSABER_EAST_AABB = Block.box(0.0D, 1.810752D, 5.9D, 7.420048D, 14.554848D, 10.1D);

	protected static final VoxelShape SCONCE_DARKSABER_NORTH_AABB = Block.box(5.9D, 1.810752D, 9.437424D, 10.1D, 12.744992D, 16.0D);
	protected static final VoxelShape SCONCE_DARKSABER_SOUTH_AABB = Block.box(5.9D, 1.810752D, 0.0D, 10.1D, 12.744992D, 6.562576D);
	protected static final VoxelShape SCONCE_DARKSABER_WEST_AABB = Block.box(9.437424D, 1.810752D, 5.9D, 16.0D, 12.744992D, 10.1D);
	protected static final VoxelShape SCONCE_DARKSABER_EAST_AABB = Block.box(0.0D, 1.810752D, 5.9D, 6.562576D, 12.744992D, 10.1D);

	public static final BooleanProperty DARKSABER = BooleanProperty.create("darksaber");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public LightsaberSconce() {
		this.registerDefaultState(this.stateDefinition.any().setValue(DARKSABER, false).setValue(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		if (state.getValue(DARKSABER)) {
			switch ((Direction)state.getValue(FACING))
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
			switch ((Direction)state.getValue(FACING))
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

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos pos, BlockPos fromPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return super.updateShape(state, facing, facingState, worldIn, pos, fromPos);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> drops = super.getDrops(state, builder);
		TileEntityLightsaberSconce te = builder.getParameter(LootContextParams.BLOCK_ENTITY) instanceof TileEntityLightsaberSconce ? (TileEntityLightsaberSconce) builder.getParameter(LootContextParams.BLOCK_ENTITY) : null;
		if (te != null)
			if (te.getStack() != ItemStack.EMPTY) 
				drops.add(te.getStack());

		return drops;
	}

	protected boolean isWaterLogged(LevelAccessor worldIn, BlockPos pos) {
		FluidState ifluidstate = worldIn.getFluidState(pos);
		return Boolean.valueOf(ifluidstate.getType() == Fluids.WATER);

	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state != null)
			state = state.setValue(WATERLOGGED, isWaterLogged(context.getLevel(), context.getClickedPos()));
		return state;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return (Boolean) state.getValue(WATERLOGGED)
				? Fluids.WATER.getSource(false)
						: super.getFluidState(state);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return Lightsaber.SCONCE.get().create(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(FACING, DARKSABER, WATERLOGGED);
	}
}
