package org.lilbrocodes.composer_reloaded.mixin.impl.scroll;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import org.lilbrocodes.composer_reloaded.api.events.ClientScrollEvents;
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
    @Shadow
    @Final
    public PlayerEntity player;
    @Unique
    final List<Event<ClientScrollEvents.ClientScrollAction>> priorities = List.of(
            ClientScrollEvents.HIGH_PRIORITY,
            ClientScrollEvents.MEDIUM_PRIORITY,
            ClientScrollEvents.LOW_PRIORITY
    );

    //? if minecraft: <=1.20.1
    @Inject(method = "scrollInHotbar", at = @At("HEAD"), cancellable = true)
    public void composer_reloaded$triggerActions(double scrollAmount, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientWorld world = client.world;
        ClientPlayerEntity player = this.player instanceof ClientPlayerEntity cp ? cp : client.player;

        for (Event<ClientScrollEvents.ClientScrollAction> event : priorities) {
            if (event.invoker().onScroll(client, world, player, scrollAmount)) {
                ci.cancel();
                break;
            }
        }
    }
}
