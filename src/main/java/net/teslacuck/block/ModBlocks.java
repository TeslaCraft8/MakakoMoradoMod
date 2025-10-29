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

    public static final Block BLOCK_OF_MONKEY_FUR =registerBlock("bloque_de_pelo_de_mono",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LOG).sounds(BlockSoundGroup.WOOL)));

    private static Block registerBlock (String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MakakoMorado.MOD_ID, name), block);
    }


    private static Item registerBlockItem (String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(MakakoMorado.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));

    }



    public static void registerModedBlocks(){
        MakakoMorado.LOGGER.info("registrando bloques para" + MakakoMorado.MOD_ID);




}
}
