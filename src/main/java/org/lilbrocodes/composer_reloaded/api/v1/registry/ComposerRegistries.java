package org.lilbrocodes.composer_reloaded.api.v1.registry;

import net.minecraft.registry.Registry;
import org.lilbrocodes.composer_reloaded.api.v1.advancement.ComposerAdvancement;
import org.lilbrocodes.composer_reloaded.api.v1.overlay.impl.Overlay;
import org.lilbrocodes.composer_reloaded.api.v1.toast.impl.AbstractToast;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.PacketSerializer;

public class ComposerRegistries {
    public static  Registry<ComposerAdvancement> COMPOSER_ADVANCEMENTS;
    public static Registry<PacketSerializer<? extends AbstractToast>> TOAST_SERIALIZERS;
    public static Registry<PacketSerializer<? extends Overlay>> OVERLAY_SERIALIZERS;
}
