package org.lilbrocodes.composer_reloaded.api.v1.registry.lazy;

import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.DynamicTooltip;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.DynamicTooltipRegistry;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.Provider;

public class DeferredDynamicTooltipRegistry extends EmptyDeferredRegistry {
    private final DynamicTooltipRegistry reg = DynamicTooltipRegistry.getInstance();

    public DeferredDynamicTooltipRegistry(String modId) {
        super(modId);
    }

    public <T extends DynamicTooltip> T register(String name, T tooltip) {
        return reg.register(Identifier.of(modId, name), tooltip);
    }

    public <T extends DynamicTooltip> T add(String name, Provider<T> tooltip) {
        return register(name, tooltip.provide());
    }
}
