package net.teslacuck.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.teslacuck.MakakoMorado;
import net.teslacuck.MakakoMoradoClient;
import net.teslacuck.item.custom.OreDetectorItem;
import net.teslacuck.item.custom.WeightCalculatorItem;

import javax.swing.*;

public class ModItems {

    public static final Item CUBO_AUTISTA = registerItem("cubo_autista", new Item(new FabricItemSettings()));
    public static final Item PELO_DE_MONO = registerItem("pelo_de_mono", new Item(new FabricItemSettings()));
    public static final Item ESENSIA = registerItem("esensia", new Item(new FabricItemSettings()));
    public static final Item RAW_ESENSIA = registerItem("raw_esensia", new Item(new FabricItemSettings()));

    public static final Item ORE_DETECTOR = registerItem("ore_detector", new OreDetectorItem(new FabricItemSettings().
            maxDamage(64)));
    public static final Item WEIGHT_CALCULATOR = registerItem("weight_calculator", new WeightCalculatorItem(new FabricItemSettings().
            maxDamage(64)));

    private static void addIngidientsItemGroup(FabricItemGroupEntries entries){
        entries.add(CUBO_AUTISTA);
        entries.add(PELO_DE_MONO);
    }


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MakakoMorado.MOD_ID, name), item);
    }

    public static void registerModItems(){
        MakakoMorado.LOGGER.info("Resgistrame este | (item) |" + MakakoMorado.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addIngidientsItemGroup);
    }
}
