package org.lilbrocodes.composer_reloaded.util;

import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.util.Vec2;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vec2Test {
    final Vec2 a = new Vec2(-132, 210);
    final Vec2 b = new Vec2(2125, -123);

    @Test
    void distance() {
        assertEquals(2281.433321401263, a.distance(b), "Distance from %s to %s".formatted(a, b));
    }

    @Test
    void squaredDistance() {
        assertEquals(5204938, a.distanceSquared(b), "Squared distance from %s to %s".formatted(a, b));
    }
}
