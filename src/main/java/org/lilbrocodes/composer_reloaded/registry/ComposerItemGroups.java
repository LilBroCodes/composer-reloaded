package org.lilbrocodes.composer_reloaded.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;

public class ComposerItemGroups {
    public static final RegistryKey<ItemGroup> COMPOSER_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), ComposerReloaded.identify("composer"));

    public static final ItemGroup COMPOSER_ITEMS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ComposerBlocks.PLUSH.item))
            .displayName(Text.translatable("composer.name"))
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, COMPOSER_ITEM_GROUP_KEY, COMPOSER_ITEMS_GROUP);
    }
}
