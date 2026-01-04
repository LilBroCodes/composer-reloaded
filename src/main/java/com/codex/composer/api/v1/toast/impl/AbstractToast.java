package com.codex.composer.api.v1.toast.impl;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.joml.Vector2i;

public abstract class AbstractToast {
    private long creationTime = -1;
    private boolean removed = false;
    public TextRenderer textRenderer;

    public void init(TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }

    public final void draw(DrawContext context, int x, int y) {
        if (creationTime == -1) {
            creationTime = System.currentTimeMillis();
        }

        long timeAlive = System.currentTimeMillis() - creationTime;
        draw(context, timeAlive, x, y);
    }

    public void remove() {
        removed = true;
    }

    public abstract void draw(DrawContext context, long timeAlive, int x, int y);
    public abstract Vector2i size();
    public abstract int margin();
    public abstract Identifier getId();

    public boolean shouldRemove() {
        return removed;
    }
}
