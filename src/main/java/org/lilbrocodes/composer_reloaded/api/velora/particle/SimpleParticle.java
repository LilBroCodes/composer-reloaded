package org.lilbrocodes.composer_reloaded.api.velora.particle;

import net.minecraft.client.gui.DrawContext;
import org.lilbrocodes.composer_reloaded.api.util.Vec2;
import org.lilbrocodes.composer_reloaded.client.render.ParticleRenderer;

import static org.lilbrocodes.composer_reloaded.api.util.Math.*;

@SuppressWarnings("unused")
public class SimpleParticle extends VeloraParticle {
    public final ParticleShape shape;
    public final double startSize;
    public final double endSize;
    public final int startColor;
    public final int endColor;
    public final double startRotation;
    public final double endRotation;
    public final Vec2 startVelocity;
    public final Vec2 endVelocity;

    protected final long creationTime;
    private Vec2 pos;

    public SimpleParticle(ParticleShape shape, double lifeTimeSeconds, double startSize, double endSize,
                          int startColor, int endColor, double startRotation, double endRotation,
                          Vec2 startVelocity, Vec2 endVelocity) {
        super(lifeTimeSeconds);
        this.shape = shape;
        this.startSize = startSize;
        this.endSize = endSize;
        this.startColor = startColor;
        this.endColor = endColor;
        this.startRotation = startRotation;
        this.endRotation = endRotation;
        this.startVelocity = startVelocity;
        this.endVelocity = endVelocity;

        this.creationTime = System.currentTimeMillis();
    }

    @Override
    public void render(DrawContext ctx, Vec2 origin) {
        if (pos == null) pos = origin.copy();

        double elapsedSeconds = (System.currentTimeMillis() - creationTime) / 1000.0;
        double t = Math.min(1.0, elapsedSeconds / lifetimeSeconds);

        double size = lerp(startSize, endSize, t);
        double rotation = lerp(startRotation, endRotation, t);
        int color = lerpColor(startColor, endColor, t);
        Vec2 velocity = lerpVec2(startVelocity, endVelocity, t);
        pos.add(velocity.x * elapsedSeconds, velocity.y * elapsedSeconds);

        ParticleRenderer.renderShape(shape, pos, size, rotation, color);
    }
}
