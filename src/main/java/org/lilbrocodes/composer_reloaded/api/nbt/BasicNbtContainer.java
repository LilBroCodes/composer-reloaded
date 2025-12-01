package org.lilbrocodes.composer_reloaded.api.nbt;

import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

public class BasicNbtContainer implements NbtProvider {
    private NbtCompound tag;

    @Override
    public @Nullable NbtCompound composerReloaded$getNbt() {
        return tag;
    }

    @Override
    public NbtCompound composerReloaded$getOrCreateNbt() {
        if (tag == null) tag = new NbtCompound();
        return tag;
    }

    @Override
    public void composerReloaded$setNbt(NbtCompound tag) {
        this.tag = tag;
    }
}
