package org.lilbrocodes.composer_reloaded.common.registry;

import net.minecraft.block.entity.BlockEntityType;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredBlockEntityRegistry;
import org.lilbrocodes.composer_reloaded.api.util.Loadable;
import org.lilbrocodes.composer_reloaded.common.block.entity.PlushBlockEntity;

public class ComposerBlockEntities extends Loadable {
    private static final DeferredBlockEntityRegistry REGISTRY = new DeferredBlockEntityRegistry(ComposerReloaded.MOD_ID);

    public static final BlockEntityType<PlushBlockEntity> PLUSH = REGISTRY.register(
            "plush",
            PlushBlockEntity::new,
            ComposerBlocks.PLUSH.block
    );
}
