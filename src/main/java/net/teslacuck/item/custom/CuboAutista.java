package net.teslacuck.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import net.teslacuck.screens.WeightCalcScreenManager;
import org.jetbrains.annotations.NotNull;

public class CuboAutista extends Item {
    public CuboAutista(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        user.sendMessage(Text.literal("Mikel Putero").formatted(Formatting.RED,Formatting.BOLD), true);
        user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value(), SoundCategory.PLAYERS, 1f, 1f);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
