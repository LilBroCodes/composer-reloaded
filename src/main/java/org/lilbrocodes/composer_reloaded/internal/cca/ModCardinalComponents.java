package org.lilbrocodes.composer_reloaded.internal.cca;


import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.cca.entity.TargetedBlockComponent;
import org.lilbrocodes.composer_reloaded.internal.cca.entity.TargetedEntityComponent;

//? if minecraft: <=1.20.1 {
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
 //? } else {
/*import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
*///?}

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
