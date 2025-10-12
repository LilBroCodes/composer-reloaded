package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class DeferredItemGroupRegistry {
    private final String modId;

    public DeferredItemGroupRegistry(String modId) {
        this.modId = modId;
    }

    public RegistryKey<ItemGroup> registerItemGroup(String name, Supplier<ItemStack> iconSupplier) {
        Identifier id = new Identifier(modId, name);
        RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), id);

        ItemGroup group = FabricItemGroup.builder()
                .icon(iconSupplier)
                .displayName(Text.translatable("itemGroup." + name))
                .build();

        Registry.register(Registries.ITEM_GROUP, key, group);
        return key;
    }
}
