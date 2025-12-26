package org.lilbrocodes.composer_reloaded.util;

import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.v1.util.Math;
import org.lilbrocodes.composer_reloaded.api.v1.util.Vec2;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
    @Test
    void testLerp() {
        assertEquals(5.0, Math.lerp(0, 10, 0.5));
        assertEquals(0, Math.lerp(0, 10, 0));
        assertEquals(10, Math.lerp(0, 10, 1));
    }

    @Test
    void testLerpVec2() {
        Vec2 start = new Vec2(0, 0);
        Vec2 end = new Vec2(10, 20);
        Vec2 result = Math.lerpVec2(start, end, 0.5);

        assertEquals(5, result.x);
        assertEquals(10, result.y);
    }

    @Test
    void testLerpColor() {
        int startColor = 0xFF000000; // opaque black
        int endColor = 0xFFFFFFFF;   // opaque white

        int midColor = Math.lerpColor(startColor, endColor, 0.5);

        assertEquals(0xFF7F7F7F, midColor); // middle gray
    }
}
