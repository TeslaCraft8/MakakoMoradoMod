package net.teslacuck.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.teslacuck.block.ModBlocks;
import net.teslacuck.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        //menas de esensia
        getOrCreateTagBuilder(ModTags.Blocks.ESENSIA_ORES)
                .add(ModBlocks.DEACTIVATED_SAND_ESENSIA_ORE)
                .add(ModBlocks.SAND_ESENSIA_ORE)
                .add(ModBlocks.BLACK_ESENSIA_ORE)
                .add(ModBlocks.END_ESENSIA_ORE)
                .add(ModBlocks.ESENSIA_ORE)
                .add(ModBlocks.DEEPSLATE_ESENSIA_ORE)
        ;
        //bloques detectables por el dectector
        getOrCreateTagBuilder(ModTags.Blocks.ORE_DETECTOR_VALUABLE_BLOCK)
                .forceAddTag(ModTags.Blocks.ESENSIA_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
        ;
        //minables con el pico
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ESENSIA_BLOCK)
                .add(ModBlocks.RAW_ESENSIA_BLOCK)
                .add(ModBlocks.BLOCK_OF_MONKEY_FUR)
                .forceAddTag(ModTags.Blocks.ESENSIA_ORES)
        ;



        //necesita tier de herramienta
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ESENSIA_BLOCK)
                .add(ModBlocks.RAW_ESENSIA_BLOCK)
                .add(ModBlocks.BLOCK_OF_MONKEY_FUR)
                .forceAddTag(ModTags.Blocks.ESENSIA_ORES)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
        ;

        //minables con pala
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.BLOCK_OF_MONKEY_FUR)
        ;



    }
}
