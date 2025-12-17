package org.lilbrocodes.composer_reloaded.internal.data.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

import static net.minecraft.advancement.criterion.InventoryChangedCriterion.Conditions.items;
import static net.minecraft.item.Items.*;
import static org.lilbrocodes.composer_reloaded.internal.registry.ModItems.PLUSHIE;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, PLUSHIE)
                .pattern("WBW")
                .pattern("NBP")
                .pattern("P N")
                .input('B', BLACK_WOOL)
                .input('P', PURPLE_WOOL)
                .input('W', WHITE_WOOL)
                .input('N', BROWN_WOOL)
                .criterion("has_wool", items(BLACK_WOOL, PURPLE_WOOL, WHITE_WOOL, BROWN_WOOL))
                .offerTo(exporter);

        CookingRecipeJsonBuilder.createCampfireCooking(
                Ingredient.ofItems(PLUSHIE),
                RecipeCategory.MISC,
                AIR,
                0f,
                10
        ).criterion("has_plush", items(PLUSHIE)).offerTo(exporter, "cook_plush");
    }
}
