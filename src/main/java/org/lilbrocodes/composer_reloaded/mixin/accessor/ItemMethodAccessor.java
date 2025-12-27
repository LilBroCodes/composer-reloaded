package org.lilbrocodes.composer_reloaded.mixin.accessor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ItemMethodAccessor {
    boolean composerReloaded$soulbound();
    boolean composerReloaded$soulboundCanDrop();

    static ItemMethodAccessor get(Item item) {
        if (item instanceof ItemMethodAccessor acc) return acc;
        return null;
    }

    static ItemMethodAccessor get(ItemStack stack) {
        return get(stack.getItem());
    }
}
