package org.lilbrocodes.composer_reloaded.api.util.misc;

import net.minecraft.item.ItemStack;

public interface ItemDataProvider<D> {
    ItemStack provide(D data);
}
