package org.lilbrocodes.composer_reloaded.common.targeting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.api.targeting.TargetingContext;

import java.util.function.Predicate;

public class Targeting {
    public static boolean isValidTarget(@Nullable Entity target, TargetingContext options) {
        if (target == null) return false;
        if (!(target instanceof LivingEntity) && !options.targetNonLiving) return false;
        if (options.player == null) return false;
        if (target == options.player) return false;
        if (!options.player.canSee(target)) return false;
        if (target instanceof LivingEntity living && living.isDead() && !options.targetDead) return false;
        if (target.isRemoved()) return false;
        if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(target)) return false;
        if (target instanceof TameableEntity tamed && tamed.isOwner(options.player) && !options.targetTamed) return false;
        return target.canHit();
    }

    /**
     * Method based on the raycast method of Mialeemisc by AmyMialee (<a href="https://github.com/AmyMialeeMods/">AmyMialeeMods</a>)
     */
    public static EntityHitResult raycast(Entity player, Box box, Predicate<Entity> predicate, float hitboxScale) {
        World world = player.getWorld();
        Vec3d start = player.getEyePos();
        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d end = start.add(direction.multiply(box.getXLength()));

        Entity closestEntity = null;
        double closestDistance = Double.MAX_VALUE;
        Vec3d hitEntityPos = null;

        for (Entity entity : world.getOtherEntities(player, box, predicate)) {
            Box scaledBox = entity.getBoundingBox().expand(hitboxScale - 1.0);
            Vec3d hitPos = scaledBox.raycast(start, end).orElse(null);

            if (hitPos != null) {
                double distance = start.squaredDistanceTo(hitPos);
                if (distance < closestDistance) {
                    closestEntity = entity;
                    closestDistance = distance;
                    hitEntityPos = hitPos;
                }
            }
        }

        if (closestEntity != null) return new EntityHitResult(closestEntity, hitEntityPos);
        return null;
    }
}
