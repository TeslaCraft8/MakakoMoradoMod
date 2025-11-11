package net.teslacuck.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.teslacuck.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PeloDeMono extends Item {
    public PeloDeMono(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient) {
            ItemStack peloMojado = new ItemStack(ModItems.WEAT_PELO_DE_MONO);
            ItemEntity peloMojadoEnItem = new ItemEntity(
                    world,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    peloMojado
            );

            Vec3d userLoock = user.getRotationVec(1.0F);
            double dropSpeed = 0.6;

            peloMojadoEnItem.setVelocity(userLoock.x * dropSpeed, userLoock.y * dropSpeed, userLoock.z * dropSpeed);

            world.playSound(
                    null, //si es null es para los jugadores cercanos, que locura
                    user.getX(),
                    user.getY()+0.4,
                    user.getZ(),
                    SoundEvents.ENTITY_LLAMA_SPIT,
                    user.getSoundCategory(),
                    1.0F,
                    1.0F
            );
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.SPIT,
                        user.getX(),
                        user.getEyeY(),
                        user.getZ(),
                        5,
                        userLoock.x * 1, userLoock.y * 1, userLoock.z * 1,
                        0.05
                );

            peloMojadoEnItem.setPickupDelay(20);
            world.spawnEntity(peloMojadoEnItem);
        }

        return super.finishUsing(stack, world, user);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Text restText = Text.literal(" te comas esto.").formatted(Formatting.WHITE);
        Text noText = Text.literal("NO").formatted(Formatting.RED, Formatting.BOLD).append(restText);

        tooltip.add(noText);
        super.appendTooltip(stack, world, tooltip, context);
    }

}

