package org.lilbrocodes.composer_reloaded.api.nbt;

import net.minecraft.nbt.NbtCompound;

public interface NbtSerializable<T extends NbtSerializable<T>> {
    NbtCompound writeNbt(NbtCompound tag);

    default NbtCompound writeNbt() {
        return writeNbt(new NbtCompound());
    }
}
