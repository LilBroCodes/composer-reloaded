package org.lilbrocodes.composer_reloaded.internal.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.v1.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.networking.handler.NotifyToastHandler;

//? if minecraft: <=1.20.1 {
/*import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
*///? } else {
import net.minecraft.network.packet.CustomPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.codec.PacketCodec;
 //? }

public record NotifyToastPayload(String message, ToastManager.Corner corner, int backgroundColor, int borderColor)
        implements /*? if minecraft: <=1.20.1 { *//*FabricPacket*//*? } else {*/CustomPayload/*?}*/ {
    public static final Identifier oID = ComposerReloaded.identify("notify_toast_s2c");

    private NotifyToastPayload(PacketByteBuf buf) {
        this(
                buf.readString(),
                buf.readEnumConstant(ToastManager.Corner.class),
                buf.readInt(),
                buf.readInt()
        );
    }

    //? if minecraft: <= 1.20.1 {
    /*public static final Identifier ID = oID;

    @Override
    *///?}
    public void write(PacketByteBuf buf) {
        buf.writeString(message);
        buf.writeEnumConstant(corner);
        buf.writeInt(backgroundColor);
        buf.writeInt(borderColor);
    }

    //? if minecraft: <=1.20.1 {
    /*private static final PacketType<NotifyToastPayload> TYPE = PacketType.create(oID, NotifyToastPayload::new);
    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
    *///? } else {
    public static final PacketCodec<PacketByteBuf, NotifyToastPayload> CODEC = PacketCodec.of(NotifyToastPayload::write, NotifyToastPayload::new);
    public static final CustomPayload.Id<NotifyToastPayload> ID = new Id<>(oID);

    public CustomPayload.Id<NotifyToastPayload> getId() {
        return ID;
    }
    //? }

    @Environment(EnvType.CLIENT)
    public static void registerHandler() {
        //? if minecraft: >=1.21.4 {
        PayloadTypeRegistry.playS2C().register(ID, CODEC);
        ClientPlayNetworking.registerGlobalReceiver(ID, new NotifyToastHandler());
        //?} else {
        /*ClientPlayNetworking.registerGlobalReceiver(TYPE, new NotifyToastHandler());
        *///?}
    }
}
