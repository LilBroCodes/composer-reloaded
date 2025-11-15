package org.lilbrocodes.composer_reloaded.api.events;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.common.networking.ScrollActionPayload;

public class ClientScrollEvents {
    public static final Event<ClientScrollAction> HIGH_PRIORITY = createEvent();
    public static final Event<ClientScrollAction> MEDIUM_PRIORITY = createEvent();
    public static final Event<ClientScrollAction> LOW_PRIORITY = createEvent();

    private static Event<ClientScrollAction> createEvent() {
        return EventFactory.createArrayBacked(ClientScrollAction.class,
                callbacks -> (client, world, player, scrollAmount) -> callActions(callbacks, client, world, player, scrollAmount)
        );
    }

    @FunctionalInterface
    public interface ClientScrollAction {
        boolean onScroll(MinecraftClient client, @Nullable ClientWorld world, @Nullable ClientPlayerEntity player, double scrollAmount);

        default void sync(Identifier channel, double scrollAmount) {
            ClientPlayNetworking.send(new ScrollActionPayload(channel, scrollAmount));
        }
    }

    private static boolean callActions(ClientScrollAction[] callbacks, MinecraftClient client, @Nullable ClientWorld world, @Nullable ClientPlayerEntity player, double scrollAmount) {
        for (ClientScrollAction action : callbacks) if (action.onScroll(client, world, player, scrollAmount)) return true;
        return false;
    }
}
