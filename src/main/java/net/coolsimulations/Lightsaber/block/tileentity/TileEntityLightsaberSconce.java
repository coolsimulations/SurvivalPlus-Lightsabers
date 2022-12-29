package net.coolsimulations.Lightsaber.block.tileentity;

import net.coolsimulations.Lightsaber.Lightsaber;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityLightsaberSconce extends BlockEntity {
	
	public CompoundTag itemStack = new CompoundTag();

	public TileEntityLightsaberSconce(BlockPos pos, BlockState state) {
		super(Lightsaber.SCONCE.get(), pos, state);
	}
	
	@Override
	public void load(CompoundTag compound)
	{
		super.load(compound);

        this.itemStack = compound.getCompound("Item");
	}

	@Override
	protected void saveAdditional(CompoundTag compound)
	{
		super.saveAdditional(compound);
		compound.put("Item", this.itemStack);
	}

	@Override
	public CompoundTag getUpdateTag()
	{
		return this.saveWithoutMetadata();
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket()
	{
		return ClientboundBlockEntityDataPacket.create(this);
	}
	
	public ItemStack getStack()
	{
		return ItemStack.of(itemStack);
	}

	public void setStack(CompoundTag tag)
	{
		this.itemStack = tag;
	}


}
