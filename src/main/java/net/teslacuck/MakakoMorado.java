package net.teslacuck;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.teslacuck.block.ModBlocks;
import net.teslacuck.item.ModItemGroups;
import net.teslacuck.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakakoMorado implements ModInitializer {
	public static final String MOD_ID = "makakomorado";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ModBlocks.registerModedBlocks();

        FuelRegistry.INSTANCE.add(ModItems.PELO_DE_MONO, 60);


    }
}