package org.lilbrocodes.composer_reloaded.api.v1.tooltips.impl;

import net.minecraft.text.Text;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.DynamicTooltip;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.Section;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.TooltipContext;

import java.util.List;

public class SimpleDynamicTooltip implements DynamicTooltip {
    private final Section tooltip;
    private final Location where;

    public SimpleDynamicTooltip(Section tooltip, Location where) {
        this.tooltip = tooltip;
        this.where = where;
    }

    @Override
    public void appendTooltip(TooltipContext context, List<Text> out) {
        tooltip.append(context, out);
    }

    @Override
    public Location where() {
        return where;
    }
}
