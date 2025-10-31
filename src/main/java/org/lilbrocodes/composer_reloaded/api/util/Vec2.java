package org.lilbrocodes.composer_reloaded.api.util;

import java.lang.Math;

@SuppressWarnings({"UnusedReturnValue", "ClassCanBeRecord"})
public class Vec2 {
    public final double x;
    public final double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(int x, int y) {
        this(x, (double) y);
    }

    public Vec2 add(Vec2 b) {
        return new Vec2(this.x + b.x, this.y + b.y);
    }

    public Vec2 add(double x, double y) {
        return new Vec2(this.x + x, this.y + y);
    }

    public Vec2 sub(Vec2 b) {
        return new Vec2(this.x - b.x, this.y - b.y);
    }

    public Vec2 mul(double scalar) {
        return new Vec2(this.x * scalar, this.y * scalar);
    }

    public double dot(Vec2 b) {
        return this.x * b.x + this.y * b.y;
    }

    public double distanceSquared(Vec2 b) {
        double dx = this.x - b.x;
        double dy = this.y - b.y;
        return dx * dx + dy * dy;
    }

    public double distance(Vec2 b) {
        return Math.sqrt(distanceSquared(b));
    }

    public Vec2 copy() {
        return new Vec2(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Vec2(" + x + ", " + y + ")";
    }
}
