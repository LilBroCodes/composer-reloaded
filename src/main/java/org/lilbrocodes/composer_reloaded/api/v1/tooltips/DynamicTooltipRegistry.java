package org.lilbrocodes.composer_reloaded.api.v1.tooltips;

import org.lilbrocodes.composer_reloaded.api.v1.util.misc.AbstractPseudoRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class DynamicTooltipRegistry extends AbstractPseudoRegistry.Impl<DynamicTooltip> {
    private static DynamicTooltipRegistry INSTANCE;

    private DynamicTooltipRegistry() {

    }

    @Override
    protected void bootstrap() {
        identify(ComposerReloaded.identify("dynamic_tooltips"), this);
    }

    public static DynamicTooltipRegistry getInstance() {
        if (INSTANCE == null) INSTANCE = new DynamicTooltipRegistry();
        return INSTANCE;
    }
}
