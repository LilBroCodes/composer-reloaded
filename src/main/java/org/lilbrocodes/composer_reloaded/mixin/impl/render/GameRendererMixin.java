package org.lilbrocodes.composer_reloaded.mixin.impl.render;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import org.lilbrocodes.composer_reloaded.api.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;draw(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
    public void composerReloaded$renderToasts(float tickDelta, long startTime, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
        ToastManager.getInstance().render(drawContext);
    }
}
