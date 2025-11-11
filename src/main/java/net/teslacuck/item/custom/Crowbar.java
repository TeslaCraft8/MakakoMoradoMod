package net.teslacuck.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Crowbar extends Item {
    public Crowbar(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stackParaLaCabeza;
        if(!world.isClient) {
            if (hand == Hand.OFF_HAND) {
                stackParaLaCabeza = user.getMainHandStack();
                if (!stackParaLaCabeza.isEmpty()) {
                    ItemStack old = user.getEquippedStack(EquipmentSlot.HEAD);

                    if (!old.isEmpty()) {
                        int before = 0;
                        for (ItemStack s : user.getInventory().main) {
                            if (!s.isEmpty() && ItemStack.areItemsEqual(s, old)) {
                                before += s.getCount();
                            }
                        }

                        ItemStack toInsert = old.copy();
                        user.getInventory().insertStack(toInsert);
                        int after = 0;
                        for (ItemStack s : user.getInventory().main) {
                            if (!s.isEmpty() && ItemStack.areItemsEqual(s, old)) {
                                after += s.getCount();
                            }
                        }

                        boolean pudoMeterla = after > before;
                        if (!pudoMeterla) {

                            ItemEntity entity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), old.copy());
                            world.spawnEntity(entity);
                        }
                    }

                    user.equipStack(EquipmentSlot.HEAD, new ItemStack(stackParaLaCabeza.getItem()));
                    stackParaLaCabeza.decrement(1);


                    Vec3d userLook = user.getRotationVec(1.0f);
                    ((ServerWorld) world).spawnParticles(
                            new ItemStackParticleEffect(ParticleTypes.ITEM, user.getEquippedStack(EquipmentSlot.HEAD)),
                            user.getX(), user.getEyeY(), user.getZ(),
                            40,
                            userLook.x * 0.3, userLook.y * 0.3, userLook.z * 0.3,
                            0.05
                    );


                    world.playSound(null,
                            user.getX(), user.getY(), user.getZ(),
                            SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.7f, 1f);

                    world.playSound(null,
                            user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1f, 1f);

                    user.swingHand(Hand.MAIN_HAND, true);

                }
            }
        }
        return super.use(world, user, hand);
    }
}
