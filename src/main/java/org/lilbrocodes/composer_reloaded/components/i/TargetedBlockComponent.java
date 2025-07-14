package org.lilbrocodes.composer_reloaded.components.i;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public interface TargetedBlockComponent extends Component, AutoSyncedComponent {
    @Nullable BlockPos getPos();
    void setPos(PlayerEntity player, BlockPos pos);
    int getTicks();
    void tick();
}
