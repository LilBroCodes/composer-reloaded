package com.codex.composer.internal.networking.handler;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import com.codex.composer.internal.cca.ModCardinalComponents;
import com.codex.composer.internal.networking.TargetBlockPayload;

//? if minecraft: <=1.20.4 {
/*import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.network.ServerPlayerEntity;
*///?}

//? if minecraft: <=1.20.4 {
/*public class TargetBlockHandler implements ServerPlayNetworking.PlayPacketHandler<TargetBlockPayload> {
    *///?} else {
 public class TargetBlockHandler implements ServerPlayNetworking.PlayPayloadHandler<TargetBlockPayload> {
//?}
    //? if minecraft: <=1.20.4 {
    /*@Override
    public void receive(TargetBlockPayload payload, ServerPlayerEntity player, PacketSender sender) {
        ModCardinalComponents.TARGETED_BLOCK.get(player).setPos(payload.pos());
    }
    *///? } else {
    @Override
    public void receive(TargetBlockPayload payload, ServerPlayNetworking.Context context) {
        ModCardinalComponents.TARGETED_BLOCK.get(context.player()).setPos(payload.pos());
    }
    //?}
}
