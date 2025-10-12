package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DeferredItemRegistry {
    private final String modId;
    private final RegistryKey<ItemGroup> itemGroupKey;
    private final List<Item> registeredItems = new ArrayList<>();

    public DeferredItemRegistry(String modId, RegistryKey<ItemGroup> itemGroupKey) {
        this.modId = modId;
        this.itemGroupKey = itemGroupKey;
    }

    public <T extends Item> T register(String name, T item) {
        Identifier id = new Identifier(modId, name);
        T registered = Registry.register(Registries.ITEM, id, item);
        registeredItems.add(registered);
        return registered;
    }

    public Item registerSimple(String name) {
        return register(name, new Item(new FabricItemSettings()));
    }

    public void finalizeRegistration() {
        if (itemGroupKey != null) {
            ItemGroupEvents.modifyEntriesEvent(itemGroupKey)
                    .register(entries -> registeredItems.forEach(item -> entries.add(item.getDefaultStack())));
        }
    }
}
