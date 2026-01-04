package com.codex.composer.api.v1.easytags.data;

import com.codex.composer.api.v1.nbt.NbtSerializable;

/**
 * Marker interface for item stack data that can be serialized to NBT.
 * <p>
 * Extends {@link NbtSerializable} to indicate that instances can be written to NBT
 * and potentially deserialized.
 * </p>
 *
 * @param <T> the concrete type of the implementing class
 */
public interface ItemStackData<T extends ItemStackData<T>> extends NbtSerializable<T> {

}
