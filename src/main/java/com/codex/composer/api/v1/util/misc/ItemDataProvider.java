package com.codex.composer.api.v1.util.misc;

import net.minecraft.item.ItemStack;

public interface ItemDataProvider<D> {
    ItemStack provide(D data);
}
