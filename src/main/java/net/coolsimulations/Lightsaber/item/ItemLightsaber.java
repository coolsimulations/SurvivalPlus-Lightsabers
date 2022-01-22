package net.coolsimulations.Lightsaber.item;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.coolsimulations.Lightsaber.config.LightsaberConfig;
import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.TNTBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLightsaber extends Item{

	private final float attackDamage;
	private final float attackSpeed;
	private final Multimap<Attribute, AttributeModifier> attribute;
	private final ItemLightsaber.LightsaberTier tier;
	private boolean isSneaking = false;
	private boolean holdsOne = false;

	public ItemLightsaber(Item.Properties properties, ItemLightsaber.LightsaberTier tier)
	{
		super(properties.stacksTo(1));
		this.tier = tier;
		this.attackDamage = -1.0F + tier.getAttackDamage();
		this.attackSpeed = -4.0F + tier.getAttackSpeed();
		ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.<Attribute, AttributeModifier>builder();
		attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
		attributeBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
		this.attribute = attributeBuilder.build();
		if(!SPCompatibilityManager.isSwordBlockingLoaded()) {
			DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		}
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){

		ItemStack itemStackIn = playerIn.getItemInHand(handIn);
		Item item = itemStackIn.getItem();
		CompoundNBT tag = item.getShareTag(itemStackIn);

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
					playerIn.setItemInHand(Hand.OFF_HAND, red);
				}
				else
				{
					playerIn.setItemInHand(Hand.MAIN_HAND, red);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.blue_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(Hand.OFF_HAND, blue);
				}
				else
				{
					playerIn.setItemInHand(Hand.MAIN_HAND, blue);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.green_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(Hand.OFF_HAND, green);
				}
				else
				{
					playerIn.setItemInHand(Hand.MAIN_HAND, green);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.yellow_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(Hand.OFF_HAND, yellow);
				}
				else
				{
					playerIn.setItemInHand(Hand.MAIN_HAND, yellow);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}

			if(item == LightsaberItems.purple_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(Hand.OFF_HAND, purple);
				}
				else
				{
					playerIn.setItemInHand(Hand.MAIN_HAND, purple);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.white_lightsaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(Hand.OFF_HAND, white);
				}
				else
				{
					playerIn.setItemInHand(Hand.MAIN_HAND, white);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.darksaber){

				if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
				{
					playerIn.setItemInHand(Hand.OFF_HAND, dark);
				}
				else
				{
					playerIn.setItemInHand(Hand.MAIN_HAND, dark);
				}
				worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.darksaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
		} else {
			isSneaking = false;
		}

		if(playerIn.getItemInHand(Hand.MAIN_HAND).getItem() instanceof ItemLightsaber && playerIn.getItemInHand(Hand.OFF_HAND).isEmpty()) {
			holdsOne = true;
		} else if(playerIn.getItemInHand(Hand.OFF_HAND).getItem() instanceof ItemLightsaber && playerIn.getItemInHand(Hand.MAIN_HAND).isEmpty()) {
			holdsOne = true;
		} else {
			holdsOne = false;	
		}

		if(holdsOne){
			playerIn.startUsingItem(handIn);
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStackIn);
		}

		return new ActionResult<ItemStack>(ActionResultType.PASS, itemStackIn);
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack)
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
	public boolean onDroppedByPlayer(ItemStack itemStackIn, PlayerEntity playerIn)
	{
		World worldIn = playerIn.getCommandSenderWorld();
		Item item = itemStackIn.getItem();
		CompoundNBT tag = item.getShareTag(itemStackIn);

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
				playerIn.setItemInHand(Hand.OFF_HAND, red);
			}
			else
			{
				playerIn.setItemInHand(Hand.MAIN_HAND, red);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.blue_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(Hand.OFF_HAND, blue);
			}
			else
			{
				playerIn.setItemInHand(Hand.MAIN_HAND, blue);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.green_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(Hand.OFF_HAND, green);
			}
			else
			{
				playerIn.setItemInHand(Hand.MAIN_HAND, green);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.yellow_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(Hand.OFF_HAND, yellow);
			}
			else
			{
				playerIn.setItemInHand(Hand.MAIN_HAND, yellow);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.purple_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(Hand.OFF_HAND, purple);
			}
			else
			{
				playerIn.setItemInHand(Hand.MAIN_HAND, purple);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.white_lightsaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(Hand.OFF_HAND, white);
			}
			else
			{
				playerIn.setItemInHand(Hand.MAIN_HAND, white);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			return true;
		}
		if(item == LightsaberItems.darksaber){

			if (ItemStack.isSame(playerIn.getOffhandItem(), itemStackIn))
			{
				playerIn.setItemInHand(Hand.OFF_HAND, dark);
			}
			else
			{
				playerIn.setItemInHand(Hand.MAIN_HAND, dark);
			}
			worldIn.playSound(playerIn, playerIn.blockPosition(), LightsaberSoundHandler.darksaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
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
		if(stack.getUseAnimation() == UseAction.BLOCK) {
			return 72000;
		} else {
			return 0;
		}
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entityLiving)
	{
		if(stack.getItem() == LightsaberItems.darksaber){
			entityLiving.level.playSound((PlayerEntity) entityLiving, entityLiving.blockPosition(), LightsaberSoundHandler.darksaber_swing, SoundCategory.HOSTILE, 1.0F, 1.0F);
		} else {
			entityLiving.level.playSound((PlayerEntity) entityLiving, entityLiving.blockPosition(), LightsaberSoundHandler.lightsaber_swing, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		return false;
	}

	@Override
	public boolean canAttackBlock(BlockState state, World world, BlockPos pos, PlayerEntity player)
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
			return lvt_4_1_ != Material.PLANT && lvt_4_1_ != Material.REPLACEABLE_PLANT && lvt_4_1_ != Material.CORAL
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

	@SuppressWarnings("static-access")
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player)
	{
		BlockState state = player.level.getBlockState(pos);
		Block block = state.getBlock();
		if(!player.isCreative() && block instanceof TNTBlock && state.getProperties().contains(TNTBlock.UNSTABLE) && !state.getValue(TNTBlock.UNSTABLE)) {
			try {
				((TNTBlock) block).catchFire(state, player.level, pos, player.getDirection(), player);
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

	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment)
	{
		if(enchantment.category == EnchantmentType.WEAPON)
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
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attribute : super.getDefaultAttributeModifiers(equipmentSlot);
	}

	public static enum LightsaberTier{
		Lightsaber(3, 10.0F, (float) LightsaberConfig.generalLightsaberDamage, (float) LightsaberConfig.generalLightsaberSpeed, 24),
		Darksaber(3, 10.0F, (float) LightsaberConfig.darksaberDamage, (float) LightsaberConfig.darksaberSpeed, 24),
		White_Lightsaber(3, 10.0F, (float) LightsaberConfig.whiteLightsaberDamage, (float) LightsaberConfig.whiteLightsaberSpeed, 24),
		Purple_Lightsaber(3, 10.0F, (float) LightsaberConfig.purpleLightsaberDamage, (float) LightsaberConfig.purpleLightsaberSpeed, 24);

		private final int harvestLevel;
		private final float efficiency;
		private final float attackDamage;
		private final float attackSpeed;
		private final int enchantability;

		LightsaberTier(int harvestLevelIn, float efficiencyIn, float attackDamageIn, float attackSpeedIn, int enchantabilityIn) {
			this.harvestLevel = harvestLevelIn;
			this.efficiency = efficiencyIn;
			this.attackDamage = attackDamageIn;
			this.attackSpeed = attackSpeedIn;
			this.enchantability = enchantabilityIn;
		}

		//@Override
		public float getAttackDamage() {
			// TODO Auto-generated method stub
			return this.attackDamage;
		}

		//@Override
		public float getAttackSpeed() {
			// TODO Auto-generated method stub
			return this.attackSpeed;
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
