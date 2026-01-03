package org.lilbrocodes.composer_reloaded.api.v1.registry;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.lilbrocodes.composer_reloaded.api.v1.advancement.ComposerAdvancement;
import org.lilbrocodes.composer_reloaded.api.v1.overlay.impl.Overlay;
import org.lilbrocodes.composer_reloaded.api.v1.toast.impl.AbstractToast;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.PacketSerializer;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ComposerRegistryKeys {
    public static final RegistryKey<Registry<ComposerAdvancement>> COMPOSER_ADVANCEMENT_KEY = of("composer_advancement");
    public static final RegistryKey<Registry<PacketSerializer<? extends AbstractToast>>> TOAST_SERIALIZERS_KEY = of("toast_serializer");
    public static final RegistryKey<Registry<PacketSerializer<? extends Overlay>>> OVERLAY_SERIALIZERS_KEY = of("overlay_serializer");

    private static <T> RegistryKey<Registry<T>> of(String name) {
        return RegistryKey.ofRegistry(ComposerReloaded.identify(name));
    }
}
