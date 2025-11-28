package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

@SuppressWarnings("ClassCanBeRecord")
public class DeferredPotionRegistry {
    private final String modId;

    public DeferredPotionRegistry(String modId) {
        this.modId = modId;
    }

    public <T extends Potion> T register(String name, T potion) {
        return Registry.register(Registries.POTION, new Identifier(modId, name), potion);
    }

    @SuppressWarnings("UnusedReturnValue")
    public Potion register(String name, StatusEffectInstance... effects) {
        return register(name, new Potion(effects));
    }

    public static StatusEffectInstance effect(StatusEffect type, int duration) {
        return new StatusEffectInstance(type, duration);
    }

    public static StatusEffectInstance effect(StatusEffect type, int duration, int amplifier) {
        return new StatusEffectInstance(type, duration, amplifier);
    }

    public Potion registerLong(String baseName, StatusEffectInstance... effects) {
        return register("long_" + baseName, new Potion(baseName, effects));
    }

    public Potion registerStrong(String baseName, StatusEffectInstance... effects) {
        return register("strong_" + baseName, new Potion(baseName, effects));
    }

    public void registerVariants(String baseName,
                                 StatusEffectInstance normal,
                                 StatusEffectInstance longVariant,
                                 StatusEffectInstance strongVariant) {

        register(baseName, normal);
        register("long_" + baseName, new Potion(baseName, longVariant));
        register("strong_" + baseName, new Potion(baseName, strongVariant));
    }

    public RegistryKey<Potion> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.POTION, new Identifier(modId, name));
    }

    public Potion register(String name, RegistryKey<Potion> key, Potion potion) {
        return Registry.register(Registries.POTION, key, potion);
    }

    public Potion registerEmpty(String name) {
        return register(name, new Potion());
    }

    public Potion registerWater(String name) {
        return register(name, new Potion());
    }

    public static Potion of(StatusEffect type, int duration) {
        return new Potion(new StatusEffectInstance(type, duration));
    }

    public static Potion of(StatusEffect type, int duration, int amplifier) {
        return new Potion(new StatusEffectInstance(type, duration, amplifier));
    }

    public Potion multi(String name, StatusEffectInstance... effects) {
        return register(name, new Potion(effects));
    }
}

