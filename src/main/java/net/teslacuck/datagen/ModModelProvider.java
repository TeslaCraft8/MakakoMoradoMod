package net.teslacuck.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.teslacuck.block.ModBlocks;
import net.teslacuck.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_ESENSIA_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ESENSIA_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ESENSIA_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_ESENSIA_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLACK_ESENSIA_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAND_ESENSIA_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_ESENSIA_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEACTIVATED_SAND_ESENSIA_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_MONKEY_FUR);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ESENSIA, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ESENSIA, Models.GENERATED);

        itemModelGenerator.register(ModItems.PELO_DE_MONO, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAT_PELO_DE_MONO, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORE_DETECTOR, Models.GENERATED);
    }
}
