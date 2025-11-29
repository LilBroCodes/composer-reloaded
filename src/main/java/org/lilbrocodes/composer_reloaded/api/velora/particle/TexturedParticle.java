package org.lilbrocodes.composer_reloaded.api.velora.particle;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.util.builder.BuilderFields;
import org.lilbrocodes.composer_reloaded.api.velora.VeloraParticleManager;
import org.lilbrocodes.composer_reloaded.client.render.ParticleRenderer;

import static org.lilbrocodes.composer_reloaded.api.util.Math.*;

public class TexturedParticle extends SimpleParticle {
    public final Identifier texture;
    public final int frameCount;
    public final double frameDurationSeconds;

    protected TexturedParticle(Builder builder) {
        super(builder);
        this.texture = builder.texture;
        this.frameCount = builder.frameCount;
        this.frameDurationSeconds = builder.frameDurationSeconds;
    }

    @Override
    public void render(DrawContext ctx, Vec2 origin) {
        double age = (System.currentTimeMillis() - creationTime) / 1000.0;
        double t = Math.min(Math.max(age / lifetimeSeconds, 0.0), 1.0);

        double size = lerp(startSize, endSize, t);
        double rotation = lerp(startRotation, endRotation, t);
        int color = lerpColor(startColor, endColor, t);
        Vec2 velocity = lerpVec2(startVelocity, endVelocity, t);
        Vec2 pos = new Vec2(origin.x + velocity.x * age, origin.y + velocity.y * age);

        if (frameCount > 1) {
            int currentFrame = (int) ((age / frameDurationSeconds) % frameCount);
            ParticleRenderer.renderAnimatedQuad(texture, pos, size, rotation, color, currentFrame, frameCount);
        } else {
            ParticleRenderer.renderTexturedQuad(texture, pos, size, rotation, color);
        }
    }

    public static class Builder extends SimpleParticle.Builder {
        private Identifier texture;
        private int frameCount = 1;
        private double frameDurationSeconds = 1.0;

        public Builder texture(Identifier texture) {
            this.texture = texture;
            return this;
        }

        public Builder frameCount(int frameCount) {
            this.frameCount = frameCount;
            return this;
        }

        public Builder frameDuration(double seconds) {
            this.frameDurationSeconds = seconds;
            return this;
        }

        @Override
        public Builder shape(ParticleShape shape) {
            super.shape(shape);
            return this;
        }

        @Override
        public Builder startSize(double startSize) {
            super.startSize(startSize);
            return this;
        }

        @Override
        public Builder endSize(double endSize) {
            super.endSize(endSize);
            return this;
        }

        @Override
        public Builder startColor(int startColor) {
            super.startColor(startColor);
            return this;
        }

        @Override
        public Builder endColor(int endColor) {
            super.endColor(endColor);
            return this;
        }

        @Override
        public Builder startRotation(double startRotation) {
            super.startRotation(startRotation);
            return this;
        }

        @Override
        public Builder endRotation(double endRotation) {
            super.endRotation(endRotation);
            return this;
        }

        @Override
        public Builder startVelocity(Vec2 startVelocity) {
            super.startVelocity(startVelocity);
            return this;
        }

        @Override
        public Builder endVelocity(Vec2 endVelocity) {
            super.endVelocity(endVelocity);
            return this;
        }

        @Override
        public Builder lifetime(double seconds) {
            super.lifetime(seconds);
            return this;
        }

        @Override
        public TexturedParticle build() {
            BuilderFields.verify(this);
            return new TexturedParticle(this);
        }

        public void spawn(Vec2 pos) {
            VeloraParticleManager.getInstance().spawnParticle(pos, build());
        }
    }
}
