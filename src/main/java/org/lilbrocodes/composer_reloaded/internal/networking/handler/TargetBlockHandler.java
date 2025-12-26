package org.lilbrocodes.composer_reloaded.internal.networking.handler;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.lilbrocodes.composer_reloaded.internal.cca.ModCardinalComponents;
import org.lilbrocodes.composer_reloaded.internal.networking.TargetBlockPayload;

//? if minecraft: <=1.20.1 {
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.network.ServerPlayerEntity;
//?}

//? if minecraft: <=1.20.1 {
public class TargetBlockHandler implements ServerPlayNetworking.PlayPacketHandler<TargetBlockPayload> {
    //?} else {
 /*public class TargetBlockHandler implements ServerPlayNetworking.PlayPayloadHandler<TargetBlockPayload> {
*///?}
    //? if minecraft: <=1.20.1 {
    @Override
    public void receive(TargetBlockPayload payload, ServerPlayerEntity player, PacketSender sender) {
        ModCardinalComponents.TARGETED_BLOCK.get(player).setPos(payload.pos());
    }
    //? } else {
    /*@Override
    public void receive(TargetBlockPayload payload, ServerPlayNetworking.Context context) {
        ModCardinalComponents.TARGETED_BLOCK.get(context.player()).setPos(payload.pos());
    }
    *///?}
}
