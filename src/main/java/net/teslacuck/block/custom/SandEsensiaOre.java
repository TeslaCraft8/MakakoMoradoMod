package net.teslacuck.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class SandEsensiaOre extends Block {

    private final IntProvider experienceDropped;

    public SandEsensiaOre(AbstractBlock.Settings settings) {
        this(settings, ConstantIntProvider.create(0));
    }

    public SandEsensiaOre(AbstractBlock.Settings settings, IntProvider experience) {
        super(settings);
        this.experienceDropped = experience;
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (dropExperience) {
            this.dropExperienceWhenMined(world, pos, tool, this.experienceDropped);
        }
        BlockPos finalPos = stopMakingTpLikeANigga(pos);
    }

    private BlockPos stopMakingTpLikeANigga(BlockPos niggaPos){
        return niggaPos;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.isClient()) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble();

            double dx = (random.nextDouble() - 0.5) * 0.2;
            double dy = (random.nextDouble() - 0.5) * 0.2;
            double dz = (random.nextDouble() - 0.5) * 0.2;

            world.addParticle(
                    new DustParticleEffect(new Vector3f(9F/255F, 247F/255F, 206F/255F), 1.5F),
                    x, y, z,
                    dx, dy, dz
            );
            world.addParticle(
                    ParticleTypes.PORTAL,
                    x, y, z,
                    -dx*2, -dy*2, -dz*2
            );
        }
    }
}
