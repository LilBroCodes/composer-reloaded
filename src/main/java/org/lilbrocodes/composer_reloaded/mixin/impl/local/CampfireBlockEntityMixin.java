package org.lilbrocodes.composer_reloaded.mixin.impl.local;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lilbrocodes.composer_reloaded.internal.registry.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if minecraft: >=1.21.4 {
/*import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.server.world.ServerWorld;
*///?}

@Mixin(CampfireBlockEntity.class)
public abstract class CampfireBlockEntityMixin {
    @Inject(method = "litServerTick", at = @At("HEAD"), cancellable = true)
    //? if minecraft: <=1.20.1 {
    private static void composer$explodePlushies(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
    //? } else {
    /*private static void composer$explodePlushies(ServerWorld world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, ServerRecipeManager.MatchGetter<SingleStackRecipeInput, CampfireCookingRecipe> recipeMatchGetter, CallbackInfo ci) {
    *///? }
        for (ItemStack stack : campfire.getItemsBeingCooked()) {
            if (!stack.isEmpty() && stack.getItem().equals(ModItems.PLUSHIE)) {
                ci.cancel();
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4.0f, World.ExplosionSourceType.BLOCK);
            }
        }
    }
}
