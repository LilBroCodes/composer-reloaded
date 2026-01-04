package com.codex.composer.api.v1.tooltips;

import net.minecraft.item.ItemStack;

/**
* Context for generating tooltips based on held keys.
*/
public class TooltipContext {
    public final ItemStack stack;
    public final boolean shiftHeld;
    public final boolean ctrlHeld;
    public final boolean altHeld;

    public TooltipContext(ItemStack stack, boolean shiftHeld, boolean ctrlHeld, boolean altHeld) {
        this.stack = stack;
        this.shiftHeld = shiftHeld;
        this.ctrlHeld = ctrlHeld;
        this.altHeld = altHeld;
    }
}