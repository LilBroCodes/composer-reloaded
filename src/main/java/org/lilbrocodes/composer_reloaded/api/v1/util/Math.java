package org.lilbrocodes.composer_reloaded.api.v1.util;

import org.apache.commons.lang3.function.TriFunction;

public class Math {
    public static double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    public static Vec2 lerpVec2(Vec2 start, Vec2 end, double t) {
        return lerpVec2(Math::lerp, start, end, t);
    }

    public static Vec2 lerpVec2(TriFunction<Double, Double, Double, Double> lerp, Vec2 start, Vec2 end, double t) {
        return new Vec2(
                lerp.apply(start.x, end.x, t),
                lerp.apply(start.y, end.y, t)
        );
    }

    public static int lerpColor(int startColor, int endColor, double t) {
        return lerpColor((start, end, t1) -> (int) lerp(start, end, t1), startColor, endColor, t);
    }

    public static int lerpColor(TriFunction<Integer, Integer, Double, Integer> lerp, int startColor, int endColor, double t) {
        int a1 = (startColor >> 24) & 0xFF;
        int r1 = (startColor >> 16) & 0xFF;
        int g1 = (startColor >> 8) & 0xFF;
        int b1 = startColor & 0xFF;

        int a2 = (endColor >> 24) & 0xFF;
        int r2 = (endColor >> 16) & 0xFF;
        int g2 = (endColor >> 8) & 0xFF;
        int b2 = endColor & 0xFF;

        int a = lerp.apply(a1, a2, t);
        int r = lerp.apply(r1, r2, t);
        int g = lerp.apply(g1, g2, t);
        int b = lerp.apply(b1, b2, t);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static Integer iLerp(Integer start, Integer end, Double t) {
        return java.lang.Math.toIntExact(java.lang.Math.round(lerp(start, end, t)));
    }
}
