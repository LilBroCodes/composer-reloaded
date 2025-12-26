package org.lilbrocodes.composer_reloaded.api.toast;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Vector2i;
import org.lilbrocodes.composer_reloaded.api.render.ColorProgram;
import org.lilbrocodes.composer_reloaded.api.render.ToastDrawUtils;

import java.util.List;

//? if minecraft: >= 1.21.4
//import net.minecraft.client.render.RenderLayer;

public class SimpleToast extends AbstractToast {
    private final Identifier iconTexture;
    private final String message;
    private final int backgroundColor;
    private final int borderColor;
    private final ColorProgram backgroundColorProgram;
    private final ColorProgram borderColorProgram;

    private static final int HEIGHT = 40;
    private static final int MAX_WIDTH = 200;
    private static final int ICON_SIZE = 30;
    private static final long DURATION = 3000;
    private static final long FADE_TIME = 500;
    private static final int MARGIN = 50;

    public SimpleToast(Identifier iconTexture, String message, int backgroundColor, int borderColor) {
        super();
        this.iconTexture = iconTexture;
        this.message = message;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.backgroundColorProgram = null;
        this.borderColorProgram = null;
    }

    public SimpleToast(Identifier iconTexture, String message, ColorProgram backgroundColorProgram, ColorProgram borderColorProgram) {
        super();
        this.iconTexture = iconTexture;
        this.message = message;
        this.backgroundColorProgram = backgroundColorProgram;
        this.borderColorProgram = borderColorProgram;
        this.backgroundColor = -1;
        this.borderColor = -1;
    }

    @Override
    protected void draw(DrawContext context, long timeAlive, int x, int y) {
        float scaleX = getHorizontalScaleFactor(timeAlive);

        context.getMatrices().push();
        context.getMatrices().translate(x, y, 0);
        context.getMatrices().scale(scaleX, 1.0f, 1.0f);

        drawBox(context);
        drawText(context);
        drawIcon(context);

        context.getMatrices().pop();

        if (timeAlive >= DURATION) {
            remove();
        }
    }

    @Override
    protected Vector2i size() {
        return new Vector2i(MAX_WIDTH, HEIGHT);
    }

    @Override
    protected int margin() {
        return MARGIN;
    }

    private void drawIcon(DrawContext ctx) {
        int x = -MAX_WIDTH / 2 - 5;
        int y = -ICON_SIZE / 2;

        ctx.drawTexture(/*? minecraft: >=1.21.4 { *//*RenderLayer::getGuiTextured, *//*?}*/ iconTexture, x, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
    }

    private void drawBox(DrawContext ctx) {
        ToastDrawUtils.drawCenteredBox(ctx, 0, 0, MAX_WIDTH, HEIGHT, backgroundColorProgram == null ? backgroundColor : backgroundColorProgram.pick());
        ToastDrawUtils.drawCenteredOutline(ctx, 0, 0, MAX_WIDTH, HEIGHT, borderColorProgram == null ? borderColor : borderColorProgram.pick(), 1);
    }

    private void drawText(DrawContext ctx) {
        int wrapWidth = Math.max(10, MAX_WIDTH - 20);
        List<OrderedText> lines = textRenderer.wrapLines(Text.literal(message), wrapWidth);
        int wrappedHeight = textRenderer.getWrappedLinesHeight(Text.literal(message), wrapWidth);
        ToastDrawUtils.drawCenteredLines(ctx, textRenderer, lines, 6, 0, wrappedHeight, 0xFFFFFFFF);
    }

    private float getHorizontalScaleFactor(long timeAlive) {
        if (timeAlive < FADE_TIME) {
            float t = timeAlive / (float) FADE_TIME;
            return 1.0f - (float) Math.pow(1.0f - t, 3);
        } else if (timeAlive > DURATION - FADE_TIME) {
            float t = (timeAlive - (DURATION - FADE_TIME)) / (float) FADE_TIME;
            return 1.0f - (1.0f - (float) Math.pow(1.0f - t, 3));
        } else {
            return 1.0f;
        }
    }
}