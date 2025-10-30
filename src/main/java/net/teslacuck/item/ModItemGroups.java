package net.teslacuck.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.teslacuck.MakakoMorado;
import net.teslacuck.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(MakakoMorado.MOD_ID, "makakomorado"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.makakomorado"))
                    .icon(() -> new ItemStack(ModItems.CUBO_AUTISTA)).entries((displayContext, entries) -> {
                        entries.add(ModItems.CUBO_AUTISTA);
                        entries.add(ModItems.PELO_DE_MONO);
                        entries.add(ModBlocks.BLOCK_OF_MONKEY_FUR);
                        entries.add(ModItems.ESENSIA);
                        entries.add(ModItems.RAW_ESENSIA);
                        entries.add(ModBlocks.ESENSIA_BLOCK);
                        entries.add(ModBlocks.RAW_ESENSIA_BLOCK);

                    }).build());

    public static void registerItemGroups(){
        MakakoMorado.LOGGER.info("Regsitrando grupos de items" + MakakoMorado.MOD_ID);
    }
}
