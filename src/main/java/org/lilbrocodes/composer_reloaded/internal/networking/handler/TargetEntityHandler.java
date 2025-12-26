package org.lilbrocodes.composer_reloaded.internal.networking.handler;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.lilbrocodes.composer_reloaded.internal.cca.ModCardinalComponents;
import org.lilbrocodes.composer_reloaded.internal.networking.TargetEntityPayload;

//? if minecraft: <=1.20.1 {
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.network.ServerPlayerEntity;
//?}


//? if minecraft: <=1.20.1 {
public class TargetEntityHandler implements ServerPlayNetworking.PlayPacketHandler<TargetEntityPayload> {
    //?} else {
 /*public class TargetEntityHandler implements ServerPlayNetworking.PlayPayloadHandler<TargetEntityPayload> {
*///?}
    //? if minecraft: <=1.20.1 {
    @Override
    public void receive(TargetEntityPayload payload, ServerPlayerEntity player, PacketSender sender) {
        ModCardinalComponents.TARGETED_ENTITY.get(player).setUuid(payload.uuid());
    }
    //? } else {
    /*@Override
    public void receive(TargetEntityPayload payload, ServerPlayNetworking.Context context) {
        ModCardinalComponents.TARGETED_ENTITY.get(context.player()).setUuid(payload.uuid());
    }
    *///?}
}
