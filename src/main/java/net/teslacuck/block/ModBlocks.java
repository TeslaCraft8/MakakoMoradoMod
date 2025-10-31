package net.teslacuck.block;

import net.fabricmc.fabric.api.block.v1.FabricBlockState;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.teslacuck.MakakoMorado;
import net.minecraft.registry.Registry;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;

public class ModBlocks
{
//bloques
    public static final Block BLOCK_OF_MONKEY_FUR =registerBlock("bloque_de_pelo_de_mono",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).sounds(BlockSoundGroup.WOOL)));

    public static final Block ESENSIA_BLOCK =registerBlock("esensia_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.SCULK_CATALYST)));

    public static final Block RAW_ESENSIA_BLOCK =registerBlock("raw_esensia_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    //ores tacticos
    public static final Block ESENSIA_ORE = registerBlock("esensia_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(1.8F, 9.0F), UniformIntProvider.create(2, 14)));
    public static final Block DEEPSLATE_ESENSIA_ORE = registerBlock("deepslate_esensia_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(3F, 9.0F), UniformIntProvider.create(2, 14)));
    public static final Block BLACK_ESENSIA_ORE = registerBlock("black_esensia_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.BLACKSTONE).strength(1.8F, 9.0F), UniformIntProvider.create(2, 14)));
    public static final Block END_ESENSIA_ORE = registerBlock("end_esensia_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).strength(2.2F, 9.0F), UniformIntProvider.create(2, 60)));

    //registrar los bloques
    private static Block registerBlock (String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MakakoMorado.MOD_ID, name), block);
    }

//registrar los items de los bloques por separado
    private static Item registerBlockItem (String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(MakakoMorado.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));

    }



    public static void registerModedBlocks(){
        MakakoMorado.LOGGER.info("registrando bloques para" + MakakoMorado.MOD_ID);




}
}
