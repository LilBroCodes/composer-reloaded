package org.lilbrocodes.composer_reloaded.api.velora.particle;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.util.Vec2;
import org.lilbrocodes.composer_reloaded.client.render.ParticleRenderer;

import static org.lilbrocodes.composer_reloaded.api.util.Math.*;

@SuppressWarnings("unused")
public class TexturedParticle extends SimpleParticle {
    public final Identifier texture;
    public final int frameCount;
    public final double frameDurationSeconds;

    public TexturedParticle(
            Identifier texture,
            double lifeTimeSeconds,
            double startSize,
            double endSize,
            int startColor,
            int endColor,
            double startRotation,
            double endRotation,
            Vec2 startVelocity,
            Vec2 endVelocity,
            int frameCount,
            double frameDurationSeconds
    ) {
        super(null, lifeTimeSeconds, startSize, endSize,
                startColor, endColor, startRotation, endRotation,
                startVelocity, endVelocity);
        this.texture = texture;
        this.frameCount = frameCount;
        this.frameDurationSeconds = frameDurationSeconds;
    }

    public TexturedParticle(
            Identifier texture,
            double lifeTimeSeconds,
            double startSize,
            double endSize,
            int startColor,
            int endColor,
            double startRotation,
            double endRotation,
            Vec2 startVelocity,
            Vec2 endVelocity
    ) {
        this(texture, lifeTimeSeconds, startSize, endSize,
                startColor, endColor, startRotation, endRotation,
                startVelocity, endVelocity, 1, 1);
    }

    @Override
    public void render(DrawContext ctx, Vec2 origin) {
        double age = (System.currentTimeMillis() - creationTime) / 1000.0;
        double t = Math.min(Math.max(age / lifetimeSeconds, 0.0), 1.0);

        double size = lerp(startSize, endSize, t);
        double rotation = lerp(startRotation, endRotation, t);
        int color = lerpColor(startColor, endColor, t);
        Vec2 velocity = lerpVec2(startVelocity, endVelocity, t);
        Vec2 position = new Vec2(origin.x + velocity.x * age, origin.y + velocity.y * age);

        if (frameCount > 1) {
            int currentFrame = (int) ((age / frameDurationSeconds) % frameCount);

            ParticleRenderer.renderAnimatedQuad(
                    texture, position, size, rotation, color, currentFrame, frameCount
            );
        } else {
            ParticleRenderer.renderTexturedQuad(texture, position, size, rotation, color);
        }
    }
}

