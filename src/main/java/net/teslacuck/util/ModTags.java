package net.teslacuck.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.teslacuck.MakakoMorado;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> ORE_DETECTOR_VALUABLE_BLOCK =
                createTag("ore_detector_valuable_block");
        public static final TagKey<Block> ESENSIA_ORES =
                createTag("esensia_ores");

        //registrar las tags
        public static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(MakakoMorado.MOD_ID, name));
        }
    }

    public static class Items {



        //registrar las tags
        public static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MakakoMorado.MOD_ID, name));
        }
    }
}
