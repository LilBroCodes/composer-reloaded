package com.codex.composer.api.v1.easytags.registry;

import com.codex.composer.api.v1.nbt.ComposerCompound;

/**
 * Interface for defining custom serialization and deserialization logic
 * for specific types in the Automata serialization system.
 *
 * @param <T> the type of object this serializer can handle
 */
public interface AutomataSerializable<T> {

    /**
     * Writes the given value into a {@link ComposerCompound} using the provided key.
     *
     * @param tag   the {@link ComposerCompound} to write into
     * @param key   the key under which to store the value
     * @param value the value to serialize
     */
    void write(ComposerCompound tag, String key, T value);

    /**
     * Reads a value from a {@link ComposerCompound} using the provided key.
     *
     * @param tag the {@link ComposerCompound} to read from
     * @param key the key to read the value from
     * @return the deserialized value
     */
    T read(ComposerCompound tag, String key);
}
