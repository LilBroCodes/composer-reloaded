package com.codex.composer.mixin.accessor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ItemMethodAccessor {
    boolean composer$soulbound();
    boolean composer$soulboundCanDrop();

    static ItemMethodAccessor get(Item item) {
        if (item instanceof ItemMethodAccessor acc) return acc;
        return null;
    }

    static ItemMethodAccessor get(ItemStack stack) {
        return get(stack.getItem());
    }
}
