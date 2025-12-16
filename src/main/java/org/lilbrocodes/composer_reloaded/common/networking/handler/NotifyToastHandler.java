package org.lilbrocodes.composer_reloaded.common.networking.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
import org.lilbrocodes.composer_reloaded.api.toast.NotifyToast;
import org.lilbrocodes.composer_reloaded.api.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.common.networking.NotifyToastPayload;

public class NotifyToastHandler implements ClientPlayNetworking.PlayPacketHandler<NotifyToastPayload> {
    @Override
    public void receive(NotifyToastPayload payload, ClientPlayerEntity player, PacketSender sender) {
        ToastManager.getInstance().addToast(new NotifyToast(payload.message(), payload.backgroundColor(), payload.borderColor()), payload.corner());
    }
}
