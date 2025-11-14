package org.lilbrocodes.composer_reloaded.mixin.impl;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import org.lilbrocodes.composer_reloaded.api.events.ScrollEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Shadow @Final public PlayerEntity player;
    @Unique List<Event<ScrollEvents.ScrollAction>> priorities = List.of(
            ScrollEvents.HIGH_PRIORITY,
            ScrollEvents.MEDIUM_PRIORITY,
            ScrollEvents.LOW_PRIORITY
    );

    @Inject(method = "scrollInHotbar", at = @At("HEAD"), cancellable = true)
    public void composer_reloaded$triggerActions(double scrollAmount, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientWorld world = client.world;
        ClientPlayerEntity player = this.player instanceof ClientPlayerEntity cp ? cp : client.player;

        for (Event<ScrollEvents.ScrollAction> event : priorities) {
            if (event.invoker().onScroll(client, world, player, scrollAmount)) {
                ci.cancel();
                break;
            }
        }
    }
}
