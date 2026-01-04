package com.codex.composer.internal.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import com.codex.composer.api.v1.toast.impl.AbstractToast;
import com.codex.composer.api.v1.toast.ToastManager;
import com.codex.composer.api.v1.util.misc.PacketSerializer;
import com.codex.composer.internal.Composer;
import com.codex.composer.internal.networking.handler.TriggerToastHandler;

import static com.codex.composer.api.v1.registry.ComposerRegistries.TOAST_SERIALIZERS;
import static com.codex.composer.api.v1.registry.ComposerRegistryKeys.TOAST_SERIALIZERS_KEY;

//? if minecraft: <=1.20.4 {
/*import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
*///? } else {
import net.minecraft.network.packet.CustomPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.codec.PacketCodec;
//? }

public record TriggerToastPayload<T extends AbstractToast>(T toast, ToastManager.Corner corner)
        implements /*? if minecraft: <=1.20.4 { *//*FabricPacket*//*? } else {*/CustomPayload/*?}*/ {
    public static final Identifier oID = Composer.identify("trigger_toast_s2c");

    private static TriggerToastPayload<?> read(PacketByteBuf buf) {
        Identifier id = buf.readIdentifier();
        PacketSerializer<? extends AbstractToast> serializer = TOAST_SERIALIZERS.getOrThrow(RegistryKey.of(TOAST_SERIALIZERS_KEY, id))/*? if minecraft: >=1.21.3 { */.value()/*? }*/;
        AbstractToast toast = serializer.read(buf);
        ToastManager.Corner corner = buf.readEnumConstant(ToastManager.Corner.class);
        return new TriggerToastPayload<>(toast, corner);
    }

    //? if minecraft: <= 1.20.4 {
    /*public static final Identifier ID = oID;

    @Override
    *///?}
    public void write(PacketByteBuf buf) {
        buf.writeIdentifier(toast.getId());
        @SuppressWarnings("unchecked")
        PacketSerializer<T> serializer = (PacketSerializer<T>) TOAST_SERIALIZERS.getOrThrow(RegistryKey.of(TOAST_SERIALIZERS_KEY, toast.getId()))/*? if minecraft: >=1.21.3 { */.value()/*? }*/;
        serializer.write(toast, buf);
        buf.writeEnumConstant(corner);
    }

    //? if minecraft: <=1.20.4 {
    /*private static final PacketType<TriggerToastPayload<?>> TYPE = PacketType.create(ID, TriggerToastPayload::read);
    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
    *///? } else {
    public static final PacketCodec<PacketByteBuf, TriggerToastPayload<?>> CODEC = PacketCodec.of(TriggerToastPayload::write, TriggerToastPayload::read);
    public static final CustomPayload.Id<TriggerToastPayload<?>> ID = new Id<>(oID);

    public CustomPayload.Id<TriggerToastPayload<?>> getId() {
        return ID;
    }
    //? }

    @Environment(EnvType.CLIENT)
    public static void registerHandler() {
        //? if minecraft: >=1.20.6 {
        PayloadTypeRegistry.playS2C().register(ID, CODEC);
        ClientPlayNetworking.registerGlobalReceiver(ID, new TriggerToastHandler());
        //?} else {
        /*ClientPlayNetworking.registerGlobalReceiver(TYPE, new TriggerToastHandler());
        *///?}
    }
}
