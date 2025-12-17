package org.lilbrocodes.composer_reloaded.internal.networking.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
import org.lilbrocodes.composer_reloaded.api.toast.SimpleToast;
import org.lilbrocodes.composer_reloaded.api.toast.ToastManager;
import org.lilbrocodes.composer_reloaded.internal.networking.SimpleToastPayload;

public class SimpleToastHandler implements ClientPlayNetworking.PlayPacketHandler<SimpleToastPayload> {
    @Override
    public void receive(SimpleToastPayload payload, ClientPlayerEntity player, PacketSender sender) {
        ToastManager.getInstance().addToast(new SimpleToast(payload.iconTexture(), payload.message(), payload.backgroundColor(), payload.borderColor()), payload.corner());
    }
}
