package eu.magkari.mc.endium.datagen;

import eu.magkari.mc.endium.EndiumEnhanced;
import eu.magkari.mc.endium.block.EndiumBlocks;
import eu.magkari.mc.endium.item.EndiumItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class EndiumEnhancedRecipeProvider extends FabricRecipeProvider {
    public EndiumEnhancedRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                this.nineBlockStorageRecipes(
                        RecipeCategory.MISC,
                        EndiumItems.ENDIUM_SCRAP,
                        RecipeCategory.BUILDING_BLOCKS,
                        EndiumBlocks.ENDIUM_SCRAP_BLOCK
                );
                this.copySmithingTemplate(EndiumItems.ENDIUM_UPGRADE_SMITHING_TEMPLATE, Items.END_STONE);

                this.nineBlockStorageRecipesRecipesWithCustomUnpacking(
                        RecipeCategory.MISC, EndiumItems.ENDIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, EndiumBlocks.ENDIUM_BLOCK, "endium_ingot_from_endium_block", "endium_ingot"
                );
                this.nineBlockStorageRecipesWithCustomPacking(
                        RecipeCategory.MISC, EndiumItems.ENDIUM_NUGGET, RecipeCategory.MISC, EndiumItems.ENDIUM_INGOT, "endium_ingot_from_nuggets", "endium_ingot"
                );
                this.shapeless(RecipeCategory.MISC, EndiumItems.ENDIUM_INGOT)
                        .requires(EndiumItems.ENDIUM_SCRAP, 4)
                        .requires(Items.GOLD_INGOT, 4)
                        .unlockedBy(getHasName(EndiumItems.ENDIUM_SCRAP), has(EndiumItems.ENDIUM_SCRAP));

                this.endiumSmithing(Items.BOW, EndiumItems.ENDIUM_BOW);
                this.endiumSmithing(Items.ELYTRA, EndiumItems.ENDIUM_ELYTRA);
                this.endiumSmithing(Items.FISHING_ROD, EndiumItems.ENDIUM_ROD);
                this.endiumSmithing(Items.SHEARS, EndiumItems.ENDIUM_SHEARS);
                this.endiumSmithing(Items.TURTLE_HELMET, EndiumItems.ENDIUM_TURTLE_HELMET);
            }

            private void endiumSmithing(ItemLike item, Item outputItem) {
                smithing(item, EndiumItems.ENDIUM_UPGRADE_SMITHING_TEMPLATE, EndiumItems.ENDIUM_INGOT, outputItem);
            }
            private void smithing(ItemLike item, ItemLike template, ItemLike ingredient, Item outputItem) {
                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(template),
                                Ingredient.of(ingredient),
                                Ingredient.of(item),
                                RecipeCategory.MISC,
                                outputItem
                        )
                        .unlocks(getHasName(template), has(template))
                        .save(recipeOutput, EndiumEnhanced.MOD_ID + outputItem.asItem().builtInRegistryHolder().key().identifier().getPath());
            }
        };
    }

    @Override
    public String getName() {
        return "EndiumEnhancedRecipeProvider";
    }
}
