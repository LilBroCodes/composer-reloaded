package org.lilbrocodes.composer_reloaded.internal.registry;

import org.lilbrocodes.composer_reloaded.api.v1.registry.lazy.DeferredDynamicTooltipRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.tooltip.SoulboundTooltip;

public class ModDynamicTooltips {
    private static final DeferredDynamicTooltipRegistry REGISTRY = new DeferredDynamicTooltipRegistry(ComposerReloaded.MOD_ID);

    public static void initialize() {
        REGISTRY.add("soulbound", SoulboundTooltip::new);
    }
}
