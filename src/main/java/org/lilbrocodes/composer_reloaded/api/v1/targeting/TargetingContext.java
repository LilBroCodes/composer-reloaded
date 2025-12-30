package org.lilbrocodes.composer_reloaded.api.v1.targeting;

import net.minecraft.entity.player.PlayerEntity;
import org.lilbrocodes.constructive.api.v1.anno.Constructive;
import org.lilbrocodes.constructive.api.v1.anno.builder.HardRequire;

@Constructive(builder = true)
public class TargetingContext {
    @HardRequire
    public final PlayerEntity player;
    public final int minDistance;
    public final int maxDistance;
    public final int decayTicks;
    public final boolean targetNonLiving;
    public final boolean targetTamed;
    public final boolean targetDead;

    TargetingContext(PlayerEntity player, int minDistance, int maxDistance, int decayTicks, boolean targetNonLiving, boolean targetTamed, boolean targetDead) {
        this.player = player;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.decayTicks = decayTicks;
        this.targetNonLiving = targetNonLiving;
        this.targetTamed = targetTamed;
        this.targetDead = targetDead;
    }

    public static TargetingContext getDefault(PlayerEntity player) {
        return TargetingContextBuilder.create(player).build();
    }

    public static TargetingContext getIgnoring(PlayerEntity player) {
        return TargetingContextBuilder.create(player).minDistance(0).maxDistance((int) Math.floor(Math.sqrt(Integer.MAX_VALUE))).targetDead(true).targetTamed(true).targetNonLiving(true).decayTicks(Integer.MAX_VALUE).build();
    }
}
