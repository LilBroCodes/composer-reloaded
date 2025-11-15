package org.lilbrocodes.composer_reloaded.common.networking.handler;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import org.lilbrocodes.composer_reloaded.api.events.ServerScrollEvents;
import org.lilbrocodes.composer_reloaded.api.events.ServerScrollEvents.ServerScrollAction;
import org.lilbrocodes.composer_reloaded.common.networking.ScrollActionPayload;

import java.util.List;

public class ScrollActionHandler implements ServerPlayNetworking.PlayPacketHandler<ScrollActionPayload> {
    private static final List<Event<ServerScrollAction>> priorities = List.of(
            ServerScrollEvents.HIGH_PRIORITY,
            ServerScrollEvents.MEDIUM_PRIORITY,
            ServerScrollEvents.LOW_PRIORITY
    );

    @Override
    public void receive(ScrollActionPayload payload, ServerPlayerEntity player, PacketSender sender) {
        for (Event<ServerScrollAction> event : priorities) if (event.invoker().onScroll(payload.channel(), player, sender, payload.scrollAmount())) return;
    }
}
