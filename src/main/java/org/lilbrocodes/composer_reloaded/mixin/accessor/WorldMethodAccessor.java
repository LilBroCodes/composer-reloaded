package org.lilbrocodes.composer_reloaded.mixin.accessor;

import net.minecraft.entity.Entity;

import java.util.UUID;

public interface WorldMethodAccessor {
    Entity composerReloaded$getEntityByUuid(UUID uuid);
}
