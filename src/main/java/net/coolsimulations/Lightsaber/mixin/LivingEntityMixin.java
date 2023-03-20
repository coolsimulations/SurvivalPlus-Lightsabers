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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "dropFromLootTable", cancellable = true)
	protected void dropFromLootTable(DamageSource source, boolean causedByPlayer, CallbackInfo info) {
		if(source.getEntity() instanceof Player) {
			Player player = (Player) source.getEntity();
			if(player.getMainHandItem().getItem() instanceof ItemLightsaber) {
				List<ItemStack> newList = new ArrayList<ItemStack>();
				List<ItemStack> oldList = new ArrayList<ItemStack>();

				ResourceLocation location = this.getLootTable();
				LootTable lootTable = this.level.getServer().getLootTables().get(location);
				LootContext.Builder builder = this.createLootContext(causedByPlayer, source);

				for(Iterator<ItemStack> iter = lootTable.getRandomItems(builder.create(LootContextParamSets.ENTITY)).iterator(); iter.hasNext();) {
					ItemStack drop = iter.next();
					newList.add(drop);
					oldList.add(drop);
				}

				for(int i = 0; i < newList.size(); i++) {
					ItemStack itemstack = newList.get(i);
					int count = itemstack.getCount();

					List<SmokingRecipe> recipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.SMOKING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

					if(!recipe.isEmpty()) {
						for(SmokingRecipe smokeingList : recipe) {
							ItemStack result = smokeingList.getResultItem(source.getEntity().getLevel().registryAccess());
							result.setCount(count);
							newList.remove(newList.get(i));
							newList.add(result);
						}
					} else {

						List<CampfireCookingRecipe> campfireRecipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.CAMPFIRE_COOKING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

						if(!campfireRecipe.isEmpty()) {
							for(CampfireCookingRecipe campfireList : campfireRecipe) {
								ItemStack result = campfireList.getResultItem(source.getEntity().getLevel().registryAccess());
								result.setCount(count);
								newList.remove(newList.get(i));
								newList.add(result);
							}
						} else {

							List<SmeltingRecipe> furnaceRecipe = player.getCommandSenderWorld().getRecipeManager().getRecipesFor(RecipeType.SMELTING, new SimpleContainer(new ItemStack[]{itemstack}), player.getCommandSenderWorld());

							if(!furnaceRecipe.isEmpty()) {
								for(SmeltingRecipe furnaceList : furnaceRecipe) {
									ItemStack result = furnaceList.getResultItem(source.getEntity().getLevel().registryAccess());
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

	@Shadow
	public abstract ResourceLocation getLootTable();

	@Shadow
	public abstract LootContext.Builder createLootContext(boolean causedByPlayer, DamageSource source);

}
