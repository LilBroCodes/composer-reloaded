package org.lilbrocodes.composer_reloaded.api.v1.util;

public class Math {
    public static double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    public static Vec2 lerpVec2(Vec2 start, Vec2 end, double t) {
        return new Vec2(
                lerp(start.x, end.x, t),
                lerp(start.y, end.y, t)
        );
    }

    public static int lerpColor(int startColor, int endColor, double t) {
        int a1 = (startColor >> 24) & 0xFF;
        int r1 = (startColor >> 16) & 0xFF;
        int g1 = (startColor >> 8) & 0xFF;
        int b1 = startColor & 0xFF;

        int a2 = (endColor >> 24) & 0xFF;
        int r2 = (endColor >> 16) & 0xFF;
        int g2 = (endColor >> 8) & 0xFF;
        int b2 = endColor & 0xFF;

        int a = (int) lerp(a1, a2, t);
        int r = (int) lerp(r1, r2, t);
        int g = (int) lerp(g1, g2, t);
        int b = (int) lerp(b1, b2, t);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
