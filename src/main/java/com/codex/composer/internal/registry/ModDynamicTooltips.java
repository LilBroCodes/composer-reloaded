package com.codex.composer.internal.registry;

import com.codex.composer.api.v1.registry.lazy.DeferredDynamicTooltipRegistry;
import com.codex.composer.internal.Composer;
import com.codex.composer.internal.tooltip.SoulboundTooltip;

public class ModDynamicTooltips {
    private static final DeferredDynamicTooltipRegistry REGISTRY = new DeferredDynamicTooltipRegistry(Composer.MOD_ID);

    public static void initialize() {
        REGISTRY.add("soulbound", SoulboundTooltip::new);
    }
}
