package com.codex.composer.api.v1.registry.lazy;

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
import java.util.function.Function;
import java.util.function.UnaryOperator;

//? if minecraft: >=1.20.6
import net.minecraft.registry.RegistryKeys;

import com.codex.composer.api.v1.item.settings.ComposerItemSettings;
import com.codex.composer.api.v1.util.misc.Provider;

public class DeferredItemRegistry extends EmptyDeferredRegistry {
    private final RegistryKey<ItemGroup> itemGroupKey;
    private final List<Item> registeredItems = new ArrayList<>();

    public DeferredItemRegistry(String modId, RegistryKey<ItemGroup> itemGroupKey) {
        super(modId);
        this.itemGroupKey = itemGroupKey;
    }

    //? if minecraft: >=1.21.3
    @SuppressWarnings("unchecked")
    public <I extends Item, S extends Item.Settings> I register(String name, Function<S, I> provider, Provider<S> settingsSupplier, boolean addToGroup) {
        Identifier id = Identifier.of(modId, name);
        S settings = settingsSupplier.provide();

        //? if minecraft: >=1.21.3 {
        settings = (S) settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, id));
        //? }

        I item = provider.apply(settings);
        I registered = Registry.register(Registries.ITEM, id, item);

        if (addToGroup) registeredItems.add(registered);
        return registered;
    }

    public <I extends Item, S extends Item.Settings> I register(String name, Function<S, I> provider, S settings, boolean addToGroup) {
        return register(name, provider, (Provider<S>) () -> settings, addToGroup);
    }

    public <I extends Item, S extends Item.Settings> I register(String name, Function<S, I> provider, S settings) {
        return register(name, provider, settings, true);
    }

    public <I extends Item, S extends Item.Settings> I register(String name, Function<S, I> provider, Provider<S> settings) {
        return register(name, provider, settings, true);
    }

    public <I extends Item, S extends Item.Settings> I register(String name, Function<S, I> provider, Provider<S> settings, UnaryOperator<S> sBuilder) {
        return register(name, provider, sBuilder.apply(settings.provide()), true);
    }

    public <I extends Item> I register(String name, Function<ComposerItemSettings, I> provider, UnaryOperator<ComposerItemSettings> sBuilder) {
        return register(name, provider, sBuilder.apply(new ComposerItemSettings()), true);
    }

    public Item register(String name) {
        return register(name, true);
    }

    public Item register(String name, boolean addToGroup) {
        return register(name, Item::new, (Provider<Item.Settings>) Item.Settings::new, addToGroup);
    }

    public <S extends Item.Settings> Item register(String name, Provider<S> settings) {
        return register(name, Item::new, settings, true);
    }

    public <S extends Item.Settings> Item register(
            String name,
            Provider<S> settings,
            boolean addToGroup
    ) {
        return register(name, Item::new, settings, addToGroup);
    }

    public <B extends Block, I extends BlockItem, S extends Item.Settings> I register(B block, String name, Function<S, I> provider, Provider<S> settings, boolean addToGroup) {
        return register(name, provider, settings, addToGroup);
    }

    public <B extends Block, I extends BlockItem, S extends Item.Settings> I register(B block, String name, Function<S, I> provider, S settings, boolean addToGroup) {
        return register(block, name, provider, (Provider<S>) () -> settings, addToGroup);
    }

    public <B extends Block, I extends BlockItem, S extends Item.Settings> I register(B block, String name, Function<S, I> provider, S settings) {
        return register(block, name, provider, settings, true);
    }

    public <B extends Block, I extends BlockItem, S extends Item.Settings> I register(B block, String name, Function<S, I> provider, Provider<S> settings) {
        return register(block, name, provider, settings, true);
    }

    public <B extends Block, I extends BlockItem, S extends Item.Settings> I register(B block, String name, Function<S, I> provider, Provider<S> settings, UnaryOperator<S> sBuilder) {
        return register(block, name, provider, sBuilder.apply(settings.provide()), true);
    }

    public <B extends Block, I extends BlockItem> I register(B block, String name, Function<ComposerItemSettings, I> provider, UnaryOperator<ComposerItemSettings> sBuilder) {
        return register(block, name, provider, sBuilder.apply(new ComposerItemSettings()), true);
    }

    public <B extends Block> BlockItem register(B block, String name) {
        return register(block, name, true);
    }

    public <B extends Block> BlockItem register(B block, String name, boolean addToGroup) {
        return register(
                block,
                name,
                s -> new BlockItem(block, s),
                (Provider<Item.Settings>) Item.Settings::new,
                addToGroup
        );
    }

    public <B extends Block, S extends Item.Settings> BlockItem register(B block, String name, Provider<S> settings) {
        return register(block, name, settings, true);
    }

    public <B extends Block, S extends Item.Settings> BlockItem register(B block, String name, Provider<S> settings, boolean addToGroup) {
        return register(
                block,
                name,
                s -> new BlockItem(block, s),
                settings,
                addToGroup
        );
    }

    /* =========================== */

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
}
