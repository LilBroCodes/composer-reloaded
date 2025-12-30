package org.lilbrocodes.composer_reloaded.internal.networking.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.lilbrocodes.composer_reloaded.api.v1.toast.NotifyToast;
import org.lilbrocodes.composer_reloaded.api.v1.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.internal.networking.NotifyToastPayload;

//? if minecraft: <=1.20.4 {
/*import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
 *///? }

//? if minecraft: <=1.20.4 {
/*public class NotifyToastHandler implements ClientPlayNetworking.PlayPacketHandler<NotifyToastPayload> {
    *///?} else {
 public class NotifyToastHandler implements ClientPlayNetworking.PlayPayloadHandler<NotifyToastPayload> {
//?}
    @Override
    //? if minecraft: <=1.20.4 {
    /*public void receive(NotifyToastPayload payload, ClientPlayerEntity player, PacketSender sender) {
    *///? } else {
    public void receive(NotifyToastPayload payload, ClientPlayNetworking.Context context) {
    //? }
        ToastManager.getInstance().addToast(new NotifyToast(payload.message(), payload.backgroundColor(), payload.borderColor()), payload.corner());
    }
}
