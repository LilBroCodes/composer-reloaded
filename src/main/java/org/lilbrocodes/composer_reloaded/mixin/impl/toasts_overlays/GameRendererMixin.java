package org.lilbrocodes.composer_reloaded.mixin.impl.toasts_overlays;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import org.lilbrocodes.composer_reloaded.api.v1.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.internal.overlay.OverlayHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? minecraft: >=1.21
import net.minecraft.client.render.RenderTickCounter;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;draw(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
    //? if minecraft: <=1.20.6 {
    /*public void composerReloaded$renderToasts(float f, long startTime, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    *///? } else {
    public void composerReloaded$renderToasts(RenderTickCounter f, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    //? }
        ToastManager.getInstance().render(drawContext);
        OverlayHandler.render(drawContext, f);
    }
}
