package com.codex.composer.internal.registry;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registry;
import com.codex.composer.api.v1.overlay.Overlays;
import com.codex.composer.api.v1.overlay.impl.Overlay;
import com.codex.composer.api.v1.util.misc.PacketSerializer;
import com.codex.composer.internal.Composer;

public class ModOverlaySerializers {
    public static PacketSerializer<Overlay> EMPTY;
    public static PacketSerializer<Overlays.TextureOverlay> TEXTURE;
    public static PacketSerializer<Overlays.TextOverlay> TEXT;

    public static void initialize() {

    }

    public static PacketSerializer<? extends Overlay> registerAndGetDefault(Registry<PacketSerializer<? extends Overlay>> registry) {
        EMPTY = Registry.register(
                registry,
                Composer.identify("empty"),
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

        TEXTURE = Registry.register(registry, Composer.identify("textured"), new Overlays.TextureOverlay.Serializer());
        TEXT = Registry.register(registry, Composer.identify("text"), new Overlays.TextOverlay.Serializer());

        return EMPTY;
    }
}
