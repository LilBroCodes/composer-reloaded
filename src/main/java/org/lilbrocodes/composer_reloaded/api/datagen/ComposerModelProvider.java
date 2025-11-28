package org.lilbrocodes.composer_reloaded.api.datagen;

import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Abstract base class for generating block and item models for a Minecraft mod.
 * <p>
 * Extends {@link DataProvider} to integrate with Fabric's data generation system.
 * Provides utilities to generate block states, item models, and automatically write
 * them to the correct resource pack paths.
 * </p>
 * <p>
 * Subclasses should implement {@link #generateBlocks(BlockStateModelGenerator)}
 * and {@link #generateItems(ItemModelGenerator)} to define the mod's models.
 * </p>
 */
public abstract class ComposerModelProvider implements DataProvider {
    private final DataOutput.PathResolver blockstatesPathResolver;
    private final DataOutput.PathResolver modelsPathResolver;

    public ComposerModelProvider(FabricDataOutput output) {
        this.blockstatesPathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "blockstates");
        this.modelsPathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "models");
    }

    /**
     * Generates block state definitions for this provider.
     * <p>
     * Implementations should register blocks and their associated block state suppliers
     * using the provided {@link BlockStateModelGenerator}.
     * </p>
     *
     * @param generator the block state model generator
     */
    public abstract void generateBlocks(BlockStateModelGenerator generator);

    /**
     * Generates item models for this provider.
     * <p>
     * Implementations should register items and their models using the provided
     * {@link ItemModelGenerator}.
     * </p>
     *
     * @param generator the item model generator
     */
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

    private <T> CompletableFuture<?> writeJsons(DataWriter writer, Map<T, ? extends Supplier<com.google.gson.JsonElement>> map, Function<T, Path> pathGetter) {
        return CompletableFuture.allOf(map.entrySet().stream()
                .map(entry -> DataProvider.writeToPath(writer, entry.getValue().get(), pathGetter.apply(entry.getKey())))
                .toArray(CompletableFuture[]::new));
    }
}
