package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registry;
import org.lilbrocodes.composer_reloaded.api.v1.toast.impl.AbstractToast;
import org.lilbrocodes.composer_reloaded.api.v1.toast.impl.SimpleToast;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.PacketSerializer;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModToastSerializers {
    public static PacketSerializer<AbstractToast> EMPTY;
    public static PacketSerializer<SimpleToast> SIMPLE;

    public static void initialize() {

    }

    public static PacketSerializer<? extends AbstractToast> registerAndGetDefault(Registry<PacketSerializer<? extends AbstractToast>> registry) {
        EMPTY = Registry.register(
                registry,
                ComposerReloaded.identify("empty"),
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
                ComposerReloaded.identify("simple"),
                new SimpleToast.Serializer()
        );

        return EMPTY;
    }
}
