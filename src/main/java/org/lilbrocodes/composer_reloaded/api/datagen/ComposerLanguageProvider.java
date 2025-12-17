package org.lilbrocodes.composer_reloaded.api.datagen;

import me.fzzyhmstrs.fzzy_config.util.EnumTranslatable;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.stat.StatType;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.feature.FeatureHandle;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.Feature;
import org.lilbrocodes.composer_reloaded.api.util.misc.Translatable;
import org.lilbrocodes.composer_reloaded.api.util.misc.TranslatableSoundEvent;

import java.io.IOException;
import java.nio.file.Path;

public abstract class ComposerLanguageProvider extends FabricLanguageProvider {
    protected TranslationBuilder builder;

    public ComposerLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        this.builder = translationBuilder;
        generate();
    }

    public abstract void generate();
    public String prefix() {
        return "";
    }

    // Custom

    public void feature(FeatureHandle feature, String translation) {
        if (feature.id() != null) add(feature.id().toTranslationKey("composer_reloaded.feature.description"), translation);
    }

    public void feature(Feature feature, String translation) {
        feature(feature.getHandle(), translation);
    }

    public void stat(Identifier identifier, String value) {
        add("stat.%s.%s".formatted(identifier.getNamespace(), identifier.getPath()), value);
    }

    public void group(RegistryKey<ItemGroup> key, String value) {
        add(String.format("itemGroup.%s", key.getValue().getPath()), value);
    }

    public <T extends Enum<T> & EnumTranslatable> void enumTranslatable(Class<T> enumClass, String... values) {
        T[] constants = enumClass.getEnumConstants();
        if (constants.length != values.length) {
            throw new IllegalArgumentException(
                    "Expected " + constants.length + " values, but got " + values.length
            );
        }

        String prefix = constants[0].prefix();
        for (int i = 0; i < constants.length; i++) add("%s.%s".formatted(prefix, constants[i].name()), values[i]);
    }

    public void sound(TranslatableSoundEvent sound, String value) {
        add(sound, value);
    }

    public void add(Translatable translatable, String value) {
        add(translatable.getTranslationKey(), value);
    }

    public void prefix(String key, String value) {
        add(prefix() + "." + key, value);
    }

    // Vanilla redirect

    public void add(String key, String value) {
        builder.add(key, value);
    }

    public void item(Item item, String value) {
        builder.add(item, value);
    }

    public void block(Block block, String value) {
        builder.add(block, value);
    }

    public void registryKey(RegistryKey<ItemGroup> registryKey, String value) {
        builder.add(registryKey, value);
    }

    public void entity(EntityType<?> entityType, String value) {
        builder.add(entityType, value);
    }

    public void enchantment(Enchantment enchantment, String value) {
        builder.add(enchantment, value);
    }

    public void attribute(EntityAttribute entityAttribute, String value) {
        builder.add(entityAttribute, value);
    }

    public void stat(StatType<?> statType, String value) {
        builder.add(statType, value);
    }

    public void effect(StatusEffect statusEffect, String value) {
        builder.add(statusEffect, value);
    }

    public void add(Identifier identifier, String value) {
        builder.add(identifier, value);
    }

    public void append(Path existingLanguageFile) throws IOException {
        builder.add(existingLanguageFile);
    }
}
