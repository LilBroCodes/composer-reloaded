package org.lilbrocodes.composer_reloaded.api.v1.velora.emitter;

import org.lilbrocodes.composer_reloaded.api.v1.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.v1.util.builder.BuilderFields;
import org.lilbrocodes.composer_reloaded.api.v1.velora.VeloraParticleManager;
import org.lilbrocodes.composer_reloaded.api.v1.velora.particle.VeloraParticle;

import java.util.Random;
import java.util.function.Function;

public class ContinuousParticleEmitter {
    private final int intervalMs;
    private final double spread;
    private final Function<Vec2, VeloraParticle> particleSupplier;

    private long lastSpawnTime;
    private boolean enabled;

    private ContinuousParticleEmitter(Builder builder) {
        this.intervalMs = builder.intervalMs;
        this.spread = builder.spread;
        this.particleSupplier = builder.particleSupplier;
        this.lastSpawnTime = System.currentTimeMillis();
    }

    protected void tick(Vec2 origin) {
        if (enabled) {
            long now = System.currentTimeMillis();
            if (now - lastSpawnTime >= intervalMs) {
                lastSpawnTime = now;
                spawnParticle(origin);
            }
        }
    }

    protected void toggle(boolean bl) {
        enabled = bl;
    }

    private void spawnParticle(Vec2 origin) {
        Vec2 spawnPos = origin.copy();
        if (spread > 0.0f) {
            Random random = new Random();
            double angle = random.nextDouble() * Math.PI * 2;
            double radius = Math.sqrt(random.nextDouble()) * spread;
            float offsetX = (float) (Math.cos(angle) * radius);
            float offsetY = (float) (Math.sin(angle) * radius);
            spawnPos = origin.copy().add(new Vec2(offsetX, offsetY));
        }

        VeloraParticleManager.getInstance().spawnParticle(spawnPos, particleSupplier.apply(spawnPos));
    }

    public static Builder builder(Function<Vec2, VeloraParticle> supplier) {
        return new Builder(supplier);
    }

    public static class Builder {
        private final Function<Vec2, VeloraParticle> particleSupplier;

        private int intervalMs = 100;
        private double spread = 0.0;

        private Builder(Function<Vec2, VeloraParticle> supplier) {
            this.particleSupplier = supplier;
        }

        public Builder intervalMs(int ms) {
            this.intervalMs = ms;
            return this;
        }

        public Builder intervalTicks(int ticks) {
            this.intervalMs = ticks * 50;
            return this;
        }

        public Builder spread(double spread) {
            this.spread = spread;
            return this;
        }

        public ContinuousParticleEmitter build() {
            BuilderFields.verify(this);
            return new ContinuousParticleEmitter(this);
        }
    }
}
