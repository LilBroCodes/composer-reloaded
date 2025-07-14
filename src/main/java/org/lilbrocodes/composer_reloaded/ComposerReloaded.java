package org.lilbrocodes.composer_reloaded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.registry.ComposerRegistries;
import org.lilbrocodes.composer_reloaded.api.util.AdvancementManager;
import org.lilbrocodes.composer_reloaded.components.entry.ModEntityComponents;
import org.lilbrocodes.composer_reloaded.networking.TargetBlockPayload;
import org.lilbrocodes.composer_reloaded.networking.TargetEntityPayload;

import java.util.UUID;

public class ComposerReloaded implements ModInitializer {
    public static final String MOD_ID = "composer_reloaded";
    public static final Identifier TARGET_ENTITY = identify("target_entity_c2s");
    public static final Identifier TARGET_BLOCK = identify("target_block_c2s");

    @Override
    public void onInitialize() {
        ComposerRegistries.initialize();
        TargetEntityPayload.registerHandler();
        TargetBlockPayload.registerHandler();

        ServerTickEvents.END_WORLD_TICK.register(world -> {
            for (ServerPlayerEntity player : world.getPlayers()) {
                ComposerRegistries.COMPOSER_ADVANCEMENTS.streamEntries().forEach(advancementReference -> {
                    if (advancementReference.hasKeyAndValue() && advancementReference.value().advancementPredicate().test(player)) {
                        AdvancementManager.grantAdvancement(player, advancementReference.value().advancementIdentifier());
                    }
                });

                UUID id = ModEntityComponents.TARGETED_ENTITY.get(player).getUuid();
                if (id != null && world.getEntity(id) != null) {
                    if (!(world.getEntity(id) instanceof LivingEntity living)) continue;
                    living.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 10, 254));
                }
            }
        });
    }

    public static Identifier identify(String name) {
        return new Identifier(MOD_ID, name);
    }
}
