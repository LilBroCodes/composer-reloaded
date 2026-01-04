package com.codex.composer.internal.networking.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import com.codex.composer.api.v1.toast.ToastManager;
import com.codex.composer.internal.networking.TriggerToastPayload;

//? if minecraft: <=1.20.4 {
/*import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
*///? }

//? if minecraft: <=1.20.4 {
/*public class TriggerToastHandler implements ClientPlayNetworking.PlayPacketHandler<TriggerToastPayload<?>> {
    *///?} else {
 public class TriggerToastHandler implements ClientPlayNetworking.PlayPayloadHandler<TriggerToastPayload<?>> {
//?}
    @Override
    //? if minecraft: <=1.20.4 {
    /*public void receive(TriggerToastPayload<?> payload, ClientPlayerEntity player, PacketSender sender) {
    *///? } else {
    public void receive(TriggerToastPayload<?> payload, ClientPlayNetworking.Context context) {
    //? }
        ToastManager.getInstance().addToast(payload.toast(), payload.corner());
    }
}
