package net.teslacuck.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.teslacuck.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OreDetectorItem extends Item {
    public OreDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState state = context.getWorld().getBlockState(positionClicked.down(i));

                if (isValuableBlock(state)){
                    outputValuableCoordinates(positionClicked.down(i),player,state.getBlock());
                    foundBlock =true;
                    context.getWorld().playSound(
                            null,
                            player.getBlockPos(),
                            SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value(),
                            SoundCategory.PLAYERS, 1.0F, 1.0F
                    );
                     break;
                }
            }

            if(!foundBlock){
                player.sendMessage(Text.literal("Nada"),true);
                context.getWorld().playSound(
                        null,
                        player.getBlockPos(),
                        SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE.value(),
                        SoundCategory.PLAYERS, 1.0F, 1.0F
                );
            }
        }

        context.getStack().damage(1, context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        String space = ", ";
        player.sendMessage(Text.literal("Encontrado " + block.asItem().getName().getString() + " en las coordenadas: " +
                blockPos.getX() + space + blockPos.getY() + space + blockPos.getZ() ),true);
    }

    private boolean isValuableBlock(BlockState state) {

        return state.isIn(ModTags.Blocks.ORE_DETECTOR_VALUABLE_BLOCK);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.makakomorado.ore_detector.tooltip"));

        super.appendTooltip(stack, world, tooltip, context);
    }
}

