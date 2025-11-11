package net.teslacuck.item.custom;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.teslacuck.packets.ModPackets;
import net.teslacuck.screens.WeightCalcScreenManager;

public class WeightCalculatorItem extends Item {
    public WeightCalculatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        // daña item
        stack.damage(1, user, (player) -> player.sendToolBreakStatus(hand));

        if (!world.isClient) {
            if (user instanceof ServerPlayerEntity serverPlayer) {
                ServerPlayNetworking.send(serverPlayer, ModPackets.OPEN_WEIGHT_CALC_SCREEN, PacketByteBufs.empty());
            }
        }
        return TypedActionResult.success(stack);
    }
}
