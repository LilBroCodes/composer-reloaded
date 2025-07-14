package org.lilbrocodes.composer_reloaded.components.i;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface TargetedEntityComponent extends Component, AutoSyncedComponent {
    @Nullable UUID getUuid();
    void setUuid(PlayerEntity player, UUID pos);
    int getTicks();
    void tick();
}
