package com.codex.composer.internal.registry;


//? if minecraft: >= 1.21 {
import net.minecraft.component.ComponentType;
//? } else if minecraft: >=1.20.6 {
/*import net.minecraft.component.DataComponentType;
*///? }

//? if minecraft: >=1.20.6 {
import com.mojang.serialization.Codec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;
import com.codex.composer.api.v1.registry.lazy.DeferredDataComponentTypeRegistry;
import com.codex.composer.internal.Composer;

public class ModDataComponentTypes {
    private static final DeferredDataComponentTypeRegistry REGISTRY = new DeferredDataComponentTypeRegistry(Composer.MOD_ID);

    public static final /*? minecraft: >=1.21 {*/ComponentType/*? } else {*//*DataComponentType*//*? }*/<Integer> STEPS = REGISTRY.register(
            "steps",
            builder -> builder.codec(Codecs./*? minecraft: >=1.21.3 {*/NON_NEGATIVE_INT/*? } else {*//*NONNEGATIVE_INT*//*? }*/).packetCodec(PacketCodecs.INTEGER)
    );

    public static final /*? minecraft: >=1.21 {*/ComponentType/*? } else {*//*DataComponentType*//*? }*/<Boolean> SOULBOUND = REGISTRY.register(
            "soulbound",
            builder -> builder
                    .codec(Codec.BOOL)
                    .packetCodec(PacketCodecs./*? minecraft: >=1.21.4 {*/BOOLEAN/*? } else {*//*BOOL*//*? }*/)
    );

    public static final /*? minecraft: >=1.21 {*/ComponentType/*? } else {*//*DataComponentType*//*? }*/<Boolean> SOULBOUND_CAN_DROP = REGISTRY.register(
            "soulbound_can_drop",
            builder -> builder
                    .codec(Codec.BOOL)
                    .packetCodec(PacketCodecs./*? minecraft: >=1.21.4 {*/BOOLEAN/*? } else {*//*BOOL*//*? }*/)
    );

    public static void initialize() {

    }
}
//? }
