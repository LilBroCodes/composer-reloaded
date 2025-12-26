package org.lilbrocodes.composer_reloaded.api.registry.lazy;

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
import java.util.function.UnaryOperator;

//? if minecraft: >=1.21.4
//import net.minecraft.registry.RegistryKeys;

public class DeferredItemRegistry extends EmptyDeferredRegistry {
    private final RegistryKey<ItemGroup> itemGroupKey;
    private final List<Item> registeredItems = new ArrayList<>();

    public DeferredItemRegistry(String modId, RegistryKey<ItemGroup> itemGroupKey) {
        super(modId);
        this.itemGroupKey = itemGroupKey;
    }

    public <T extends Item> T register(String name, ItemProvider<T> provider, Item.Settings settings, boolean addToGroup) {
        Identifier id = Identifier.of(modId, name);

        //? if minecraft: >=1.21.4 {
        /*T item = provider.provide(settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        *///? } else {
        T item = provider.provide(settings);
        //? }

        T registered = Registry.register(Registries.ITEM, id, item);
        if (addToGroup) registeredItems.add(registered);
        return registered;
    }

    public <T extends Item> T register(String name, ItemProvider<T> provider, Item.Settings settings) {
        return register(name, provider, settings, true);
    }

    public Item register(String name) {
        return register(name, Item::new, new Item.Settings(), true);
    }

    public Item register(String name, boolean addToGroup) {
        return register(name, Item::new, new Item.Settings(), addToGroup);
    }

    public Item register(String name, UnaryOperator<Item.Settings> settings) {
        return register(name, Item::new, settings.apply(new Item.Settings()), true);
    }

    public <T extends Block> BlockItem register(T block, String path) {
        return register(block, path, new Item.Settings(), true);
    }

    public <T extends Block> BlockItem register(T block, String path, boolean addToGroup) {
        return register(block, path, new Item.Settings(), addToGroup);
    }

    public <T extends Block> BlockItem register(
            T block,
            String path,
            Item.Settings settings,
            boolean addToGroup
    ) {
        return register(
                path,
                s -> new BlockItem(block, s),
                settings,
                addToGroup
        );
    }

    public void finalizeRegistration() {
        if (itemGroupKey != null) {
            ItemGroupEvents.modifyEntriesEvent(itemGroupKey)
                    .register(entries ->
                            registeredItems.forEach(item ->
                                    entries.add(item.getDefaultStack())
                            )
                    );
        }
    }

    @FunctionalInterface
    public interface ItemProvider<T extends Item> {
        T provide(Item.Settings settings);
    }
}
