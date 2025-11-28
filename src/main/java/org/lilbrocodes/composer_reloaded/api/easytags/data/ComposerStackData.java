package org.lilbrocodes.composer_reloaded.api.easytags.data;

import net.minecraft.nbt.NbtCompound;
import org.lilbrocodes.composer_reloaded.api.nbt.ComposerCompound;

/**
 * Interface for item stack data that supports serialization to {@link ComposerCompound}.
 * <p>
 * Extends {@link ItemStackData} and provides a default implementation of {@link #writeNbt(NbtCompound)}
 * that converts the standard {@link NbtCompound} into a {@link ComposerCompound} before writing.
 * </p>
 *
 * @param <T> the concrete type of the implementing class
 */
public interface ComposerStackData<T extends ComposerStackData<T>> extends ItemStackData<T> {

    /**
     * Serializes this instance to a {@link ComposerCompound}.
     * <p>
     * Implementing classes must provide the logic to write their fields into the provided
     * {@link ComposerCompound}.
     * </p>
     *
     * @param tag the {@link ComposerCompound} to write data into
     * @return the same {@link ComposerCompound} containing serialized data
     */
    ComposerCompound writeNbt(ComposerCompound tag);

    /**
     * Default implementation that converts a standard {@link NbtCompound} into a {@link ComposerCompound}
     * and delegates serialization to {@link #writeNbt(ComposerCompound)}.
     *
     * @param tag the {@link NbtCompound} to write data into
     * @return the {@link NbtCompound} containing serialized data
     */
    @Override
    default NbtCompound writeNbt(NbtCompound tag) {
        return writeNbt(ComposerCompound.copy(tag));
    }
}
