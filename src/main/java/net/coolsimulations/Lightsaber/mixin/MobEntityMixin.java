package net.coolsimulations.Lightsaber.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.world.World;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {

	protected MobEntityMixin(EntityType<? extends LivingEntity> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "dropEquipment", cancellable = true)
	protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops, CallbackInfo info) {
		if(source.getAttacker() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) source.getAttacker();
			if(player.getMainHandStack().getItem() instanceof ItemLightsaber) {
				List<ItemStack> newList = new ArrayList<ItemStack>();
				List<ItemStack> oldList = new ArrayList<ItemStack>();
				EquipmentSlot[] var4 = EquipmentSlot.values();
				int var5 = var4.length;

				for(int var6 = 0; var6 < var5; ++var6) {
					EquipmentSlot equipmentSlot = var4[var6];
					ItemStack itemStack = this.getEquippedStack(equipmentSlot);
					float f = this.getDropChance(equipmentSlot);
					boolean bl = f > 1.0F;
					if (!itemStack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemStack) && (allowDrops || bl) && this.random.nextFloat() - (float)lootingMultiplier * 0.01F < f) {
						if (!bl && itemStack.isDamageable()) {
							itemStack.setDamage(itemStack.getMaxDamage() - this.random.nextInt(1 + this.random.nextInt(Math.max(itemStack.getMaxDamage() - 3, 1))));
						}

						newList.add(itemStack);
						oldList.add(itemStack);

						for(int i = 0; i < newList.size(); i++) {
							ItemStack itemstack = newList.get(i);
							int count = itemstack.getCount();

							List<SmokingRecipe> recipe = player.getEntityWorld().getRecipeManager().getAllMatches(RecipeType.SMOKING, new SimpleInventory(new ItemStack[]{itemstack}), player.getEntityWorld());

							if(!recipe.isEmpty()) {
								for(SmokingRecipe smokeingList : recipe) {
									ItemStack result = smokeingList.getOutput();
									result.setCount(count);
									newList.remove(newList.get(i));
									newList.add(result);
								}
							} else {

								List<CampfireCookingRecipe> campfireRecipe = player.getEntityWorld().getRecipeManager().getAllMatches(RecipeType.CAMPFIRE_COOKING, new SimpleInventory(new ItemStack[]{itemstack}), player.getEntityWorld());

								if(!campfireRecipe.isEmpty()) {
									for(CampfireCookingRecipe campfireList : campfireRecipe) {
										ItemStack result = campfireList.getOutput();
										result.setCount(count);
										newList.remove(newList.get(i));
										newList.add(result);
									}
								} else {

									List<SmeltingRecipe> furnaceRecipe = player.getEntityWorld().getRecipeManager().getAllMatches(RecipeType.SMELTING, new SimpleInventory(new ItemStack[]{itemstack}), player.getEntityWorld());

									if(!furnaceRecipe.isEmpty()) {
										for(SmeltingRecipe furnaceList : furnaceRecipe) {
											ItemStack result = furnaceList.getOutput();
											result.setCount(count);
											if(result.getItem().isFood()) {
												newList.remove(newList.get(i));
												newList.add(result);
											}
										}
									}
								}
							}
						}

						if(newList != oldList)
							for(ItemStack stack : newList) {
								this.dropStack(stack);
							}
						info.cancel();
					}
				}
			}
		}
	}
	
	@Shadow
	public abstract float getDropChance(EquipmentSlot equipmentSlot);

}
