package com.codex.composer.internal.registry;

import net.minecraft.block.entity.BlockEntityType;
import com.codex.composer.api.v1.registry.lazy.DeferredBlockEntityRegistry;
import com.codex.composer.internal.Composer;
import com.codex.composer.internal.block.entity.PlushBlockEntity;

public class ModBlockEntities {
    private static final DeferredBlockEntityRegistry REGISTRY = new DeferredBlockEntityRegistry(Composer.MOD_ID);

    public static final BlockEntityType<PlushBlockEntity> PLUSH = REGISTRY.register(
            "plush",
            PlushBlockEntity::new,
            ModBlocks.PLUSH
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
