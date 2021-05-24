package net.coolsimulations.Lightsaber.item;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Multimap;

import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.events.ItemAccessor;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.Material;
import net.minecraft.block.TntBlock;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLightsaber extends Item implements ItemAccessor{

	private final float attackDamage;
	private final ItemLightsaber.LightsaberTier tier;
	private boolean isSneaking = false;
	private boolean holdsOne = false;

	public ItemLightsaber(FabricItemSettings properties, ItemLightsaber.LightsaberTier tier)
	{
		super(properties.maxCount(1));
		this.tier = tier;
		this.attackDamage = 3.0F + tier.getAttackDamage();
		this.addPropertyGetter(new Identifier("blocking"), (stack, worldIn, entityIn) -> {
				return entityIn != null && entityIn.isUsingItem() && entityIn.getActiveItem() == stack ? 1.0F : 0.0F;
			});
			DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
	}

	@Override
	public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

		ItemStack itemStackIn = playerIn.getStackInHand(handIn);
		Item item = itemStackIn.getItem();
		CompoundTag tag = itemStackIn.getTag();

		ItemStack red = new ItemStack(LightsaberItems.red_lightsaber_hilt);
		red.setTag(tag);

		ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber_hilt);
		blue.setTag(tag);

		ItemStack green = new ItemStack(LightsaberItems.green_lightsaber_hilt);
		green.setTag(tag);
		
		ItemStack yellow = new ItemStack(LightsaberItems.yellow_lightsaber_hilt);
		yellow.setTag(tag);

		ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber_hilt);
		purple.setTag(tag);

		ItemStack white = new ItemStack(LightsaberItems.white_lightsaber_hilt);
		white.setTag(tag);

		ItemStack dark = new ItemStack(LightsaberItems.darksaber_hilt);
		dark.setTag(tag);

		if(playerIn.isSneaking()) {
			isSneaking = true;
			if(item == LightsaberItems.red_lightsaber){

				if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
				{
					playerIn.setStackInHand(Hand.OFF_HAND, red);
				}
				else
				{
					playerIn.setStackInHand(Hand.MAIN_HAND, red);
				}
				worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.blue_lightsaber){

				if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
				{
					playerIn.setStackInHand(Hand.OFF_HAND, blue);
				}
				else
				{
					playerIn.setStackInHand(Hand.MAIN_HAND, blue);
				}
				worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.green_lightsaber){

				if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
				{
					playerIn.setStackInHand(Hand.OFF_HAND, green);
				}
				else
				{
					playerIn.setStackInHand(Hand.MAIN_HAND, green);
				}
				worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.yellow_lightsaber){

				if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
				{
					playerIn.setStackInHand(Hand.OFF_HAND, yellow);
				}
				else
				{
					playerIn.setStackInHand(Hand.MAIN_HAND, yellow);
				}
				worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.purple_lightsaber){

				if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
				{
					playerIn.setStackInHand(Hand.OFF_HAND, purple);
				}
				else
				{
					playerIn.setStackInHand(Hand.MAIN_HAND, purple);
				}
				worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.white_lightsaber){

				if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
				{
					playerIn.setStackInHand(Hand.OFF_HAND, white);
				}
				else
				{
					playerIn.setStackInHand(Hand.MAIN_HAND, white);
				}
				worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.darksaber){

				if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
				{
					playerIn.setStackInHand(Hand.OFF_HAND, dark);
				}
				else
				{
					playerIn.setStackInHand(Hand.MAIN_HAND, dark);
				}
				worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.darksaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
		} else {
			isSneaking = false;
		}

		if(playerIn.getStackInHand(Hand.MAIN_HAND).getItem() instanceof ItemLightsaber && playerIn.getStackInHand(Hand.OFF_HAND).isEmpty()) {
			holdsOne = true;
		} else if(playerIn.getStackInHand(Hand.OFF_HAND).getItem() instanceof ItemLightsaber && playerIn.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
			holdsOne = true;
		} else {
			holdsOne = false;	
		}

		if(holdsOne){
			playerIn.setCurrentHand(handIn);
			return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemStackIn);
		}

		return new TypedActionResult<ItemStack>(ActionResult.PASS, itemStackIn);
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		if(isSneaking) {
			return UseAction.NONE;
		} else {
			return UseAction.BLOCK;
		}
	}
	
	@Override
	public boolean isShield(ItemStack stack, @Nullable LivingEntity entity)
	{
		return true;
	}
	
	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return false;
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack itemStackIn, PlayerEntity playerIn)
	{
		World worldIn = playerIn.getEntityWorld();
		Item item = itemStackIn.getItem();
		CompoundTag tag = itemStackIn.getTag();
		
		ItemStack red = new ItemStack(LightsaberItems.red_lightsaber_hilt);
		red.setTag(tag);

		ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber_hilt);
		blue.setTag(tag);

		ItemStack green = new ItemStack(LightsaberItems.green_lightsaber_hilt);
		green.setTag(tag);
		
		ItemStack yellow = new ItemStack(LightsaberItems.yellow_lightsaber_hilt);
		yellow.setTag(tag);

		ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber_hilt);
		purple.setTag(tag);

		ItemStack white = new ItemStack(LightsaberItems.white_lightsaber_hilt);
		white.setTag(tag);

		ItemStack dark = new ItemStack(LightsaberItems.darksaber_hilt);
		dark.setTag(tag);
		
		if(item == LightsaberItems.red_lightsaber){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, red);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, red);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.blue_lightsaber){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, blue);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, blue);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.green_lightsaber){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, green);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, green);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.yellow_lightsaber){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, yellow);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, yellow);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.purple_lightsaber){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, purple);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, purple);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.white_lightsaber){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, white);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, white);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.darksaber){

			if (ItemStack.areItemsEqual(playerIn.getOffHandStack(), itemStackIn))
			{
				playerIn.setStackInHand(Hand.OFF_HAND, dark);
			}
			else
			{
				playerIn.setStackInHand(Hand.MAIN_HAND, dark);
			}
			worldIn.playSound(playerIn, playerIn.getBlockPos(), LightsaberSoundHandler.darksaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}

		return true;
	}
	
	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxUseTime(ItemStack stack)
	{
		if(stack.getUseAction() == UseAction.BLOCK) {
			return 72000;
		} else {
			return 0;
		}
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entityLiving)
	{
		if(stack.getItem() == LightsaberItems.darksaber){
			entityLiving.world.playSound((PlayerEntity) entityLiving, entityLiving.getBlockPos(), LightsaberSoundHandler.darksaber_swing, SoundCategory.HOSTILE, 1.0F, 1.0F);
		} else {
			entityLiving.world.playSound((PlayerEntity) entityLiving, entityLiving.getBlockPos(), LightsaberSoundHandler.lightsaber_swing, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		return false;
	}

	@Override
	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity player)
	{
		return !player.isCreative();
	}

	/**
	 * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
	 */
	public float getAttackDamage()
	{
		return this.tier.getAttackDamage();
	}

	public float getMiningSpeed(ItemStack stack, BlockState state) {
		Block lvt_3_1_ = state.getBlock();
		if (lvt_3_1_ == Blocks.COBWEB) {
			return 15.0F;
		} else {
			Material lvt_4_1_ = state.getMaterial();
			return lvt_4_1_ != Material.PLANT && lvt_4_1_ != Material.REPLACEABLE_PLANT && lvt_4_1_ != Material.UNUSED_PLANT
					&& !state.matches(BlockTags.LEAVES) && lvt_4_1_ != Material.PUMPKIN ? 1.0F : 1.5F;
		}
	}

	/**
	 * Check whether this Item can harvest the given Block
	 */
	public boolean canHarvestBlock(BlockState blockIn)
	{
		return blockIn.getBlock() == Blocks.COBWEB;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player)
	{
		BlockState state = player.world.getBlockState(pos);
		Block block = state.getBlock();

		if(!player.isCreative() && block instanceof TntBlock && state.getProperties().contains(TntBlock.UNSTABLE) && !state.get(TntBlock.UNSTABLE)) {
			try {
				((TntBlock) block).primeTnt(player.world, pos);
				player.world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
			} catch(Exception e) {
			}
		}
		return false;
	}


	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getEnchantability()
	{
		return this.tier.getEnchantability();
	}
	
	public boolean isEnchantable(ItemStack stack)
    {
        return this.getMaxCount() == 1;
    }
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment)
	{
		if(enchantment.type == EnchantmentTarget.WEAPON)
			return true;
		else
			return false;
	}


	/**
	 * Return the name for this tool's material.
	 */
	public String getLightsaberMaterialName()
	{
		return this.tier.toString();
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	/**public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.material.getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }**/

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 */
	public Multimap<String, EntityAttributeModifier> getModifiers(EquipmentSlot equipmentSlot)
	{
		Multimap<String, EntityAttributeModifier> multimap = super.getModifiers(equipmentSlot);

		if (equipmentSlot == EquipmentSlot.MAINHAND)
		{
			multimap.put(EntityAttributes.ATTACK_DAMAGE.getId(), new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_UUID, "Weapon modifier", (double) this.attackDamage, Operation.ADDITION));
			multimap.put(EntityAttributes.ATTACK_SPEED.getId(), new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "Weapon modifier", (double) -1.2000000476837158D, Operation.ADDITION));
		}

		return multimap;
	}

	public static enum LightsaberTier{
		Lightsaber(3, 10.0F, 16.0F, 24),
		Darksaber(3, 10.0F, 26.0F, 24),
		White_Lightsaber(3, 10.0F, 20.0F, 24),
		Purple_Lightsaber(3, 10.0F, 18.0F, 24);

		private final int harvestLevel;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;

		LightsaberTier(int harvestLevelIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn) {
			this.harvestLevel = harvestLevelIn;
			this.efficiency = efficiencyIn;
			this.attackDamage = attackDamageIn;
			this.enchantability = enchantabilityIn;
		}

		//@Override
		public float getAttackDamage() {
			// TODO Auto-generated method stub
			return this.attackDamage;
		}

		//@Override
		public float getEfficiency() {
			// TODO Auto-generated method stub
			return this.efficiency;
		}

		//@Override
		public int getEnchantability() {
			// TODO Auto-generated method stub
			return this.enchantability;
		}

		//@Override
		public int getHarvestLevel() {
			// TODO Auto-generated method stub
			return this.harvestLevel;
		}

	}

}
