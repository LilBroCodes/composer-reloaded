package org.lilbrocodes.composer_reloaded.internal.networking.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.lilbrocodes.composer_reloaded.api.v1.overlay.OverlayManager;
import org.lilbrocodes.composer_reloaded.internal.networking.ShowOverlayPayload;

//? if minecraft: <=1.20.4 {
/*import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
*///? }

//? if minecraft: <=1.20.4 {
/*public class ShowOverlayHandler implements ClientPlayNetworking.PlayPacketHandler<ShowOverlayPayload<?>> {
*///?} else {
 public class ShowOverlayHandler implements ClientPlayNetworking.PlayPayloadHandler<ShowOverlayPayload<?>> {
//?}
    @Override
    //? if minecraft: <=1.20.4 {
    /*public void receive(ShowOverlayPayload<?> payload, ClientPlayerEntity player, PacketSender sender) {
    *///? } else {
    public void receive(ShowOverlayPayload<?> payload, ClientPlayNetworking.Context context) {
    //? }
        OverlayManager.getInstance().queue(payload.overlay());
    }
}
