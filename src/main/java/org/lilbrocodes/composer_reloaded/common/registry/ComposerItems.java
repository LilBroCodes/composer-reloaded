package org.lilbrocodes.composer_reloaded.common.registry;

import net.minecraft.item.BlockItem;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredItemRegistry;

public class ComposerItems {
    private static final DeferredItemRegistry REGISTRY = new DeferredItemRegistry(ComposerReloaded.MOD_ID, ComposerItemGroups.COMPOSER);

    public static final BlockItem PLUSHIE = REGISTRY.register(ComposerBlocks.PLUSH, "plush");

    public static void initialize() {
        REGISTRY.finalizeRegistration();
    }
}
