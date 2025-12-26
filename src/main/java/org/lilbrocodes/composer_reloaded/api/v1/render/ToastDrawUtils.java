package org.lilbrocodes.composer_reloaded.api.v1.render;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;

import java.util.List;

public class ToastDrawUtils {
    public static void drawCenteredBox(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x - width / 2, y - height / 2, x + width / 2, y + height / 2, color);
    }

    public static void drawCenteredOutline(DrawContext context, int x, int y, int width, int height, int color, int thickness) {
        int x1 = x - width / 2;
        int y1 = y - height / 2;
        int x2 = x1 + width;
        int y2 = y1 + height;

        context.fill(x1 - thickness, y1 - thickness, x2 + thickness, y1, color);
        context.fill(x1 - thickness, y2, x2 + thickness, y2 + thickness, color);
        context.fill(x1 - thickness, y1, x1, y2, color);
        context.fill(x2, y1, x2 + thickness, y2, color);
    }

    public static void drawCenteredLines(DrawContext context, TextRenderer textRenderer, List<OrderedText> lines, int x, int y, int height, int color) {
        if (lines.isEmpty()) return;
        int lineHeight = height / lines.size();
        int topY = y - height / 2;
        for (int i = 0; i < lines.size(); i++) {
            OrderedText line = lines.get(i);
            context.drawCenteredTextWithShadow(textRenderer, line, x, topY + i * lineHeight, color);
        }
    }

    public static int argb(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static int rgba(int r, int g, int b, int a) {
        return (r << 24) | (g << 16) | (b << 8) | a;
    }
}
