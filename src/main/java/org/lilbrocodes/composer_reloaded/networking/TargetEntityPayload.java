package org.lilbrocodes.composer_reloaded.networking;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.components.entry.ModEntityComponents;

import java.util.UUID;

public record TargetEntityPayload(UUID uuid) implements FabricPacket {
    private static final Identifier ID = ComposerReloaded.TARGET_ENTITY;
    private static final PacketType<TargetEntityPayload> TYPE = PacketType.create(ID, TargetEntityPayload::read);

    private static TargetEntityPayload read(PacketByteBuf buf) {
        return new TargetEntityPayload(buf.readUuid());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeUuid(uuid);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static void registerHandler() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, playNetworkHandler, buf, sender) -> {
            ModEntityComponents.TARGETED_ENTITY.get(player).setUuid(player, read(buf).uuid);
        });
    }
}
