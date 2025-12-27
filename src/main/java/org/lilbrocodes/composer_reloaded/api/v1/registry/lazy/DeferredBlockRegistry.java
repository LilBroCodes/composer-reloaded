package org.lilbrocodes.composer_reloaded.api.v1.registry.lazy;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import java.util.function.UnaryOperator;

//? if minecraft: >=1.21.4 {
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

//? }

public class DeferredBlockRegistry extends EmptyDeferredRegistry {
    public DeferredBlockRegistry(String modId) {
        super(modId);
    }

    public <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, Identifier.of(modId, name), block);
    }

    public <T extends Block> T register(String name, BlockProvider<T> newBlock, AbstractBlock.Settings settings) {
        //? minecraft: >=1.21.4 {
        return register(name, newBlock.provide(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(modId, name)))));
        //? } else {
        /*return register(name, newBlock.provide(settings));
        *///? }
    }

    public Block register(String name, AbstractBlock.Settings settings) {
        return register(name, Block::new, settings);
    }

    public Block register(String name, UnaryOperator<AbstractBlock.Settings> settings) {
        return register(name, settings.apply(AbstractBlock.Settings.create()));
    }

    @FunctionalInterface
    public interface BlockProvider<T extends Block> {
        T provide(AbstractBlock.Settings settings);
    }
}
