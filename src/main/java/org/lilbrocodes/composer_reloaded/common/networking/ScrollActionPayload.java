package org.lilbrocodes.composer_reloaded.common.networking;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.common.networking.handler.ScrollActionHandler;

public record ScrollActionPayload(Identifier channel, double scrollAmount) implements FabricPacket {
    private static final Identifier ID = ComposerReloaded.SCROLL_ACTION;
    private static final PacketType<ScrollActionPayload> TYPE = PacketType.create(ID, ScrollActionPayload::read);

    private static ScrollActionPayload read(PacketByteBuf buf) {
        return new ScrollActionPayload(buf.readIdentifier(), buf.readDouble());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeIdentifier(channel);
        buf.writeDouble(scrollAmount);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static void registerHandler() {
        ServerPlayNetworking.registerGlobalReceiver(TYPE, new ScrollActionHandler());
    }
}
