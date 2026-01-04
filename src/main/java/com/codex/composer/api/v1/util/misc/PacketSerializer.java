package com.codex.composer.api.v1.util.misc;

import net.minecraft.network.PacketByteBuf;

public interface PacketSerializer<T> {
    void write(T object, PacketByteBuf buf);
    T read(PacketByteBuf buf);
}
