package me.thegoldenmine.gemofwar.CoolDowns;

import me.thegoldenmine.gemofwar.GemOfWar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class PigHeal {
    private final GemOfWar plugin;

    public PigHeal(GemOfWar main) {
        plugin = main;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!plugin.spawnedMobs.isEmpty()) {
                    for (List<LivingEntity> entities : plugin.spawnedMobs.values()) {
                        for (LivingEntity entity : entities) {
                            if (entity instanceof Pig && !entity.isDead()) {
                                Pig pig = (Pig) entity;
                                for (Entity entityNear : pig.getNearbyEntities(10, 10, 10)) {
                                    if (entityNear instanceof LivingEntity) {
                                        LivingEntity living = (LivingEntity) entityNear;
                                        double attributeInstanceW = living.getMaxHealth();
                                        living.setHealth(Math.min(living.getHealth() + 6, living.getMaxHealth()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 120);
    }
}
