package org.lilbrocodes.composer_reloaded.internal.networking.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
import org.lilbrocodes.composer_reloaded.api.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.internal.networking.ClearToastsPayload;

public class ClearToastsHandler implements ClientPlayNetworking.PlayPacketHandler<ClearToastsPayload> {
    @Override
    public void receive(ClearToastsPayload payload, ClientPlayerEntity player, PacketSender sender) {
        ToastManager.getInstance().clear();
    }
}
