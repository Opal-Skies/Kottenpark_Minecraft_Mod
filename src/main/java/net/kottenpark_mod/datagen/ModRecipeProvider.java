package net.kottenpark_mod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.kottenpark_mod.block.ModBlocks;
import net.kottenpark_mod.item.ModItems;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                List<ItemConvertible> TEMP_SMELTABLES = List.of(ModItems.PIG_HEART);

                offerSmelting(TEMP_SMELTABLES, RecipeCategory.MISC, Items.ACACIA_BOAT, 0.25f, 200, "temp");
                offerBlasting(TEMP_SMELTABLES, RecipeCategory.MISC, Items.ENCHANTED_GOLDEN_APPLE, 0.25f, 100, "temp");

                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PIG_HEART, RecipeCategory.DECORATIONS, ModBlocks.TEMP_BLOCK);

            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
