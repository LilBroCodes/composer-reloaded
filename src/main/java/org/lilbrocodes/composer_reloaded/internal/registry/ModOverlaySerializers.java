package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registry;
import org.lilbrocodes.composer_reloaded.api.v1.overlay.Overlays;
import org.lilbrocodes.composer_reloaded.api.v1.overlay.impl.Overlay;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.PacketSerializer;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModOverlaySerializers {
    public static PacketSerializer<Overlay> EMPTY;
    public static PacketSerializer<Overlays.TextureOverlay> TEXTURE;
    public static PacketSerializer<Overlays.TextOverlay> TEXT;

    public static void initialize() {

    }

    public static PacketSerializer<? extends Overlay> registerAndGetDefault(Registry<PacketSerializer<? extends Overlay>> registry) {
        EMPTY = Registry.register(
                registry,
                ComposerReloaded.identify("empty"),
                new PacketSerializer<>() {
                    @Override
                    public void write(Overlay object, PacketByteBuf buf) {
                        throw new IllegalStateException("Overlay " + object.getClass().getSimpleName() + " has no toast serializer registered!");
                    }

                    @Override
                    public Overlay read(PacketByteBuf buf) {
                        throw new IllegalStateException("An overlay has no toast serializer registered!");
                    }
                }
        );

        TEXTURE = Registry.register(registry, ComposerReloaded.identify("textured"), new Overlays.TextureOverlay.Serializer());
        TEXT = Registry.register(registry, ComposerReloaded.identify("text"), new Overlays.TextOverlay.Serializer());

        return EMPTY;
    }
}
