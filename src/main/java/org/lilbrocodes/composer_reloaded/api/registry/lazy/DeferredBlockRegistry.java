package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DeferredBlockRegistry extends EmptyDeferredRegistry {
    public DeferredBlockRegistry(String modId) {
        super(modId);
    }

    public <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, new Identifier(modId, name), block);
    }
}
