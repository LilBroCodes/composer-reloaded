package org.lilbrocodes.composer_reloaded.api.util;

import net.minecraft.client.MinecraftClient;

public class Scaling {
    /**
     * Gets the current Minecraft GUI scale (pixel ratio).
     * <p>
     * Minecraft renders GUI and tessellation at a scaled resolution.
     * For example: a 1920x1080 screen at GUI scale 2 renders at 960x540.
     */
    public static double getScale() {
        MinecraftClient client = MinecraftClient.getInstance();
        return (double) client.getWindow().getFramebufferWidth() / client.getWindow().getScaledWidth();
    }

    public static double toScaled(double pixels) {
        return pixels / getScale();
    }

    public static double toPixels(double scaled) {
        return scaled * getScale();
    }

    public static int toScaled(int pixels) {
        return (int) (pixels / getScale());
    }

    public static int toPixels(int scaled) {
        return (int) (scaled * getScale());
    }

    public static Vec2 toScaled(Vec2 pixels) {
        double scale = getScale();
        return new Vec2(pixels.x / scale, pixels.y / scale);
    }

    public static Vec2 toPixels(Vec2 scaled) {
        double scale = getScale();
        return new Vec2(scaled.x * scale, scaled.y * scale);
    }
}

