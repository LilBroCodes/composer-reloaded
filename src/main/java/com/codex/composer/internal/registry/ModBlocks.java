package com.codex.composer.internal.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import com.codex.composer.api.v1.registry.lazy.DeferredBlockRegistry;
import com.codex.composer.internal.Composer;
import com.codex.composer.internal.block.PlushBlock;

public class ModBlocks {
    private static final DeferredBlockRegistry BLOCKS = new DeferredBlockRegistry(Composer.MOD_ID);

    public static final PlushBlock PLUSH = BLOCKS.register(
            "plush",
            PlushBlock::new,
            AbstractBlock.Settings.copy(Blocks.BLACK_WOOL).nonOpaque()
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
