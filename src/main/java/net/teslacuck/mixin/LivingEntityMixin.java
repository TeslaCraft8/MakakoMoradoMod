package net.teslacuck.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.teslacuck.util.InstakillShield;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V",
                    shift = At.Shift.BEFORE
            )
    )
    private void beforeApplyDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity)(Object)this;
        float vidaAntes = self.getHealth();

        // DEBUG: asegurar que llegamos aquí
        System.out.println("[LivingEntityMixin] beforeApplyDamage: entity=" + self + " healthBefore=" + vidaAntes + " amount=" + amount);

        InstakillShield.damageDetector(self, amount, vidaAntes);
    }

    @Inject(method = "damage", at = @At("RETURN"))
    private void beforeReturnDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity selfAfterDamage = (LivingEntity)(Object)this;
        float healthAfterDamage = selfAfterDamage.getHealth();
        InstakillShield.healthAfter(healthAfterDamage);

        // DEBUG: mostrar salud despues
        System.out.println("[LivingEntityMixin] after damage: entity=" + selfAfterDamage + " healthAfter=" + healthAfterDamage);
    }
}
