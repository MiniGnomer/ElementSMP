package me.minignomer.elementsmp.truedamage;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class TrueDamage {
    public static void trueDamage(LivingEntity le, double d, Player damager) {
        if (!le.isDead()) {
            if (le.getAbsorptionAmount() > 0) {
                double absorptionHealth = le.getAbsorptionAmount();
                if (absorptionHealth - d <= 0) {
                    le.setAbsorptionAmount(0);
                } else {
                    le.damage(0.1, damager);
                    le.setAbsorptionAmount(absorptionHealth - d);
                    return;
                }
            }
            double health = le.getHealth();
            if (health - d <= 0) {
                le.damage(Integer.MAX_VALUE);
            } else {
                le.damage(0.1, damager);
                le.setHealth(health - d);
            }
        }
    }
}
