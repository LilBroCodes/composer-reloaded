package org.lilbrocodes.composer_reloaded.api.datagen;

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

import java.io.IOException;
import java.nio.file.Path;

public abstract class ComposerLanguageProvider extends FabricLanguageProvider {
    protected TranslationBuilder builder;

    protected ComposerLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        this.builder = translationBuilder;
        generate();
    }

    public abstract void generate();

    // Custom

    public void add(FeatureHandle feature, String translation) {
        if (feature.id() != null) add(feature.id().toTranslationKey("composer_reloaded.feature.description"), translation);
    }

    // Vanilla redirect

    private void add(String key, String value) {
        builder.add(key, value);
    }

    public void add(Item item, String value) {
        builder.add(item, value);
    }

    public void add(Block block, String value) {
        builder.add(block, value);
    }

    public void add(RegistryKey<ItemGroup> registryKey, String value) {
        builder.add(registryKey, value);
    }

    public void add(EntityType<?> entityType, String value) {
        builder.add(entityType, value);
    }

    public void add(Enchantment enchantment, String value) {
        builder.add(enchantment, value);
    }

    public void add(EntityAttribute entityAttribute, String value) {
        builder.add(entityAttribute, value);
    }

    public void add(StatType<?> statType, String value) {
        builder.add(statType, value);
    }

    public void add(StatusEffect statusEffect, String value) {
        builder.add(statusEffect, value);
    }

    public void add(Identifier identifier, String value) {
        builder.add(identifier, value);
    }

    public void add(Path existingLanguageFile) throws IOException {
        builder.add(existingLanguageFile);
    }
}
