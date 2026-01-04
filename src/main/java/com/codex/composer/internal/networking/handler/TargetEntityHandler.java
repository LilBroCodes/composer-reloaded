package com.codex.composer.internal.networking.handler;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import com.codex.composer.internal.cca.ModCardinalComponents;
import com.codex.composer.internal.networking.TargetEntityPayload;

//? if minecraft: <=1.20.4 {
/*import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.network.ServerPlayerEntity;
*///?}


//? if minecraft: <=1.20.4 {
/*public class TargetEntityHandler implements ServerPlayNetworking.PlayPacketHandler<TargetEntityPayload> {
    *///?} else {
 public class TargetEntityHandler implements ServerPlayNetworking.PlayPayloadHandler<TargetEntityPayload> {
//?}
    //? if minecraft: <=1.20.4 {
    /*@Override
    public void receive(TargetEntityPayload payload, ServerPlayerEntity player, PacketSender sender) {
        ModCardinalComponents.TARGETED_ENTITY.get(player).setUuid(payload.uuid());
    }
    *///? } else {
    @Override
    public void receive(TargetEntityPayload payload, ServerPlayNetworking.Context context) {
        ModCardinalComponents.TARGETED_ENTITY.get(context.player()).setUuid(payload.uuid());
    }
    //?}
}
