package net.coolsimulations.Lightsaber.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {

	protected MobMixin(EntityType<? extends LivingEntity> type, Level world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "dropCustomDeathLoot", cancellable = true)
	protected void dropCustomDeathLoot(DamageSource source, int lootingMultiplier, boolean allowDrops, CallbackInfo info) {
		if(source.getEntity() instanceof Player) {
			Player player = (Player) source.getEntity();
			if(player.getMainHandItem().getItem() instanceof ItemLightsaber) {
				List<ItemStack> newList = new ArrayList<ItemStack>();
				List<ItemStack> oldList = new ArrayList<ItemStack>();
				EquipmentSlot[] var4 = EquipmentSlot.values();
				int var5 = var4.length;

				for(int var6 = 0; var6 < var5; ++var6) {
					EquipmentSlot equipmentSlot = var4[var6];
					ItemStack itemStack = this.getItemBySlot(equipmentSlot);
					float f = this.getEquipmentDropChance(equipmentSlot);
					boolean bl = f > 1.0F;
					if (!itemStack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemStack) && (allowDrops || bl) && this.random.nextFloat() - (float)lootingMultiplier * 0.01F < f) {
						if (!bl && itemStack.isDamageableItem()) {
							itemStack.setDamageValue(itemStack.getMaxDamage() - this.random.nextInt(1 + this.random.nextInt(Math.max(itemStack.getMaxDamage() - 3, 1))));
						}

						newList.add(itemStack);
						oldList.add(itemStack);

						for(int i = 0; i < newList.size(); i++) {
							ItemStack itemstack = newList.get(i);
							int count = itemstack.getCount();

							List<SmokingRecipe> recipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.SMOKING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

							if(!recipe.isEmpty()) {
								for(SmokingRecipe smokeingList : recipe) {
									ItemStack result = smokeingList.getResultItem();
									result.setCount(count);
									newList.remove(newList.get(i));
									newList.add(result);
								}
							} else {

								List<CampfireCookingRecipe> campfireRecipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.CAMPFIRE_COOKING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

								if(!campfireRecipe.isEmpty()) {
									for(CampfireCookingRecipe campfireList : campfireRecipe) {
										ItemStack result = campfireList.getResultItem();
										result.setCount(count);
										newList.remove(newList.get(i));
										newList.add(result);
									}
								} else {

									List<SmeltingRecipe> furnaceRecipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.SMELTING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

									if(!furnaceRecipe.isEmpty()) {
										for(SmeltingRecipe furnaceList : furnaceRecipe) {
											ItemStack result = furnaceList.getResultItem();
											result.setCount(count);
											if(result.getItem().isEdible()) {
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
								this.spawnAtLocation(stack);
							}
						info.cancel();
					}
				}
			}
		}
	}
	
	@Shadow
	public abstract float getEquipmentDropChance(EquipmentSlot equipmentSlot);

}
