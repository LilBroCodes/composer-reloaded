package com.codex.composer.internal.registry;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registry;
import com.codex.composer.api.v1.toast.impl.AbstractToast;
import com.codex.composer.api.v1.toast.impl.SimpleToast;
import com.codex.composer.api.v1.util.misc.PacketSerializer;
import com.codex.composer.internal.Composer;

public class ModToastSerializers {
    public static PacketSerializer<AbstractToast> EMPTY;
    public static PacketSerializer<SimpleToast> SIMPLE;

    public static void initialize() {

    }

    public static PacketSerializer<? extends AbstractToast> registerAndGetDefault(Registry<PacketSerializer<? extends AbstractToast>> registry) {
        EMPTY = Registry.register(
                registry,
                Composer.identify("empty"),
                new PacketSerializer<>() {
                    @Override
                    public void write(AbstractToast object, PacketByteBuf buf) {
                        throw new IllegalStateException("Toast " + object.getClass().getSimpleName() + " has no toast serializer registered!");
                    }

                    @Override
                    public AbstractToast read(PacketByteBuf buf) {
                        throw new IllegalStateException("A toast has no toast serializer registered!");
                    }
                }
        );
        SIMPLE = Registry.register(
                registry,
                Composer.identify("simple"),
                new SimpleToast.Serializer()
        );

        return EMPTY;
    }
}
