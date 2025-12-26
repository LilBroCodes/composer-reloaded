package org.lilbrocodes.composer_reloaded.internal.networking.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.lilbrocodes.composer_reloaded.api.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.internal.networking.ClearToastsPayload;

//? if minecraft: <=1.20.1 {
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
//? }

//? if minecraft: <=1.20.1 {
public class ClearToastsHandler implements ClientPlayNetworking.PlayPacketHandler<ClearToastsPayload> {
//?} else {
/*public class ClearToastsHandler implements ClientPlayNetworking.PlayPayloadHandler<ClearToastsPayload> {
*///?}
    @Override
    //? if minecraft: <=1.20.1 {
    public void receive(ClearToastsPayload payload, ClientPlayerEntity player, PacketSender sender) {
    //? } else {
    /*public void receive(ClearToastsPayload payload, ClientPlayNetworking.Context context) {
    *///? }
        ToastManager.getInstance().clear();
    }
}
