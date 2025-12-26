package org.lilbrocodes.composer_reloaded.api.v1.targeting;

import net.minecraft.entity.player.PlayerEntity;

public class TargetingContext {
    public final PlayerEntity player;
    public final int minDistance;
    public final int maxDistance;
    public final int decayTicks;
    public final boolean targetNonLiving;
    public final boolean targetTamed;
    public final boolean targetDead;

    private TargetingContext(Builder builder) {
        this.player = builder.player;
        this.minDistance = builder.minDistance;
        this.maxDistance = builder.maxDistance;
        this.decayTicks = builder.decayTicks;
        this.targetNonLiving = builder.targetNonLiving;
        this.targetTamed = builder.targetTamed;
        this.targetDead = builder.targetDead;
    }

    public static Builder builder(PlayerEntity player) {
        return new Builder(player);
    }

    public static TargetingContext getDefault(PlayerEntity player) {
        return builder(player).build();
    }

    public static TargetingContext getIgnoring(PlayerEntity player) {
        return builder(player).minDistance(0).maxDistance((int) Math.floor(Math.sqrt(Integer.MAX_VALUE))).targetDead(true).targetTamed(true).targetNonLiving(true).decayTicks(Integer.MAX_VALUE).build();
    }

    public static class Builder {
        private final PlayerEntity player;
        private int minDistance = 0;
        private int maxDistance = 100;
        private int decayTicks = 20;
        private boolean targetNonLiving = false;
        private boolean targetTamed = true;
        private boolean targetDead = false;

        public Builder minDistance(int minDistance) {
            this.minDistance = minDistance;
            return this;
        }

        public Builder maxDistance(int maxDistance) {
            this.maxDistance = maxDistance;
            return this;
        }

        public Builder decayTicks(int decayTicks) {
            this.decayTicks = decayTicks;
            return this;
        }

        public Builder targetNonLiving(boolean targetNonLiving) {
            this.targetNonLiving = targetNonLiving;
            return this;
        }

        public Builder targetTamed(boolean targetTamed) {
            this.targetTamed = targetTamed;
            return this;
        }

        public Builder targetDead(boolean targetDead) {
            this.targetDead = targetDead;
            return this;
        }

        private Builder(PlayerEntity player) {
            this.player = player;
        }

        public TargetingContext build() {
            return new TargetingContext(this);
        }
    }
}
