package org.lilbrocodes.composer_reloaded.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.cca.entity.TargetedBlockComponent;
import org.lilbrocodes.composer_reloaded.cca.entity.TargetedEntityComponent;

public class ModCardinalComponents implements EntityComponentInitializer {
    public static final ComponentKey<TargetedBlockComponent> TARGETED_BLOCK = ComponentRegistry
            .getOrCreate(ComposerReloaded.identify("targeted_block"), TargetedBlockComponent.class);
    public static final ComponentKey<TargetedEntityComponent> TARGETED_ENTITY = ComponentRegistry
            .getOrCreate(ComposerReloaded.identify("targeted_entity"), TargetedEntityComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(TARGETED_ENTITY, TargetedEntityComponent::new, RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(TARGETED_BLOCK, TargetedBlockComponent::new, RespawnCopyStrategy.NEVER_COPY);
    }
}
