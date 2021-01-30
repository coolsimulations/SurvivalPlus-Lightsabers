package net.coolsimulations.Lightsaber.mixin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.coolsimulations.Lightsaber.item.ItemLightsaber;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "dropLoot", cancellable = true)
	protected void dropLoot(DamageSource source, boolean causedByPlayer, CallbackInfo info) {
		if(source.getAttacker() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) source.getAttacker();
			if(player.getMainHandStack().getItem() instanceof ItemLightsaber) {
				List<ItemStack> newList = new ArrayList<ItemStack>();
				List<ItemStack> oldList = new ArrayList<ItemStack>();

				Identifier identifier = this.getLootTable();
				LootTable lootTable = this.world.getServer().getLootManager().getSupplier(identifier);
				LootContext.Builder builder = this.getLootContextBuilder(causedByPlayer, source);

				for(Iterator<ItemStack> iter = lootTable.getDrops(builder.build(LootContextTypes.ENTITY)).iterator(); iter.hasNext();) {
					ItemStack drop = iter.next();
					newList.add(drop);
					oldList.add(drop);
				}

				for(int i = 0; i < newList.size(); i++) {
					ItemStack itemstack = newList.get(i);
					int count = itemstack.getCount();

					List<SmokingRecipe> recipe = player.getEntityWorld().getRecipeManager().getAllMatches(RecipeType.SMOKING, new BasicInventory(new ItemStack[]{itemstack}), player.getEntityWorld());

					if(!recipe.isEmpty()) {
						for(SmokingRecipe smokeingList : recipe) {
							ItemStack result = smokeingList.getOutput();
							result.setCount(count);
							newList.remove(newList.get(i));
							newList.add(result);
						}
					} else {

						List<CampfireCookingRecipe> campfireRecipe = player.getEntityWorld().getRecipeManager().getAllMatches(RecipeType.CAMPFIRE_COOKING, new BasicInventory(new ItemStack[]{itemstack}), player.getEntityWorld());

						if(!campfireRecipe.isEmpty()) {
							for(CampfireCookingRecipe campfireList : campfireRecipe) {
								ItemStack result = campfireList.getOutput();
								result.setCount(count);
								newList.remove(newList.get(i));
								newList.add(result);
							}
						} else {

							List<SmeltingRecipe> furnaceRecipe = player.getEntityWorld().getRecipeManager().getAllMatches(RecipeType.SMELTING, new BasicInventory(new ItemStack[]{itemstack}), player.getEntityWorld());

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

	@Shadow
	public abstract Identifier getLootTable();

	@Shadow
	public abstract LootContext.Builder getLootContextBuilder(boolean causedByPlayer, DamageSource source);

}
