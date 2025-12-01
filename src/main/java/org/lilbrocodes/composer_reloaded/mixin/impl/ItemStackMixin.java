package org.lilbrocodes.composer_reloaded.mixin.impl;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.api.nbt.NbtProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements NbtProvider {
    @Shadow
    public abstract NbtCompound getOrCreateNbt();

    @Shadow
    public abstract @Nullable NbtCompound getNbt();

    @Shadow
    public abstract void setNbt(@Nullable NbtCompound nbt);

    @Override
    public @Nullable NbtCompound composerReloaded$getNbt() {
        return getNbt();
    }

    @Override
    public NbtCompound composerReloaded$getOrCreateNbt() {
        return getOrCreateNbt();
    }

    @Override
    public void composerReloaded$setNbt(NbtCompound tag) {
        setNbt(tag);
    }
}
