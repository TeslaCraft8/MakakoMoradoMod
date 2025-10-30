package net.teslacuck.block;

import net.fabricmc.fabric.api.block.v1.FabricBlockState;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
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
