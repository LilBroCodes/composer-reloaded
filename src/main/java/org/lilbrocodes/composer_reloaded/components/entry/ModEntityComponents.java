package org.lilbrocodes.composer_reloaded.components.entry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.components.TargetedBlockTracker;
import org.lilbrocodes.composer_reloaded.components.TargetedEntityTracker;
import org.lilbrocodes.composer_reloaded.components.i.TargetedBlockComponent;
import org.lilbrocodes.composer_reloaded.components.i.TargetedEntityComponent;

public class ModEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<TargetedBlockComponent> TARGETED_BLOCK = ComponentRegistry
            .getOrCreate(new Identifier(ComposerReloaded.MOD_ID, "targeted_block"), TargetedBlockComponent.class);
    public static final ComponentKey<TargetedEntityComponent> TARGETED_ENTITY = ComponentRegistry
            .getOrCreate(new Identifier(ComposerReloaded.MOD_ID, "targeted_entity"), TargetedEntityComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(TARGETED_ENTITY, player -> new TargetedEntityTracker(), RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(TARGETED_BLOCK, player -> new TargetedBlockTracker(), RespawnCopyStrategy.NEVER_COPY);
    }
}
