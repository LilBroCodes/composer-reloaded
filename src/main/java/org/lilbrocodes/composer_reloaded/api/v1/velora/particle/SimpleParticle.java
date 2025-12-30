package org.lilbrocodes.composer_reloaded.api.v1.velora.particle;

import net.minecraft.client.gui.DrawContext;
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
        this.creationTime = System.currentTimeMillis();
    }

    @Override
    public void render(DrawContext ctx, Vec2 origin) {
        if (pos == null) pos = origin.copy();

        double elapsed = (System.currentTimeMillis() - creationTime) / 1000.0;
        double t = Math.min(1.0, elapsed / lifetimeSeconds);

        double size = lerp(startSize, endSize, t);
        double rotation = lerp(startRotation, endRotation, t);
        int color = lerpColor(startColor, endColor, t);
        Vec2 velocity = lerpVec2(startVelocity, endVelocity, t);
        pos.add(velocity.x * elapsed, velocity.y * elapsed);

        ParticleRenderer.renderShape(shape, pos, size, rotation, color);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class Builder {
        private ParticleShape shape;
        private double startSize = 1.0, endSize = 1.0;
        private int startColor = 0xFFFFFFFF, endColor = 0xFFFFFFFF;
        private double startRotation = 0, endRotation = 0;
        private Vec2 startVelocity = null, endVelocity = null;
        private double lifetimeSeconds = 1.0;

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

        public SimpleParticle build() {
            BuilderFields.verify(this);
            return new SimpleParticle(this);
        }
    }

    public enum ParticleShape {
        QUAD, TRIANGLE, CIRCLE
    }
}
