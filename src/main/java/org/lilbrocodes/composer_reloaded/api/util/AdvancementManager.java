package org.lilbrocodes.composer_reloaded.api.util;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class AdvancementManager {
    /**
     * Grants an advancement to the specified player.
     *
     * @param player     The player to whom the advancement should be granted. Must be a server-side player.
     * @param identifier The {@link Identifier} of the advancement to be granted.
     * @throws NullPointerException if the player’s server or advancement loader is unavailable.
     */
    public static <T extends ServerPlayerEntity> void grantAdvancement(T player, Identifier identifier) {
        Advancement advancement = Objects.requireNonNull(player.getServer()).getAdvancementLoader().get(identifier);
        if (advancement != null) {
            AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancement);
            if (!progress.isDone()) {
                progress.getUnobtainedCriteria().forEach(criterion ->
                        player.getAdvancementTracker().grantCriterion(advancement, criterion));
            }
        }
    }
}
