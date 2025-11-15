package org.lilbrocodes.composer_reloaded.common.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredBlockRegistry;
import org.lilbrocodes.composer_reloaded.common.block.PlushBlock;

public class ComposerBlocks {
    private static final DeferredBlockRegistry BLOCKS = new DeferredBlockRegistry(ComposerReloaded.MOD_ID);

    public static final PlushBlock PLUSH = BLOCKS.register(
            "plush",
            new PlushBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL).nonOpaque())
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
