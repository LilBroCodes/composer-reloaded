package org.lilbrocodes.composer_reloaded.internal.tooltip;

import net.minecraft.text.Text;
import org.lilbrocodes.composer_reloaded.api.v1.item.settings.component.SoulboundComponent;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.DynamicTooltip;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.Modifier;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.Section;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.TooltipContext;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.LanguageUtils;

import java.util.List;

public class SoulboundTooltip implements DynamicTooltip {
    private final Section rootSection;

    public SoulboundTooltip() {
        Section droppable = Section.create()
                .title("")
                .keyCombination(ctx -> Modifier.ALT)
                .content(ctx -> LanguageUtils.negate("composer_reloaded.tooltips.soulbound.droppable", SoulboundComponent.canDropSoulbound(ctx.stack)))
                .build();

        rootSection = Section.create()
                .title("")
                .details("composer_reloaded.tooltips.soulbound.details")
                .keyCombination(ctx -> SoulboundComponent.isSoulbound(ctx.stack) ? null : Modifier.SHIFT)
                .content(ctx -> LanguageUtils.negate("composer_reloaded.tooltips.soulbound", SoulboundComponent.isSoulbound(ctx.stack)))
                .children().push(droppable).end()
                .build();
    }

    @Override
    public void appendTooltip(TooltipContext context, List<Text> out) {
        rootSection.append(context, out);
    }

    @Override
    public Location where() {
        return Location.AFTER_NAME;
    }
}
