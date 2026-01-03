package org.lilbrocodes.composer_reloaded.api.v1.util;

import java.lang.Math;
import java.util.Random;

@SuppressWarnings({"UnusedReturnValue", "CanBeFinal"})
public class Vec2 {
    public double x;
    public double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(int x, int y) {
        this(x, (double) y);
    }

    public static Vec2 random(double xMin, double xBound, double yMin, double yBound) {
        Random random = new Random();
        return new Vec2(
                random.nextDouble(xMin, xBound),
                random.nextDouble(yMin, yBound)
        );
    }

    public static Vec2 random(double xBound, double yBound) {
        return random(-xBound, xBound, -yBound, yBound);
    }

    public static Vec2 random(double bound) {
        return random(bound, bound);
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

    public int x() {
        return Math.toIntExact(Math.round(x));
    }

    public int y() {
        return Math.toIntExact(Math.round(y));
    }

    @Override
    public String toString() {
        return "Vec2(" + x + ", " + y + ")";
    }
}
