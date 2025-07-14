package org.lilbrocodes.composer_reloaded.api.targeting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lilbrocodes.composer_reloaded.components.entry.ModEntityComponents;
import org.lilbrocodes.composer_reloaded.mixin.accessor.WorldMethodAccessor;

import java.util.UUID;

public class Targeting {
    public static BlockPos getTargetedBlock(PlayerEntity player, TargetingContext options) {
        if (ModEntityComponents.TARGETED_BLOCK.get(player).getTicks() > options.decayTicks) return null;
        BlockPos pos = ModEntityComponents.TARGETED_BLOCK.get(player).getPos();

        if (pos == null) return null;
        if (!pos.isWithinDistance(player.getBlockPos(), options.maxDistance)) return null;
        if (pos.getSquaredDistance(player.getBlockPos()) < MathHelper.square(options.minDistance)) return null;

        return pos;
    }

    public static Entity getTargetedEntity(PlayerEntity player, TargetingContext options) {
        if (ModEntityComponents.TARGETED_ENTITY.get(player).getTicks() > options.decayTicks) return null;

        UUID uuid = ModEntityComponents.TARGETED_ENTITY.get(player).getUuid();
        if (uuid == null) return null;

        if (!(player.getWorld() instanceof WorldMethodAccessor worldMethodAccessor)) return null;
        Entity entity = worldMethodAccessor.composerReloaded$getEntityByUuid(uuid);
        if (entity == null) return null;

        double sqDist = entity.squaredDistanceTo(player);
        if (sqDist > options.maxDistance * options.maxDistance) return null;
        if (sqDist < options.minDistance * options.minDistance) return null;

        return entity;
    }
}
