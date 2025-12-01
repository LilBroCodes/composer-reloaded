package org.lilbrocodes.composer_reloaded.api.nbt;

import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

public interface NbtProvider {
    @Nullable NbtCompound composerReloaded$getNbt();

    NbtCompound composerReloaded$getOrCreateNbt();

    void composerReloaded$setNbt(NbtCompound tag);
}
