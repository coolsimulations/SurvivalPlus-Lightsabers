package net.coolsimulations.Lightsaber.item;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.coolsimulations.SurvivalPlus.api.SPCompatibilityManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLightsaber extends Item {

	private final float attackDamage;
	private final ItemLightsaber.LightsaberMaterial material;
	private boolean isSneaking = false;
	private boolean holdsOne = false;

	public ItemLightsaber(ItemLightsaber.LightsaberMaterial material)
	{
		this.material = material;
		this.maxStackSize = 1;
		this.attackDamage = 3.0F + material.getDamageVsEntity();
		if(!SPCompatibilityManager.isSwordBlockingLoaded()) {
			this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
			{
				@SideOnly(Side.CLIENT)
				public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
				{
					return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
				}
			});
			BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){

		ItemStack itemStackIn = playerIn.getHeldItem(handIn);
		Item item = itemStackIn.getItem();
		NBTTagCompound tag = item.getNBTShareTag(itemStackIn);
		
		ItemStack red = new ItemStack(LightsaberItems.red_lightsaber_hilt);
		red.setTagCompound(tag);

		ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber_hilt);
		blue.setTagCompound(tag);

		ItemStack green = new ItemStack(LightsaberItems.green_lightsaber_hilt);
		green.setTagCompound(tag);

		ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber_hilt);
		purple.setTagCompound(tag);

		ItemStack white = new ItemStack(LightsaberItems.white_lightsaber_hilt);
		white.setTagCompound(tag);

		ItemStack dark = new ItemStack(LightsaberItems.darksaber_hilt);
		dark.setTagCompound(tag);
		
		if(playerIn.isSneaking()) {
			isSneaking = true;
			if(item == LightsaberItems.red_lightsaber){

				if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
				{
					playerIn.setHeldItem(EnumHand.OFF_HAND, red);
				}
				else
				{
					playerIn.setHeldItem(EnumHand.MAIN_HAND, red);
				}
				worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.blue_lightsaber){

				if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
				{
					playerIn.setHeldItem(EnumHand.OFF_HAND, blue);
				}
				else
				{
					playerIn.setHeldItem(EnumHand.MAIN_HAND, blue);
				}
				worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.green_lightsaber){

				if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
				{
					playerIn.setHeldItem(EnumHand.OFF_HAND, green);
				}
				else
				{
					playerIn.setHeldItem(EnumHand.MAIN_HAND, green);
				}
				worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.purple_lightsaber){

				if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
				{
					playerIn.setHeldItem(EnumHand.OFF_HAND, purple);
				}
				else
				{
					playerIn.setHeldItem(EnumHand.MAIN_HAND, purple);
				}
				worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.white_lightsaber){

				if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
				{
					playerIn.setHeldItem(EnumHand.OFF_HAND, white);
				}
				else
				{
					playerIn.setHeldItem(EnumHand.MAIN_HAND, white);
				}
				worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
			if(item == LightsaberItems.darksaber){

				if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
				{
					playerIn.setHeldItem(EnumHand.OFF_HAND, dark);
				}
				else
				{
					playerIn.setHeldItem(EnumHand.MAIN_HAND, dark);
				}
				worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.darksaber_off, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
		} else {
			isSneaking = false;
		}
		
		if(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemLightsaber && playerIn.getHeldItem(EnumHand.OFF_HAND).isEmpty()) {
			holdsOne = true;
		} else if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemLightsaber && playerIn.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {
			holdsOne = true;
		} else {
			holdsOne = false;	
		}
		
		if(!SPCompatibilityManager.isBetterCombatLoaded()) {
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		} else if(holdsOne){
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		if(isSneaking) {
			return EnumAction.NONE;
		} else {
			if(SPCompatibilityManager.isBetterCombatLoaded() && holdsOne) {
				return EnumAction.BLOCK;
			} else if(SPCompatibilityManager.isBetterCombatLoaded() && !holdsOne) {
				return EnumAction.NONE;
			} else {
				return EnumAction.BLOCK;
			}
		}
	}

	@Override
	public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity)
	{
		return true;
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
	{
		return false;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		if(stack.getItemUseAction() == EnumAction.BLOCK) {
			return 72000;
		} else {
			return 0;
		}
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	{
		if(stack.getItem() == LightsaberItems.darksaber){
			entityLiving.world.playSound((EntityPlayer) entityLiving, entityLiving.getPosition(), LightsaberSoundHandler.darksaber_swing, SoundCategory.HOSTILE, 1.0F, 1.0F);
		} else {
			entityLiving.world.playSound((EntityPlayer) entityLiving, entityLiving.getPosition(), LightsaberSoundHandler.lightsaber_swing, SoundCategory.HOSTILE, 1.0F, 1.0F);
		}
		return false;
	}

	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player)
	{
		return !(this instanceof ItemLightsaber);
	}

	/**
	 * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
	 */
	public float getDamageVsEntity()
	{
		return this.material.getDamageVsEntity();
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();

		if (block == Blocks.WEB)
		{
			return 15.0F;
		}
		else
		{
			Material material = state.getMaterial();
			return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
		}
	}

	/**
	 * Check whether this Item can harvest the given Block
	 */
	public boolean canHarvestBlock(IBlockState blockIn)
	{
		return blockIn.getBlock() == Blocks.WEB;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{
		IBlockState state = player.world.getBlockState(pos);
		Block block = state.getBlock();

		if(!player.isCreative() && block instanceof BlockTNT && state.getProperties().containsKey(BlockTNT.EXPLODE) && !state.getValue(BlockTNT.EXPLODE)) {
			try {
				((BlockTNT) block).explode(player.world, pos, state.withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)), player);
				player.world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
			} catch(Exception e) {
			}
		}
		return false;
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability()
	{
		return this.material.getEnchantability();
	}

	/**
	 * Return the name for this tool's material.
	 */
	public String getLightsaberMaterialName()
	{
		return this.material.toString();
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
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.2000000476837158D, 0));
		}

		return multimap;
	}

	public static enum LightsaberMaterial
	{
		Lightsaber(3, 10.0F, 16.0F, 24),
		Darksaber(3, 10.0F, 26.0F, 24),
		White_Lightsaber(3, 10.0F, 20.0F, 24),
		Purple_Lightsaber(3, 10.0F, 18.0F, 24);

		/** The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = WOOD/GOLD) */
		private final int harvestLevel;

		/** The strength of this tool material against blocks which it is effective against. */
		private final float efficiencyOnProperMaterial;
		/** Damage versus entities. */
		private final float damageVsEntity;
		/** Defines the natural enchantability factor of the material. */
		private final int enchantability;

		//Added by forge for custom Tool materials.
		@Deprecated public Item customCraftingMaterial = null; // Remote in 1.8.1
		private ItemStack repairMaterial = null;

		LightsaberMaterial(int harvestLevel, float efficiency, float damageVsEntity, int enchantability)
		{
			this.harvestLevel = harvestLevel;
			this.efficiencyOnProperMaterial = efficiency;
			this.damageVsEntity = damageVsEntity;
			this.enchantability = enchantability;
		}

		/**
		 * The strength of this tool material against blocks which it is effective against.
		 */
		public float getEfficiencyOnProperMaterial()
		{
			return this.efficiencyOnProperMaterial;
		}

		/**
		 * Returns the damage against a given entity.
		 */
		public float getDamageVsEntity()
		{
			return this.damageVsEntity;
		}

		/**
		 * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
		 */
		public int getHarvestLevel()
		{
			return this.harvestLevel;
		}

		/**
		 * Return the natural enchantability factor of the material.
		 */
		public int getEnchantability()
		{
			return this.enchantability;
		}
	}

}
