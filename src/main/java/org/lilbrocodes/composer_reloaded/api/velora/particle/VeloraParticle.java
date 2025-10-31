package org.lilbrocodes.composer_reloaded.api.velora.particle;

import net.minecraft.client.gui.DrawContext;
import org.lilbrocodes.composer_reloaded.api.util.Vec2;

public abstract class VeloraParticle {
    public final double lifetimeSeconds;

    public VeloraParticle(double lifetime) {
        this.lifetimeSeconds = lifetime;
    }

    public abstract void render(DrawContext ctx, Vec2 origin);

    public enum ParticleShape {
        QUAD, TRIANGLE, CIRCLE
    }
}
