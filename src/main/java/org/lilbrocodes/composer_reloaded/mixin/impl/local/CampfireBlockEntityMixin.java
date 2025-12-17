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

@Mixin(CampfireBlockEntity.class)
public abstract class CampfireBlockEntityMixin {
    @Inject(method = "litServerTick", at = @At("HEAD"), cancellable = true)
    private static void composer$explodePlushies$1(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
        for (ItemStack stack : campfire.getItemsBeingCooked()) {
            if (!stack.isEmpty() && stack.getItem().equals(ModItems.PLUSHIE)) {
                ci.cancel();
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4.0f, World.ExplosionSourceType.BLOCK);
            }
        }
    }
}
