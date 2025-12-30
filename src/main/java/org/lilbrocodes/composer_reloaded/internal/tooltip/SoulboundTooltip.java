package org.lilbrocodes.composer_reloaded.internal.tooltip;

import org.lilbrocodes.composer_reloaded.api.v1.item.settings.component.SoulboundComponent;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.*;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.impl.SimpleDynamicTooltip;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.LanguageUtils;


public class SoulboundTooltip extends SimpleDynamicTooltip {
    @Override
    public Section root() {
        Section droppable = SectionBuilder.create()
                .title("")
                .keyCombo(ctx -> Modifier.ALT)
                .content(ctx -> LanguageUtils.negate("composer_reloaded.tooltips.soulbound.droppable", SoulboundComponent.canDropSoulbound(ctx.stack)))
                .build();

        return SectionBuilder.create()
                .title("")
                .details("composer_reloaded.tooltips.soulbound.details")
                .keyCombo(ctx -> SoulboundComponent.isSoulbound(ctx.stack) ? null : Modifier.SHIFT)
                .content(ctx -> LanguageUtils.negate("composer_reloaded.tooltips.soulbound", SoulboundComponent.isSoulbound(ctx.stack)))
                .children().push(droppable).end()
                .build();
    }

    @Override
    public Location where() {
        return Location.AFTER_NAME;
    }
}
