package com.codex.composer.mixin.accessor;

import net.minecraft.entity.Entity;

import java.util.UUID;

public interface WorldMethodAccessor {
    Entity composer$getEntityByUuid(UUID uuid);
}
