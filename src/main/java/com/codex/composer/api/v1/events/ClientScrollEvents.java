package com.codex.composer.api.v1.events;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import com.codex.composer.internal.networking.ScrollActionPayload;

/**
 * Provides client-side scroll events for mods.
 * <p>
 * Supports multiple priority levels (HIGH, MEDIUM, LOW) for event handling.
 * Listeners can synchronize scroll actions to the server using {@link ClientScrollAction#sync}.
 * </p>
 */
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
        /**
         * Called when a scroll action occurs on the client.
         *
         * @param client       the Minecraft client instance
         * @param world        the current client world, nullable
         * @param player       the client player, nullable
         * @param scrollAmount the amount scrolled
         * @return true to cancel further processing, false otherwise
         */
        boolean onScroll(MinecraftClient client, @Nullable ClientWorld world, @Nullable ClientPlayerEntity player, double scrollAmount);

        /**
         * Synchronizes a scroll action to the server using the specified channel and scroll amount.
         *
         * @param channel      the scroll channel identifier
         * @param scrollAmount the amount scrolled
         */
        default void sync(Identifier channel, double scrollAmount) {
            ClientPlayNetworking.send(new ScrollActionPayload(channel, scrollAmount));
        }
    }

    private static boolean callActions(ClientScrollAction[] callbacks, MinecraftClient client, @Nullable ClientWorld world, @Nullable ClientPlayerEntity player, double scrollAmount) {
        for (ClientScrollAction action : callbacks)
            if (action.onScroll(client, world, player, scrollAmount)) return true;
        return false;
    }
}
