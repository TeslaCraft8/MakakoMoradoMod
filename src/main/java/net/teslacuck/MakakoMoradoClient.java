package net.teslacuck;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.teslacuck.block.ModBlockEntities;
import net.teslacuck.block.entity.OverlayBlockEntityRenderer;
import net.teslacuck.packets.ModPackets;
import net.teslacuck.screens.WeightCalcScreenManager;

public class MakakoMoradoClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //packet weight calc
        ClientPlayNetworking.registerGlobalReceiver(ModPackets.OPEN_WEIGHT_CALC_SCREEN, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                MinecraftClient.getInstance().setScreen(
                        new WeightCalcScreenManager(Text.literal("weight_calc_screen"))
                );
            });
        });
        BlockEntityRendererRegistry.register(ModBlockEntities.OVERLAY_BLOCK_ENTITY, OverlayBlockEntityRenderer::new);
    }
}
