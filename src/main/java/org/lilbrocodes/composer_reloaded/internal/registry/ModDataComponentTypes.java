package org.lilbrocodes.composer_reloaded.internal.registry;

//? if minecraft: >=1.21.4 {
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;
import org.lilbrocodes.composer_reloaded.api.v1.registry.lazy.DeferredDataComponentTypeRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModDataComponentTypes {
    private static final DeferredDataComponentTypeRegistry REGISTRY = new DeferredDataComponentTypeRegistry(ComposerReloaded.MOD_ID);

    public static final ComponentType<Integer> STEPS = REGISTRY.register(
            "steps",
            builder -> builder.codec(Codecs.NON_NEGATIVE_INT).packetCodec(PacketCodecs.INTEGER)
    );

    public static final ComponentType<Boolean> SOULBOUND = REGISTRY.register(
            "soulbound",
            builder -> builder
                    .codec(Codec.BOOL)
                    .packetCodec(PacketCodecs.BOOLEAN)
    );

    public static final ComponentType<Boolean> SOULBOUND_CAN_DROP = REGISTRY.register(
            "soulbound_can_drop",
            builder -> builder
                    .codec(Codec.BOOL)
                    .packetCodec(PacketCodecs.BOOLEAN)
    );

    public static void initialize() {

    }
}
//? }
