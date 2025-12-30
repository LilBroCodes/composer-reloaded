package org.lilbrocodes.composer_reloaded.api.v1.tooltips.impl;

import net.minecraft.text.Text;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.DynamicTooltip;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.Section;
import org.lilbrocodes.composer_reloaded.api.v1.tooltips.TooltipContext;

import java.util.List;

public abstract class SimpleDynamicTooltip implements DynamicTooltip {
    private final Section tooltip = root();

    public SimpleDynamicTooltip() {

    }

    @Override
    public void appendTooltip(TooltipContext context, List<Text> out) {
        tooltip.append(context, out);
    }

    public abstract Section root();
}
