package org.lilbrocodes.composer_reloaded.internal.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.networking.handler.ClearToastsHandler;

//? if minecraft: <=1.20.1 {
/*import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import org.lilbrocodes.composer_reloaded.internal.networking.handler.ScrollActionHandler;
*///? } else {
import net.minecraft.network.packet.CustomPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.codec.PacketCodec;
//? }

public record ClearToastsPayload()
        implements /*? if minecraft: <=1.20.1 { *//*FabricPacket*//*? } else {*/CustomPayload/*?}*/ {

    public static final Identifier oID = ComposerReloaded.identify("clear_toasts_s2c");

    //? if minecraft: <=1.20.1 {
    /*public static final Identifier ID = oID;

    @Override
    public void write(PacketByteBuf buf) {
        // nothing to write
    }

    public ClearToastsPayload(PacketByteBuf ignored) {
        this();
    }
    *///?}

    //? if minecraft: <=1.20.1 {
    /*private static final PacketType<ClearToastsPayload> TYPE = PacketType.create(oID, ClearToastsPayload::new);

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
    *///? } else {
    public static final PacketCodec<PacketByteBuf, ClearToastsPayload> CODEC = PacketCodec.unit(new ClearToastsPayload());
    public static final CustomPayload.Id<ClearToastsPayload> ID = new CustomPayload.Id<>(oID);

    public CustomPayload.Id<ClearToastsPayload> getId() {
        return ID;
    }
    //? }

    @Environment(EnvType.CLIENT)
    public static void registerHandler() {
        //? if minecraft: >=1.21.4 {
        PayloadTypeRegistry.playS2C().register(ID, CODEC);
        ClientPlayNetworking.registerGlobalReceiver(ID, new ClearToastsHandler());
        //?} else {
        /*ClientPlayNetworking.registerGlobalReceiver(TYPE, new ClearToastsHandler());
        *///?}
    }
}
