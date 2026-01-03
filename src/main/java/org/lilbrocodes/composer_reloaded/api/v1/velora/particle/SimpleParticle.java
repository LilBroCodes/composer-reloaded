package org.lilbrocodes.composer_reloaded.api.v1.velora.particle;

import net.minecraft.client.gui.DrawContext;
import org.apache.commons.lang3.function.TriFunction;
import org.lilbrocodes.composer_reloaded.api.v1.render.ParticleRenderer;
import org.lilbrocodes.composer_reloaded.api.v1.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.v1.util.builder.BuilderFields;

import static org.lilbrocodes.composer_reloaded.api.v1.util.Math.*;

public class SimpleParticle extends VeloraParticle {
    public final ParticleShape shape;
    public final double startSize, endSize;
    public final int startColor, endColor;
    public final double startRotation, endRotation;
    public final Vec2 startVelocity, endVelocity;
    public final TriFunction<Double, Double, Double, Double> sizeInterpolationFunction;
    public final TriFunction<Integer, Integer, Double, Integer> colorInterpolationFunction;
    public final TriFunction<Double, Double, Double, Double> rotationInterpolationFunction;
    public final TriFunction<Double, Double, Double, Double> velocityInterpolationFunction;

    private Vec2 pos;
    protected final long creationTime;

    protected SimpleParticle(Builder builder) {
        super(builder.lifetimeSeconds);
        this.shape = builder.shape;
        this.startSize = builder.startSize;
        this.endSize = builder.endSize;
        this.startColor = builder.startColor;
        this.endColor = builder.endColor;
        this.startRotation = builder.startRotation;
        this.endRotation = builder.endRotation;
        this.startVelocity = builder.startVelocity;
        this.endVelocity = builder.endVelocity;
        this.sizeInterpolationFunction = builder.sizeInterpolationFunction;
        this.colorInterpolationFunction = builder.colorInterpolationFunction;
        this.rotationInterpolationFunction = builder.rotationInterpolationFunction;
        this.velocityInterpolationFunction = builder.velocityInterpolationFunction;
        this.creationTime = System.currentTimeMillis();
    }

    @Override
    public void render(DrawContext ctx, Vec2 origin) {
        if (pos == null) pos = origin.copy();

        double elapsed = (System.currentTimeMillis() - creationTime) / 1000.0;
        double t = Math.min(1.0, elapsed / lifetimeSeconds);

        double size = sizeInterpolationFunction.apply(startSize, endSize, t);
        double rotation = rotationInterpolationFunction.apply(startRotation, endRotation, t);
        int color = lerpColor(colorInterpolationFunction, startColor, endColor, t);
        Vec2 velocity = lerpVec2(velocityInterpolationFunction, startVelocity, endVelocity, t);
        pos = pos.add(velocity.x * elapsed, velocity.y * elapsed);

        ParticleRenderer.renderShape(shape, pos, size, rotation, color);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class Builder implements Cloneable {
        private ParticleShape shape;
        private double startSize = 1.0, endSize = 1.0;
        private int startColor = 0xFFFFFFFF, endColor = 0xFFFFFFFF;
        private double startRotation = 0, endRotation = 0;
        private Vec2 startVelocity = null, endVelocity = null;
        private double lifetimeSeconds = 1.0;
        private TriFunction<Double, Double, Double, Double> sizeInterpolationFunction = org.lilbrocodes.composer_reloaded.api.v1.util.Math::lerp;
        private TriFunction<Integer, Integer, Double, Integer> colorInterpolationFunction = org.lilbrocodes.composer_reloaded.api.v1.util.Math::iLerp;
        private TriFunction<Double, Double, Double, Double> rotationInterpolationFunction = org.lilbrocodes.composer_reloaded.api.v1.util.Math::lerp;
        private TriFunction<Double, Double, Double, Double> velocityInterpolationFunction = org.lilbrocodes.composer_reloaded.api.v1.util.Math::lerp;

        public Builder shape(ParticleShape shape) {
            this.shape = shape;
            return this;
        }

        public Builder startSize(double startSize) {
            this.startSize = startSize;
            return this;
        }

        public Builder endSize(double endSize) {
            this.endSize = endSize;
            return this;
        }

        public Builder startColor(int startColor) {
            this.startColor = startColor;
            return this;
        }

        public Builder endColor(int endColor) {
            this.endColor = endColor;
            return this;
        }

        public Builder startRotation(double startRotation) {
            this.startRotation = startRotation;
            return this;
        }

        public Builder endRotation(double endRotation) {
            this.endRotation = endRotation;
            return this;
        }

        public Builder startVelocity(Vec2 startVelocity) {
            this.startVelocity = startVelocity;
            return this;
        }

        public Builder endVelocity(Vec2 endVelocity) {
            this.endVelocity = endVelocity;
            return this;
        }

        public Builder lifetime(double seconds) {
            this.lifetimeSeconds = seconds;
            return this;
        }

        public Builder sizeFunction(TriFunction<Double, Double, Double, Double> function) {
            this.sizeInterpolationFunction = function;
            return this;
        }

        public Builder colorFunction(TriFunction<Integer, Integer, Double, Integer> function) {
            this.colorInterpolationFunction = function;
            return this;
        }

        public Builder rotationFunction(TriFunction<Double, Double, Double, Double> function) {
            this.rotationInterpolationFunction = function;
            return this;
        }

        public Builder velocityFunction(TriFunction<Double, Double, Double, Double> function) {
            this.velocityInterpolationFunction = function;
            return this;
        }

        public SimpleParticle build() {
            BuilderFields.verify(this);
            return new SimpleParticle(this);
        }

        @Override
        public Builder clone() {
            try {
                Builder clone = (Builder) super.clone();
                return clone.shape(shape).startSize(startSize).endSize(endSize).startColor(startColor).endColor(endColor).startRotation(startRotation).endRotation(endRotation).startVelocity(startVelocity).endVelocity(endVelocity).lifetime(lifetimeSeconds);
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    public enum ParticleShape {
        QUAD, TRIANGLE, CIRCLE
    }
}
