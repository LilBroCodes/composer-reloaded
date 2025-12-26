package org.lilbrocodes.composer_reloaded.internal.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.networking.handler.TargetEntityHandler;

import java.util.UUID;

//? if minecraft: <=1.20.1 {
/*import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
*///? } else {
import net.minecraft.network.packet.CustomPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.codec.PacketCodec;
//? }

public record TargetEntityPayload(UUID uuid)
        implements /*? if minecraft: <=1.20.1 { *//*FabricPacket*//*? } else {*/CustomPayload/*?}*/ {
    public static final Identifier oID = ComposerReloaded.identify("target_entity_c2s");

    private TargetEntityPayload(PacketByteBuf buf) {
        this(buf.readUuid());
    }

    //? if minecraft: <= 1.20.1 {
    /*public static final Identifier ID = oID;

    @Override
    *///?}
    public void write(PacketByteBuf buf) {
        buf.writeUuid(uuid);
    }

    //? if minecraft: <=1.20.1 {
    /*private static final PacketType<TargetEntityPayload> TYPE = PacketType.create(ID, TargetEntityPayload::new);
    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
    *///? } else {
    public static final PacketCodec<PacketByteBuf, TargetEntityPayload> CODEC = PacketCodec.of(TargetEntityPayload::write, TargetEntityPayload::new);
    public static final CustomPayload.Id<TargetEntityPayload> ID = new Id<>(oID);

    public CustomPayload.Id<TargetEntityPayload> getId() {
        return ID;
    }
    //? }

    public static void registerHandler() {
        //? if minecraft: >=1.21.4 {
        PayloadTypeRegistry.playC2S().register(ID, CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ID, new TargetEntityHandler());
        //?} else {
        /*ServerPlayNetworking.registerGlobalReceiver(TYPE, new TargetEntityHandler());
        *///?}
    }
}
