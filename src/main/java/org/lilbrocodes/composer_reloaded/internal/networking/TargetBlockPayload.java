package org.lilbrocodes.composer_reloaded.internal.networking;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.cca.ModCardinalComponents;

public record TargetBlockPayload(BlockPos pos) implements FabricPacket {
    public static final Identifier TARGET_BLOCK = ComposerReloaded.identify("target_block_c2s");
    private static final Identifier ID = TARGET_BLOCK;
    private static final PacketType<TargetBlockPayload> TYPE = PacketType.create(ID, TargetBlockPayload::read);

    private static TargetBlockPayload read(PacketByteBuf buf) {
        return new TargetBlockPayload(buf.readBlockPos());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static void registerHandler() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, playNetworkHandler, buf, sender) -> ModCardinalComponents.TARGETED_BLOCK.get(player).setPos(read(buf).pos));
    }
}
