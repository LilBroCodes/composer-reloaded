package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DeferredBlockRegistry {
    private final String modId;
    private final RegistryKey<ItemGroup> itemGroupKey;
    private final List<Item> itemsToGroup = new ArrayList<>();

    public DeferredBlockRegistry(String modId, RegistryKey<ItemGroup> itemGroupKey) {
        this.modId = modId;
        this.itemGroupKey = itemGroupKey;
    }

    public <T extends Block> BlockWithItem<T> register(
            String name, T block, boolean registerItem) {

        Identifier id = new Identifier(modId, name);
        BlockItem blockItem = null;

        Registry.register(Registries.BLOCK, id, block);
        if (registerItem) {
            blockItem = Registry.register(Registries.ITEM, id,
                    new BlockItem(block, new FabricItemSettings()));
            itemsToGroup.add(blockItem);
        }

        return new BlockWithItem<>(block, blockItem);
    }

    public <T extends Block> BlockWithItem<T> register(
            String name, T block
    ) {
        return register(name, block, true);
    }

    public void finalizeRegistration() {
        if (itemGroupKey != null) {
            ItemGroupEvents.modifyEntriesEvent(itemGroupKey).register(entries -> {
                itemsToGroup.forEach(entries::add);
            });
        }
    }

    public static class BlockWithItem<T extends Block> {
        public final T block;
        public final BlockItem item;

        public BlockWithItem(T block, BlockItem item) {
            this.block = block;
            this.item = item;
        }
    }
}
