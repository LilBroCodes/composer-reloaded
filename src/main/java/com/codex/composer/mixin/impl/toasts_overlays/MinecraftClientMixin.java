package com.codex.composer.mixin.impl.toasts_overlays;

import net.minecraft.client.MinecraftClient;
import com.codex.composer.internal.overlay.OverlayHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    public void composer$tickOverlays(CallbackInfo ci) {
        OverlayHandler.tick();
    }
}
