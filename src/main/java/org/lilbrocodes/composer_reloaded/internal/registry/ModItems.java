package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.item.BlockItem;
import org.lilbrocodes.composer_reloaded.api.v1.item.settings.ComposerItemSettings;
import org.lilbrocodes.composer_reloaded.api.v1.registry.lazy.DeferredItemRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModItems {
    private static final DeferredItemRegistry REGISTRY = new DeferredItemRegistry(ComposerReloaded.MOD_ID, ModItemGroups.COMPOSER);

    public static final BlockItem PLUSHIE = REGISTRY.register(ModBlocks.PLUSH, "plush", () -> new ComposerItemSettings().soulbound(true));

    public static void initialize() {
        REGISTRY.finalizeRegistration();
    }
}
