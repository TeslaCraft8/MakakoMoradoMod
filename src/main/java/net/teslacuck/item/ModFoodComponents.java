package net.teslacuck.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent MONKEY_FUR = new FoodComponent.Builder().alwaysEdible().snack().hunger(0).saturationModifier(0)
            .statusEffect(new StatusEffectInstance((StatusEffects.HUNGER), 20), 10).build();
}
