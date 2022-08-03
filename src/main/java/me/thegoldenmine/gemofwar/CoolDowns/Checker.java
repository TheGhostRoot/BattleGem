package me.thegoldenmine.gemofwar.CoolDowns;

import me.thegoldenmine.gemofwar.GemOfWar;
import me.thegoldenmine.gemofwar.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Checker {
    private final GemOfWar plugin;

    public Checker(GemOfWar main) {
        plugin = main;
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player != null) {
                        UUID uuid = player.getUniqueId();
                        if (!player.hasPermission("battlegem.canhave") && player.getInventory().contains(ItemManager.Gem)) {
                            player.getInventory().remove(ItemManager.Gem);
                        }
                        if (plugin.spawnedMobs.get(uuid) != null && plugin.spawnedMobs.get(uuid).isEmpty()) {
                            plugin.spawnedMobs.remove(uuid);
                        }
                        if (plugin.spawnedMobs.get(uuid) == null){
                            plugin.spawnedMobs.remove(uuid);
                        }
                        if (!plugin.spawnedMobs.isEmpty()) {
                            for (List<LivingEntity> entities : plugin.spawnedMobs.values()) {
                               if (!entities.isEmpty()) {
                                   for (LivingEntity entity : entities) {
                                       if (!entity.isDead()) {
                                           if (entity instanceof Spider) {
                                               Spider spider = (Spider) entity;
                                               if (spider.getTarget() != null && spider.getTarget().isDead()) {
                                                   spider.setTarget(null);
                                               }
                                           }
                                           if (entity instanceof Skeleton) {
                                               Skeleton skeleton = (Skeleton) entity;
                                               if (skeleton.getTarget() != null && skeleton.getTarget().isDead()) {
                                                   skeleton.setTarget(null);
                                               }
                                           }
                                           if (entity instanceof Zombie) {
                                               Zombie zombie = (Zombie) entity;
                                               if (zombie.getTarget() != null && zombie.getTarget().isDead()) {
                                                   zombie.setTarget(null);
                                               }
                                           }
                                           if (entity instanceof Wolf) {
                                               Wolf wolf = (Wolf) entity;
                                               if (wolf.getTarget() != null && wolf.getTarget().isDead()) {
                                                   wolf.setTarget(null);
                                               }
                                           }
                                       }
                                   }
                               }
                            }
                        }
                        if (plugin.spawnedMobs.get(uuid) != null && !plugin.spawnedMobs.get(uuid).isEmpty()) {
                            List<LivingEntity> dead = new ArrayList<>();
                            for (LivingEntity entity : plugin.spawnedMobs.get(uuid)) {
                                if (entity != null && entity.getType().equals(EntityType.SKELETON)) {
                                    Skeleton skeleton = (Skeleton) entity;
                                    if (skeleton.getTarget() != null && skeleton.getTarget().equals(player)) {
                                        skeleton.setTarget(null);
                                    }
                                } else if (entity != null && entity.getType().equals(EntityType.ZOMBIE)) {
                                    Zombie zombie = (Zombie) entity;
                                    if (zombie.getTarget() != null && zombie.getTarget().equals(player)) {
                                        zombie.setTarget(null);
                                    }
                                } else if (entity != null && entity.getType().equals(EntityType.SPIDER)) {
                                    Spider spider = (Spider) entity;
                                    if (spider.getTarget() != null && spider.getTarget().equals(player)) {
                                        spider.setTarget(null);
                                    }
                                } else if (entity != null && entity.getType().equals(EntityType.WOLF)) {
                                    Wolf wolf = (Wolf) entity;
                                    if (wolf.getTarget() != null && wolf.isTamed() && wolf.getOwner() != null && wolf.getOwner().equals(player) && wolf.getTarget().equals(player)) {
                                        wolf.setTarget(null);
                                    }
                                }
                                assert entity != null;
                                if (entity.isDead()){
                                    dead.add(entity);
                                }
                            }
                            for (LivingEntity entity : dead) {
                                plugin.spawnedMobs.get(uuid).remove(entity);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 30);
    }
}
