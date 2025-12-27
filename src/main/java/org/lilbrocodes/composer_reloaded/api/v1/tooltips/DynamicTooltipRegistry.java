package org.lilbrocodes.composer_reloaded.api.v1.tooltips;

import org.lilbrocodes.composer_reloaded.api.v1.util.misc.AbstractPseudoRegistry;

public class DynamicTooltipRegistry extends AbstractPseudoRegistry.Impl<DynamicTooltip> {
    private static DynamicTooltipRegistry INSTANCE;

    private DynamicTooltipRegistry() {

    }

    public static DynamicTooltipRegistry getInstance() {
        if (INSTANCE == null) INSTANCE = new DynamicTooltipRegistry();
        return INSTANCE;
    }
}
