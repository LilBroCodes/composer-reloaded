package org.lilbrocodes.composer_reloaded.mixin.impl.velora;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import org.lilbrocodes.composer_reloaded.api.v1.velora.VeloraParticleManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? minecraft: >=1.21
import net.minecraft.client.render.RenderTickCounter;

@Mixin(GameRenderer.class)
public class ParticleRenderHook {
    @Shadow
    @Final
   /*? minecraft: >=1.21.3 {*/private/*?}*/ MinecraftClient client;

    //? minecraft: <= 1.20.6 {
    /*@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V", ordinal = 1))
    public void velora$renderParticles(float tickDelta, long startTime, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    *///?} else if minecraft: <=1.21 {
    /*@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;draw()V"))
    public void velora$renderParticles(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    *///? } else {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;draw()V", ordinal = 1))
    public void velora$renderParticles(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    //?}
        VeloraParticleManager.getInstance().tick(drawContext);
    }
}
