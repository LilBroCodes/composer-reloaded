package org.lilbrocodes.composer_reloaded.internal.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.networking.handler.ClearToastsHandler;

public record ClearToastsPayload() implements FabricPacket {
    public static final Identifier SCROLL_ACTION = ComposerReloaded.identify("clear_toasts_s2c");
    private static final Identifier ID = SCROLL_ACTION;
    private static final PacketType<ClearToastsPayload> TYPE = PacketType.create(ID, ClearToastsPayload::read);

    private static ClearToastsPayload read(PacketByteBuf buf) {
        return new ClearToastsPayload();
    }

    @Override
    public void write(PacketByteBuf buf) {

    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void registerHandler() {
        ClientPlayNetworking.registerGlobalReceiver(TYPE, new ClearToastsHandler());
    }
}
