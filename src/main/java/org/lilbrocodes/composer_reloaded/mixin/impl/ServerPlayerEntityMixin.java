package org.lilbrocodes.composer_reloaded.mixin.impl;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.lilbrocodes.composer_reloaded.api.targeting.Targeting;
import org.lilbrocodes.composer_reloaded.api.targeting.TargetingContext;
import org.lilbrocodes.composer_reloaded.components.entry.ModEntityComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void composer$decayTarget(CallbackInfo ci) {
        ServerPlayerEntity self = (ServerPlayerEntity) (Object) this;
        Entity entityTarget = Targeting.getTargetedEntity(self, TargetingContext.getIgnoring(self));
        BlockPos blockTarget = Targeting.getTargetedBlock(self, TargetingContext.getIgnoring(self));
        if (entityTarget != null) {
            ModEntityComponents.TARGETED_ENTITY.get(self).tick(self);
        }
        if (blockTarget != null) {
            ModEntityComponents.TARGETED_BLOCK.get(self).tick(self);
        }
    }
}
