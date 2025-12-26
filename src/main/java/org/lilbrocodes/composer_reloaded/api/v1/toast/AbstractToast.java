package org.lilbrocodes.composer_reloaded.api.v1.toast;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.joml.Vector2i;

public abstract class AbstractToast {
    private long creationTime = -1;
    private boolean removed = false;
    public TextRenderer textRenderer;

    protected void init(TextRenderer textRenderer) {
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

    protected abstract void draw(DrawContext context, long timeAlive, int x, int y);

    protected abstract Vector2i size();

    protected abstract int margin();

    public boolean shouldRemove() {
        return removed;
    }
}
