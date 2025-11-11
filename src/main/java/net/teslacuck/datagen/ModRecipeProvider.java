package net.teslacuck.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.teslacuck.block.ModBlocks;
import net.teslacuck.item.ModItems;
import net.teslacuck.util.ModTags;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public static final List<ItemConvertible> ESENSIA_SMELTEABLE = List.of(ModItems.RAW_ESENSIA,
            ModBlocks.SAND_ESENSIA_ORE,
            ModBlocks.BLACK_ESENSIA_ORE,
            ModBlocks.END_ESENSIA_ORE,
            ModBlocks.ESENSIA_ORE,
            ModBlocks.DEEPSLATE_ESENSIA_ORE
            );

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, ESENSIA_SMELTEABLE, RecipeCategory.MISC,
                ModItems.ESENSIA,
                0.7f, 120, "esensia");
        offerBlasting(exporter, ESENSIA_SMELTEABLE, RecipeCategory.MISC,
                ModItems.ESENSIA,
                0.7f, 80, "esensia");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ESENSIA, RecipeCategory.DECORATIONS,
                ModBlocks.ESENSIA_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_ESENSIA, RecipeCategory.DECORATIONS,
                ModBlocks.RAW_ESENSIA_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CUBO_AUTISTA, 1)
                .pattern("LWL")
                .pattern("W#W")
                .pattern("BBB")
                .input('#', ModBlocks.ESENSIA_BLOCK)
                .input('W', Blocks.LIME_WOOL)
                .input('B', Items.STONE_BUTTON)
                .input('L', Items.LEVER)
                .criterion("has_esensia", conditionsFromItem(ModItems.ESENSIA))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CUBO_AUTISTA)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WEIGHT_CALCULATOR, 1)
                .pattern("#G#")
                .pattern("WBW")
                .pattern("WWW")
                .input('#', ModItems.ESENSIA)
                .input('W', Blocks.BLUE_WOOL)
                .input('B', Items.STONE_BUTTON)
                .input('G', Items.GLASS)
                .criterion("has_esensia", conditionsFromItem(ModItems.ESENSIA))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WEIGHT_CALCULATOR)));
    }
}
