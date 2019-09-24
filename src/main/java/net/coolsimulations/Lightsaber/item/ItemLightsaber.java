package net.coolsimulations.Lightsaber.item;

import com.google.common.collect.Multimap;

import net.coolsimulations.Lightsaber.init.LightsaberItems;
import net.coolsimulations.Lightsaber.init.LightsaberSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLightsaber extends Item{

    private final float attackDamage;
    private final ItemLightsaber.LightsaberTier tier;

    public ItemLightsaber(Item.Properties properties, ItemLightsaber.LightsaberTier tier)
    {
    		super(properties.maxStackSize(1));
        this.tier = tier;
        this.attackDamage = 3.0F + tier.getAttackDamage();
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		
        ItemStack itemStackIn = playerIn.getHeldItem(handIn);
    	Item item = itemStackIn.getItem();
    	NBTTagCompound tag = item.getShareTag(itemStackIn);
    	
    	ItemStack red = new ItemStack(LightsaberItems.red_lightsaber_hilt);
    	red.setTag(tag);
    	
    	ItemStack blue = new ItemStack(LightsaberItems.blue_lightsaber_hilt);
    	blue.setTag(tag);
    	
    	ItemStack green = new ItemStack(LightsaberItems.green_lightsaber_hilt);
    	green.setTag(tag);
    	
    	ItemStack purple = new ItemStack(LightsaberItems.purple_lightsaber_hilt);
    	purple.setTag(tag);
    	
    	ItemStack white = new ItemStack(LightsaberItems.white_lightsaber_hilt);
    	white.setTag(tag);
    	
    	ItemStack dark = new ItemStack(LightsaberItems.darksaber_hilt);
    	dark.setTag(tag);
    	
    	if(item == LightsaberItems.red_lightsaber){
    		
    		if (ItemStack.areItemStacksEqual(playerIn.getHeldItemOffhand(), itemStackIn))
			{
    			playerIn.setHeldItem(EnumHand.OFF_HAND, red);
			}
			else
			{
    			playerIn.setHeldItem(EnumHand.MAIN_HAND, red);
			}
            worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
            worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.lightsaber_off, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
    		worldIn.playSound(playerIn, playerIn.getPosition(), LightsaberSoundHandler.darksaber_off, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
    
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        return true; //Meant to be false but bug means that when dropping it it vanishes
    }
    
    @Override
    public boolean onEntitySwing(ItemStack stack, EntityLivingBase entityLiving)
    {
    	if(stack.getItem() == LightsaberItems.darksaber){
    		entityLiving.world.playSound((EntityPlayer) entityLiving, entityLiving.getPosition(), LightsaberSoundHandler.darksaber_swing, SoundCategory.BLOCKS, 1.0F, 1.0F);
    	} else {
    		entityLiving.world.playSound((EntityPlayer) entityLiving, entityLiving.getPosition(), LightsaberSoundHandler.lightsaber_swing, SoundCategory.BLOCKS, 1.0F, 1.0F);
    	}
        return false;
    }
    
    @Override
    public boolean canPlayerBreakBlockWhileHolding(IBlockState state, World world, BlockPos pos, EntityPlayer player)
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
    
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Block lvt_3_1_ = state.getBlock();
		if (lvt_3_1_ == Blocks.COBWEB) {
			return 15.0F;
		} else {
			Material lvt_4_1_ = state.getMaterial();
			return lvt_4_1_ != Material.PLANTS && lvt_4_1_ != Material.VINE && lvt_4_1_ != Material.CORAL
					&& !state.isIn(BlockTags.LEAVES) && lvt_4_1_ != Material.GOURD ? 1.0F : 1.5F;
		}
	}

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return blockIn.getBlock() == Blocks.COBWEB;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.tier.getEnchantability();
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
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) -1.2000000476837158D, 0));
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
