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

    public DeferredBlockRegistry(String modId) {
        this.modId = modId;
    }

    public <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, new Identifier(modId, name), block);
    }
}
