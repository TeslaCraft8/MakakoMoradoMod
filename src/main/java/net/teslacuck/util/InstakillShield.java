package net.teslacuck.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstakillShield {
    private static final Map<LivingEntity, List<Float>> damageListMap = new HashMap<>();
    private static final Map<LivingEntity, List<Long>> tickListMap = new HashMap<>();

    public static float healthAfter;

    public static void healthAfter(float healthAfterDamage){
        healthAfter = healthAfterDamage;
    }

    public static void damageDetector(LivingEntity entity, float damageAmount, float healthBefore) {
        // DEBUG: ver si el método se llama
        System.out.println("[InstakillShield] damageDetector called. healthBefore=" + healthBefore + " damage=" + damageAmount + " entity=" + entity);

        // Ajusta la condición para tus pruebas; aquí pongo >= 1 para que se active con cualquier daño
        if (healthBefore >= 1f) {
            registerDamageWithTick(entity, damageAmount);

            List<Float> damageList = damageListMap.get(entity);
            if (damageList != null && !damageList.isEmpty()) {
                float first = damageList.get(0);

                if (entity instanceof ServerPlayerEntity player) {
                    // usa la firma con booleano para asegurar que se envíe correctamente al cliente
                    player.sendMessage(Text.literal("Primer daño registrado: " + first), false);
                } else {
                    System.out.println("[InstakillShield] Entidad no es jugador: " + entity.getType().toString());
                }
            } else {
                System.out.println("[InstakillShield] damageList vacía o nula para " + entity);
            }
        } else {
            // DEBUG para ver por qué no entra
            System.out.println("[InstakillShield] Condición healthBefore no satisfecha: " + healthBefore);
        }
    }

    public static void registerDamageWithTick(LivingEntity entity, float damage) {
        damageListMap.computeIfAbsent(entity, e -> new ArrayList<>()).add(damage);

        long damagedTick = entity.getWorld().getTime();
        tickListMap.computeIfAbsent(entity, e -> new ArrayList<>()).add(damagedTick);

        System.out.println("[InstakillShield] registered damage=" + damage + " tick=" + damagedTick + " for " + entity);
    }
}
