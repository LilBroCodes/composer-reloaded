package org.lilbrocodes.composer_reloaded.internal.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.lilbrocodes.composer_reloaded.internal.data.assets.ModLanguageProvider;
import org.lilbrocodes.composer_reloaded.internal.data.data.ModBlockLootTableProvider;
import org.lilbrocodes.composer_reloaded.internal.data.data.ModRecipeProvider;

public class ComposerReloadedDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModLanguageProvider::new);

        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}
