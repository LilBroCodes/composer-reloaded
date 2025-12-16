package org.lilbrocodes.composer_reloaded.common.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.common.networking.handler.NotifyToastHandler;

public record NotifyToastPayload(String message, ToastManager.Corner corner, int backgroundColor, int borderColor) implements FabricPacket {
    public static final Identifier SCROLL_ACTION = ComposerReloaded.identify("notify_toast_s2c");
    private static final Identifier ID = SCROLL_ACTION;
    private static final PacketType<NotifyToastPayload> TYPE = PacketType.create(ID, NotifyToastPayload::read);

    private static NotifyToastPayload read(PacketByteBuf buf) {
        return new NotifyToastPayload(buf.readString(), buf.readEnumConstant(ToastManager.Corner.class), buf.readInt(), buf.readInt());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(message);
        buf.writeEnumConstant(corner);
        buf.writeInt(backgroundColor);
        buf.writeInt(borderColor);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static void registerHandler() {
        ClientPlayNetworking.registerGlobalReceiver(TYPE, new NotifyToastHandler());
    }
}
