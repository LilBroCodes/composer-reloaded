package org.lilbrocodes.composer_reloaded.internal.registry;

//? if minecraft: >=1.21.4 {
/*import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredDataComponentTypeRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModDataComponentTypes {
    private static final DeferredDataComponentTypeRegistry REGISTRY = new DeferredDataComponentTypeRegistry(ComposerReloaded.MOD_ID);

    public static final ComponentType<Integer> STEPS = REGISTRY.register(
            "steps",
            builder -> builder.codec(Codecs.NON_NEGATIVE_INT).packetCodec(PacketCodecs.INTEGER)
    );

    public static void initialize() {

    }
}
*///? }
