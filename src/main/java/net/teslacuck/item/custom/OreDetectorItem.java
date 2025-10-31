package net.teslacuck.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

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
                     break;
                }
            }

            if(!foundBlock){
                player.sendMessage(Text.literal("Nada"),true);
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
        List<Block> valuables = new ArrayList<Block>();
        valuables.add(Blocks.IRON_ORE);
        valuables.add(Blocks.DEEPSLATE_IRON_ORE);
        valuables.add(Blocks.GOLD_ORE);
        valuables.add(Blocks.DEEPSLATE_GOLD_ORE);
        valuables.add(Blocks.DIAMOND_ORE);
        valuables.add(Blocks.DEEPSLATE_DIAMOND_ORE);
        if (valuables.contains(state.getBlock())){
            return true;
        }else {return false;}


    }
}

