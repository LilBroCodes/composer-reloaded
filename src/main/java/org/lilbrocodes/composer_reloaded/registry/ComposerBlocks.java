package org.lilbrocodes.composer_reloaded.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.block.PlushBlock;

import java.util.ArrayList;
import java.util.List;

public class ComposerBlocks {
    private static final List<BlockItem> registeredBlocks = new ArrayList<>();

    public static final BlockWithItem<PlushBlock> PLUSH = register(
            new PlushBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL).nonOpaque()),
            "plush",
            true
    );

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ComposerItemGroups.COMPOSER_ITEM_GROUP_KEY).register(entries -> {
            registeredBlocks.forEach(entries::add);
        });
    }

    public static <T extends Block> BlockWithItem<T> register(
            T block, String name, boolean shouldRegisterItem) {

        Identifier id = ComposerReloaded.identify(name);
        BlockItem item = null;

        if (shouldRegisterItem) {
            item = Registry.register(Registries.ITEM, id, new BlockItem(block, new FabricItemSettings()));
            registeredBlocks.add(item);
        }

        return new BlockWithItem<>(Registry.register(Registries.BLOCK, id, block), item);
    }

    public static class BlockWithItem<T extends Block> {
        public final T block;
        public BlockItem item;

        public BlockWithItem(T block, BlockItem item) {
            this.block = block;
            this.item = item;
        }
    }
}
