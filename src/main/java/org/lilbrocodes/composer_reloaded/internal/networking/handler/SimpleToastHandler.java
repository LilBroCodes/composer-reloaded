package org.lilbrocodes.composer_reloaded.internal.networking.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.lilbrocodes.composer_reloaded.api.v1.toast.SimpleToast;
import org.lilbrocodes.composer_reloaded.api.v1.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.internal.networking.SimpleToastPayload;

//? if minecraft: <=1.20.4 {
/*import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
*///? }

//? if minecraft: <=1.20.4 {
/*public class SimpleToastHandler implements ClientPlayNetworking.PlayPacketHandler<SimpleToastPayload> {
    *///?} else {
 public class SimpleToastHandler implements ClientPlayNetworking.PlayPayloadHandler<SimpleToastPayload> {
//?}
    @Override
    //? if minecraft: <=1.20.4 {
    /*public void receive(SimpleToastPayload payload, ClientPlayerEntity player, PacketSender sender) {
    *///? } else {
    public void receive(SimpleToastPayload payload, ClientPlayNetworking.Context context) {
    //? }
        ToastManager.getInstance().addToast(
                new SimpleToast(payload.iconTexture(), payload.message(), payload.backgroundColor(), payload.borderColor()),
                payload.corner()
        );
    }
}
