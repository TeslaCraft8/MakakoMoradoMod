package net.teslacuck.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.teslacuck.screens.WeightCalculatorScreen;
import net.teslacuck.screens.WeightCalculatorScreen0;

public class WeightCalculatorItem extends Item {
    public WeightCalculatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        //daña item en la mano
        stack.damage(1, user, (player) -> player.sendToolBreakStatus(hand));


        if (world.isClient) {
            MinecraftClient.getInstance().setScreen(
                    new WeightCalculatorScreen0(Text.literal("Weight Calculator0"))
            );
        }


        return TypedActionResult.success(stack);
    }
}
