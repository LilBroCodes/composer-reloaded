package org.lilbrocodes.composer_reloaded.mixin.impl;

import net.minecraft.entity.Entity;
import net.minecraft.world.entity.EntityLookup;
import org.lilbrocodes.composer_reloaded.mixin.accessor.WorldMethodAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(net.minecraft.world.World.class)
public abstract class WorldMixin implements WorldMethodAccessor {
    @Shadow protected abstract EntityLookup<Entity> getEntityLookup();

    @Override
    public Entity composerReloaded$getEntityByUuid(UUID uuid) {
        return getEntityLookup().get(uuid);
    }
}
