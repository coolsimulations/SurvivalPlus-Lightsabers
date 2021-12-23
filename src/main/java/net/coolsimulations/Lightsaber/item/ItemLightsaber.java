package net.coolsimulations.Lightsaber.item;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class ItemLightsaber extends Item{

	private final float attackDamage;
	private final Multimap<Attribute, AttributeModifier> attribute;
	private final ItemLightsaber.LightsaberTier tier;
	private boolean isSneaking = false;
	private boolean holdsOne = false;

	public ItemLightsaber(Item.Properties properties, ItemLightsaber.LightsaberTier tier)
	{
		super(properties.stacksTo(1));
		this.tier = tier;
		this.attackDamage = 3.0F + tier.getAttackDamage();
		ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.<Attribute, AttributeModifier>builder();
		attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
		attributeBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -1.20000004768D, AttributeModifier.Operation.ADDITION));
		this.attribute = attributeBuilder.build();
		if(!SPCompatibilityManager.isSwordBlockingLoaded()) {
			DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn){

		ItemStack itemStackIn = playerIn.getItemInHand(handIn);
		Item item = itemStackIn.getItem();
		CompoundTag tag = item.getShareTag(itemStackIn);

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

		if(playerIn.isCrouching()) {
			isSneaking = true;
			if(item == LightsaberItems.red_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(InteractionHand.OFF_HAND, red);
				}
				else
				{
					playerIn.setItemInHand(InteractionHand.MAIN_HAND, red);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.blue_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(InteractionHand.OFF_HAND, blue);
				}
				else
				{
					playerIn.setItemInHand(InteractionHand.MAIN_HAND, blue);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.green_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(InteractionHand.OFF_HAND, green);
				}
				else
				{
					playerIn.setItemInHand(InteractionHand.MAIN_HAND, green);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.yellow_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(InteractionHand.OFF_HAND, yellow);
				}
				else
				{
					playerIn.setItemInHand(InteractionHand.MAIN_HAND, yellow);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			}

			if(item == LightsaberItems.purple_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(InteractionHand.OFF_HAND, purple);
				}
				else
				{
					playerIn.setItemInHand(InteractionHand.MAIN_HAND, purple);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.white_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(InteractionHand.OFF_HAND, white);
				}
				else
				{
					playerIn.setItemInHand(InteractionHand.MAIN_HAND, white);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.darksaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(InteractionHand.OFF_HAND, dark);
				}
				else
				{
					playerIn.setItemInHand(InteractionHand.MAIN_HAND, dark);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.darksaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			}
		} else {
			isSneaking = false;
		}

		if(playerIn.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ItemLightsaber && playerIn.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
			holdsOne = true;
		} else if(playerIn.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof ItemLightsaber && playerIn.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
			holdsOne = true;
		} else {
			holdsOne = false;	
		}

		if(holdsOne){
			playerIn.startUsingItem(handIn);
			return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, itemStackIn);
		}

		return new InteractionResultHolder<ItemStack>(InteractionResult.PASS, itemStackIn);
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack stack)
	{
		if(isSneaking) {
			return UseAnim.NONE;
		} else {
			return UseAnim.BLOCK;
		}
	}
	
	@Override
	public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction)
	{
		return net.minecraftforge.common.ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack itemStackIn, Player playerIn)
	{
		Level worldIn = playerIn.getCommandSenderWorld();
		Item item = itemStackIn.getItem();
		CompoundTag tag = item.getShareTag(itemStackIn);
		
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

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, red);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, red);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.blue_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, blue);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, blue);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.green_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, green);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, green);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.yellow_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, yellow);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, yellow);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.purple_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, purple);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, purple);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.white_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, white);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, white);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.darksaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(InteractionHand.OFF_HAND, dark);
			}
			else
			{
				playerIn.setItemInHand(InteractionHand.MAIN_HAND, dark);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.darksaber_off, SoundSource.HOSTILE, 1.0F, 1.0F);
			return true;
		}

		return true;
	}
	
	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getUseDuration(ItemStack stack)
	{
		if(stack.getUseAnimation() == UseAnim.BLOCK) {
			return 72000;
		} else {
			return 0;
		}
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entityLiving)
	{
		if(stack.getItem() == LightsaberItems.darksaber){
			entityLiving.level.playSound((Player) entityLiving, entityLiving.blockPosition(), LightsaberSoundHandler.darksaber_swing, SoundSource.HOSTILE, 1.0F, 1.0F);
		} else {
			entityLiving.level.playSound((Player) entityLiving, entityLiving.blockPosition(), LightsaberSoundHandler.lightsaber_swing, SoundSource.HOSTILE, 1.0F, 1.0F);
		}
		return false;
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level world, BlockPos pos, Player player)
	{
		return !player.isCreative();
	}

	/**
	 * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
	 */
	public float getAttackDamageBonus()
	{
		return this.tier.getAttackDamage();
	}

	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Block lvt_3_1_ = state.getBlock();
		if (lvt_3_1_ == Blocks.COBWEB) {
			return 15.0F;
		} else {
			Material lvt_4_1_ = state.getMaterial();
			return lvt_4_1_ != Material.PLANT && lvt_4_1_ != Material.REPLACEABLE_PLANT
					&& !state.is(BlockTags.LEAVES) && lvt_4_1_ != Material.VEGETABLE ? 1.0F : 1.5F;
		}
	}

	/**
	 * Check whether this Item can harvest the given Block
	 */
	public boolean isCorrectToolForDrops(BlockState blockIn)
	{
		return blockIn.getBlock() == Blocks.COBWEB;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player)
	{
		BlockState state = player.level.getBlockState(pos);
		Block block = state.getBlock();
		if(!player.isCreative() && block instanceof TntBlock && state.getProperties().contains(TntBlock.UNSTABLE) && !state.getValue(TntBlock.UNSTABLE)) {
			try {
				((TntBlock) block).onCaughtFire(state, player.level, pos, player.getDirection(), player);
				player.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
			} catch(Exception e) {
			}
		}
		return false;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getEnchantmentValue()
	{
		return this.tier.getEnchantability();
	}
	
	public boolean isEnchantable(ItemStack stack)
    {
        return this.getItemStackLimit(stack) == 1;
    }
	
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.world.item.enchantment.Enchantment enchantment)
	{
		if(enchantment.category == EnchantmentCategory.WEAPON)
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
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 */
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot)
	{
		return equipmentSlot == EquipmentSlot.MAINHAND ? this.attribute : super.getDefaultAttributeModifiers(equipmentSlot);
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
