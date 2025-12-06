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

public class DeferredItemRegistry extends EmptyDeferredRegistry {
    private final RegistryKey<ItemGroup> itemGroupKey;
    private final List<Item> registeredItems = new ArrayList<>();

    public DeferredItemRegistry(String modId, RegistryKey<ItemGroup> itemGroupKey) {
        super(modId);
        this.itemGroupKey = itemGroupKey;
    }

    public <T extends Item> T register(String name, T item, boolean addToGroup) {
        Identifier id = new Identifier(modId, name);
        T registered = Registry.register(Registries.ITEM, id, item);
        if (addToGroup) registeredItems.add(registered);
        return registered;
    }

    public <T extends Item> T register(String name, T item) {
        return register(name, item, true);
    }

    public Item register(String name) {
        return register(name, new Item(new FabricItemSettings()), true);
    }

    public Item register(String name, boolean addToGroup) {
        return register(name, new Item(new FabricItemSettings()), addToGroup);
    }

    public <T extends Block> BlockItem register(T block, String path, Item.Settings settings) {
        return register(path, new BlockItem(block, settings), true);
    }

    public <T extends Block> BlockItem register(T block, String path, Item.Settings settings, boolean addToGroup) {
        return register(path, new BlockItem(block, settings), addToGroup);
    }

    public <T extends Block> BlockItem register(T block, String path) {
        return register(block, path, new FabricItemSettings(), true);
    }

    public <T extends Block> BlockItem register(T block, String path, boolean addToGroup) {
        return register(block, path, new FabricItemSettings(), addToGroup);
    }

    @Deprecated
    public Item registerSimple(String name) {
        return register(name, new Item(new FabricItemSettings()), true);
    }

    @Deprecated
    public Item registerSimple(String name, boolean addToGroup) {
        return register(name, new Item(new FabricItemSettings()), addToGroup);
    }

    public void finalizeRegistration() {
        if (itemGroupKey != null) {
            ItemGroupEvents.modifyEntriesEvent(itemGroupKey)
                    .register(entries -> registeredItems.forEach(item -> entries.add(item.getDefaultStack())));
        }
    }
}
