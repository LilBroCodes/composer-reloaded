package org.lilbrocodes.composer_reloaded.api.nbt;

import net.minecraft.nbt.NbtCompound;
import org.lilbrocodes.composer_reloaded.api.nbt.serial.SerializableBoolean;

/**
 * Common interface for enum classes that implement serializers for some value type.
 * No this is not the same as {@link NbtSerializable}, that needs to be implemented
 * by the object itself (like {@link SerializableBoolean}).
 */
public interface EnumSerializer<T> {
    void write(NbtCompound target, T value);
    T read(NbtCompound source);
}
