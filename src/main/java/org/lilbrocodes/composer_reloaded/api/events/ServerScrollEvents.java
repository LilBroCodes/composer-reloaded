package org.lilbrocodes.composer_reloaded.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ServerScrollEvents {
    public static final Event<ServerScrollAction> HIGH_PRIORITY = createEvent();
    public static final Event<ServerScrollAction> MEDIUM_PRIORITY = createEvent();
    public static final Event<ServerScrollAction> LOW_PRIORITY = createEvent();

    private static Event<ServerScrollAction> createEvent() {
        return EventFactory.createArrayBacked(ServerScrollAction.class,
                callbacks -> (channel, player, sender, scrollAmount) -> callActions(callbacks, channel, player, sender, scrollAmount)
        );
    }

    @FunctionalInterface
    public interface ServerScrollAction {
        boolean onScroll(Identifier channel, ServerPlayerEntity player, PacketSender sender, double scrollAmount);
        default boolean onChannel(Identifier channel, Identifier correctChannel) {
            return channel.equals(correctChannel);
        }
    }

    private static boolean callActions(ServerScrollAction[] callbacks, Identifier channel, ServerPlayerEntity player, PacketSender sender, double scrollAmount) {
        for (ServerScrollAction action : callbacks) if (action.onScroll(channel, player, sender, scrollAmount)) return true;
        return false;
    }
}
