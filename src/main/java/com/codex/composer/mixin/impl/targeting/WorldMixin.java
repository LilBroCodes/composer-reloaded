package com.codex.composer.mixin.impl.targeting;

import net.minecraft.entity.Entity;
import net.minecraft.world.entity.EntityLookup;
import com.codex.composer.mixin.accessor.WorldMethodAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(net.minecraft.world.World.class)
public abstract class WorldMixin implements WorldMethodAccessor {
    @Shadow
    protected abstract EntityLookup<Entity> getEntityLookup();

    @Override
    public Entity composer$getEntityByUuid(UUID uuid) {
        return getEntityLookup().get(uuid);
    }
}
