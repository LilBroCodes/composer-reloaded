package com.codex.composer.api.v1.datagen;

import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

//? if minecraft: <=1.21.3 {
/*import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import java.util.HashSet;
import java.util.Set;
*///? } else {
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ItemAsset;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import net.minecraft.client.data.*;
//? }

//? if minecraft: <=1.21.3 {
/*public abstract class ComposerModelProvider implements DataProvider {
    private final DataOutput.PathResolver blockstatesPathResolver;
    private final DataOutput.PathResolver modelsPathResolver;

    public ComposerModelProvider(FabricDataOutput output) {
        this.blockstatesPathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "blockstates");
        this.modelsPathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "models");
    }


    public abstract void generateBlocks(BlockStateModelGenerator generator);
    public abstract void generateItems(ItemModelGenerator generator);

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        Map<Block, BlockStateSupplier> blockStates = new HashMap<>();
        Consumer<BlockStateSupplier> blockConsumer = blockStateSupplier -> {
            Block block = blockStateSupplier.getBlock();
            if (blockStates.put(block, blockStateSupplier) != null) {
                throw new IllegalStateException("Duplicate blockstate definition for " + block);
            }
        };

        Map<Identifier, Supplier<JsonElement>> models = new HashMap<>();
        BiConsumer<Identifier, Supplier<JsonElement>> modelConsumer = (id, supplier) -> {
            if (models.put(id, supplier) != null) {
                throw new IllegalStateException("Duplicate model definition for " + id);
            }
        };

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Set<Item> itemModels = new HashSet<>();
        Consumer<Item> itemConsumer = itemModels::add;

        BlockStateModelGenerator blockStateModelGenerator = new BlockStateModelGenerator(blockConsumer, modelConsumer, itemConsumer);
        ItemModelGenerator itemModelGenerator = new ItemModelGenerator(modelConsumer);

        generateBlocks(blockStateModelGenerator);
        generateItems(itemModelGenerator);

        //noinspection deprecation
        return CompletableFuture.allOf(
                writeJsons(writer, blockStates, b -> blockstatesPathResolver.resolveJson(b.getRegistryEntry().registryKey().getValue())),
                writeJsons(writer, models, modelsPathResolver::resolveJson)
        );
    }

    private <T> CompletableFuture<?> writeJsons(DataWriter writer, Map<T, ? extends Supplier<JsonElement>> map, Function<T, Path> pathGetter) {
        return CompletableFuture.allOf(map.entrySet().stream()
                .map(entry -> DataProvider.writeToPath(writer, entry.getValue().get(), pathGetter.apply(entry.getKey())))
                .toArray(CompletableFuture[]::new));
    }
}
*///?} else {
@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public abstract class ComposerModelProvider implements DataProvider {

    private final DataOutput.PathResolver blockstatesPathResolver;
    private final DataOutput.PathResolver itemsPathResolver;
    private final DataOutput.PathResolver modelsPathResolver;

    public ComposerModelProvider(DataOutput output) {
        this.blockstatesPathResolver =
                output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "blockstates");
        this.itemsPathResolver =
                output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "items");
        this.modelsPathResolver =
                output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "models");
    }

    public abstract void generateBlocks(BlockStateModelGenerator generator);
    public abstract void generateItems(ItemModelGenerator generator);

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        BlockStateSuppliers blockStateSuppliers = new BlockStateSuppliers();
        ItemAssets itemAssets = new ItemAssets();
        ModelSuppliers modelSuppliers = new ModelSuppliers();

        BlockStateModelGenerator blockGenerator = new BlockStateModelGenerator(blockStateSuppliers, itemAssets, modelSuppliers);
        ItemModelGenerator itemGenerator = new ItemModelGenerator(itemAssets, modelSuppliers);

        generateBlocks(blockGenerator);
        generateItems(itemGenerator);

        blockStateSuppliers.validate();
        itemAssets.resolveAndValidate();

        return CompletableFuture.allOf(
                blockStateSuppliers.writeAllToPath(writer, blockstatesPathResolver),
                modelSuppliers.writeAllToPath(writer, modelsPathResolver),
                itemAssets.writeAllToPath(writer, itemsPathResolver)
        );
    }


    @Environment(EnvType.CLIENT)
    public static class ModelSuppliers implements BiConsumer<Identifier, ModelSupplier> {
        private final Map<Identifier, ModelSupplier> modelSuppliers = new HashMap<>();

        ModelSuppliers() {
        }

        public void accept(Identifier identifier, ModelSupplier modelSupplier) {
            Supplier<JsonElement> supplier = this.modelSuppliers.put(identifier, modelSupplier);
            if (supplier != null) {
                throw new IllegalStateException("Duplicate model definition for " + identifier);
            }
        }

        public CompletableFuture<?> writeAllToPath(DataWriter writer, DataOutput.PathResolver pathResolver) {
            Objects.requireNonNull(pathResolver);
            return ComposerModelProvider.writeAllToPath(writer, pathResolver::resolveJson, this.modelSuppliers);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class BlockStateSuppliers implements Consumer<BlockStateSupplier> {
        private final Map<Block, BlockStateSupplier> blockStateSuppliers = new HashMap<>();

        BlockStateSuppliers() {
        }

        public void accept(BlockStateSupplier blockStateSupplier) {
            Block block = blockStateSupplier.getBlock();
            BlockStateSupplier blockStateSupplier2 = this.blockStateSuppliers.put(block, blockStateSupplier);
            if (blockStateSupplier2 != null) {
                throw new IllegalStateException("Duplicate blockstate definition for " + block);
            }
        }

        public void validate() {
            Stream<RegistryEntry.Reference<Block>> stream = Registries.BLOCK.streamEntries();
            List<Identifier> list = stream.filter((entry) -> !this.blockStateSuppliers.containsKey(entry.value())).map((e) -> e.registryKey().getValue()).toList();
            if (!list.isEmpty()) {
                throw new IllegalStateException("Missing blockstate definitions for: " + list);
            }
        }

        public CompletableFuture<?> writeAllToPath(DataWriter writer, DataOutput.PathResolver pathResolver) {
            return ComposerModelProvider.writeAllToPath(writer, (block) -> pathResolver.resolveJson(block.getRegistryEntry().registryKey().getValue()), this.blockStateSuppliers);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class ItemAssets implements ItemModelOutput {
        private final Map<Item, ItemAsset> itemAssets = new HashMap<>();
        private final Map<Item, Item> aliasedAssets = new HashMap<>();

        ItemAssets() {
        }

        public void accept(Item item, ItemModel.Unbaked model) {
            this.accept(item, new ItemAsset(model, ItemAsset.Properties.DEFAULT));
        }

        private void accept(Item item, ItemAsset asset) {
            ItemAsset itemAsset = this.itemAssets.put(item, asset);
            if (itemAsset != null) {
                throw new IllegalStateException("Duplicate item model definition for " + item);
            }
        }

        public void acceptAlias(Item base, Item alias) {
            this.aliasedAssets.put(alias, base);
        }

        public void resolveAndValidate() {
            Registries.ITEM.forEach((item) -> {
                if (!this.aliasedAssets.containsKey(item)) {
                    if (item instanceof BlockItem blockItem) {
                        if (!this.itemAssets.containsKey(blockItem)) {
                            Identifier identifier = ModelIds.getBlockModelId(blockItem.getBlock());
                            this.accept(blockItem, ItemModels.basic(identifier));
                        }
                    }

                }
            });
            this.aliasedAssets.forEach((base, alias) -> {
                ItemAsset itemAsset = this.itemAssets.get(alias);
                if (itemAsset == null) {
                    String itemString = String.valueOf(alias);
                    throw new IllegalStateException("Missing donor: " + itemString + " -> " + base);
                } else {
                    this.accept(base, itemAsset);
                }
            });
            List<Identifier> list = Registries.ITEM.streamEntries().filter((entry) -> !this.itemAssets.containsKey(entry.value())).map((entryx) -> entryx.registryKey().getValue()).toList();
            if (!list.isEmpty()) {
                throw new IllegalStateException("Missing item model definitions for: " + list);
            }
        }

        public CompletableFuture<?> writeAllToPath(DataWriter writer, DataOutput.PathResolver pathResolver) {
            return DataProvider.writeAllToPath(writer, ItemAsset.CODEC, (item) -> pathResolver.resolveJson(item.getRegistryEntry().registryKey().getValue()), this.itemAssets);
        }
    }

    protected static <T> CompletableFuture<?> writeAllToPath(DataWriter writer, Function<T, Path> pathResolver, Map<T, ? extends Supplier<JsonElement>> idsToValues) {
        return DataProvider.writeAllToPath(writer, Supplier::get, pathResolver, idsToValues);
    }
}
//?}
