package org.lilbrocodes.composer_reloaded.api.advancement;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

public record ComposerAdvancement(Identifier advancementIdentifier, Predicate<PlayerEntity> advancementPredicate) {

}
