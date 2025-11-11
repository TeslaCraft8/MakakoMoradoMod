package net.teslacuck.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.teslacuck.block.ModBlocks;
import net.teslacuck.item.ModItems;
import net.teslacuck.util.ModTags;

public class ModLootProvider extends FabricBlockLootTableProvider {
    public ModLootProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.ESENSIA_BLOCK);
        addDrop(ModBlocks.RAW_ESENSIA_BLOCK);
        addDrop(ModBlocks.BLOCK_OF_MONKEY_FUR);

        addDrop(ModBlocks.DEACTIVATED_SAND_ESENSIA_ORE, oreDrops(Blocks.SAND, Items.SAND));
        addDrop(ModBlocks.SAND_ESENSIA_ORE, copperLikeOreDrops(ModBlocks.SAND_ESENSIA_ORE, ModItems.RAW_ESENSIA));
        addDrop(ModBlocks.BLACK_ESENSIA_ORE, copperLikeOreDrops(ModBlocks.BLACK_ESENSIA_ORE, ModItems.RAW_ESENSIA));
        addDrop(ModBlocks.END_ESENSIA_ORE, copperLikeOreDrops(ModBlocks.END_ESENSIA_ORE, ModItems.RAW_ESENSIA));
        addDrop(ModBlocks.DEEPSLATE_ESENSIA_ORE, copperLikeOreDrops(ModBlocks.DEEPSLATE_ESENSIA_ORE, ModItems.RAW_ESENSIA));
        addDrop(ModBlocks.ESENSIA_ORE, copperLikeOreDrops(ModBlocks.ESENSIA_ORE, ModItems.RAW_ESENSIA));

    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return dropsWithSilkTouch(
                drop,
                (LootPoolEntry.Builder<?>)this.applyExplosionDecay(
                        drop,
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                )
        );
    }
}
