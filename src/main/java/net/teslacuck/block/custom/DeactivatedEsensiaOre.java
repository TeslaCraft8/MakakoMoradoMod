package net.teslacuck.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.teslacuck.MakakoMorado;
import net.teslacuck.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class DeactivatedEsensiaOre extends Block {
    public DeactivatedEsensiaOre(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getMainHandStack();
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;
        if (player.getMainHandStack().getItem() == Items.ENDER_PEARL) {
            itemStack.decrement(1);
            world.playSound(player, pos, SoundEvents.ENTITY_ENDER_EYE_DEATH, SoundCategory.BLOCKS);

            if (world.isClient) {
                for (int i = 0; i < 30; i++) {
                    double dx = (world.random.nextDouble() - 0.5) * 1.5;
                    double dy = (world.random.nextDouble() - 0.5) * 1.5;
                    double dz = (world.random.nextDouble() - 0.5) * 1.5;
                    world.addParticle(
                            ParticleTypes.PORTAL,
                            x, y, z,
                            dx, dy, dz
                    );
                    world.addParticle(
                            ParticleTypes.CLOUD,
                            x, y, z,
                            dx, dy, dz
                    );
                }
            }
            randomizeBlockPos(world, pos, player);
            return ActionResult.SUCCESS;
        } else {
            world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM.value(), SoundCategory.BLOCKS);
            return ActionResult.FAIL;
        }


    }

    private void randomizeBlockPos(World world, BlockPos pos, PlayerEntity player) {

        List<Block> plantasDeLosCojones = new ArrayList<Block>();
        //herbas
        plantasDeLosCojones.add(Blocks.GRASS);
        plantasDeLosCojones.add(Blocks.TALL_GRASS);
        plantasDeLosCojones.add(Blocks.FERN);
        plantasDeLosCojones.add(Blocks.LARGE_FERN);
        //flores
        plantasDeLosCojones.add(Blocks.DANDELION);
        plantasDeLosCojones.add(Blocks.POPPY);
        plantasDeLosCojones.add(Blocks.BLUE_ORCHID);
        plantasDeLosCojones.add(Blocks.ALLIUM);
        plantasDeLosCojones.add(Blocks.AZURE_BLUET);
        plantasDeLosCojones.add(Blocks.RED_TULIP);
        plantasDeLosCojones.add(Blocks.ORANGE_TULIP);
        plantasDeLosCojones.add(Blocks.WHITE_TULIP);
        plantasDeLosCojones.add(Blocks.PINK_TULIP);
        plantasDeLosCojones.add(Blocks.OXEYE_DAISY);
        plantasDeLosCojones.add(Blocks.CORNFLOWER);
        plantasDeLosCojones.add(Blocks.LILY_OF_THE_VALLEY);
        plantasDeLosCojones.add(Blocks.WITHER_ROSE);
        //hongos
        plantasDeLosCojones.add(Blocks.BROWN_MUSHROOM);
        plantasDeLosCojones.add(Blocks.RED_MUSHROOM);
        //otros
        plantasDeLosCojones.add(Blocks.SUGAR_CANE);
        plantasDeLosCojones.add(Blocks.BAMBOO);
        plantasDeLosCojones.add(Blocks.KELP);
        plantasDeLosCojones.add(Blocks.SEAGRASS);
        plantasDeLosCojones.add(Blocks.TALL_SEAGRASS);
        plantasDeLosCojones.add(Blocks.LILY_PAD);
        plantasDeLosCojones.add(Blocks.SWEET_BERRY_BUSH);

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        int randomX = (int) x + world.random.nextInt(5) + 1;
        int newY = (int) y;
        int randomZ = (int) z + world.random.nextInt(5) + 1;
        BlockPos randomBlockPos = new BlockPos(randomX, newY, randomZ);
        Block epicBlock = world.getBlockState(randomBlockPos).getBlock();
        while (world.getBlockState(randomBlockPos).getBlock() != Blocks.AIR || plantasDeLosCojones.contains(epicBlock)) {
            if (plantasDeLosCojones.contains(epicBlock)){
                world.removeBlock(randomBlockPos, false);
                newY--;
            }
            randomBlockPos = new BlockPos(randomX, newY, randomZ);
            epicBlock = world.getBlockState(randomBlockPos).getBlock();
            newY++;
        }
        for (int i = 0; i < 30; i++) {
            //no me acuerdo de por que lo hize random, pero si no lo hago no tira ns
            double dx = (world.random.nextDouble() - 0.5) * 1.5;
            double dy = (world.random.nextDouble() - 0.5) * 1.5;
            double dz = (world.random.nextDouble() - 0.5) * 1.5;
            world.addParticle(ParticleTypes.PORTAL, randomBlockPos.getX(), randomBlockPos.getY(), randomBlockPos.getZ(), -dx, -dy, -dz);
            world.addParticle(ParticleTypes.CLOUD, randomX, newY, randomZ, dx, dy, dz);

            world.playSound(player, pos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 0.3F, 1F);
            world.setBlockState(randomBlockPos, ModBlocks.SAND_ESENSIA_ORE.getDefaultState());
            world.removeBlock(pos, false);

        }
    }
}