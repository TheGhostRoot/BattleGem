package me.thegoldenmine.gemofwar.Listeners;

import me.thegoldenmine.gemofwar.GemOfWar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.List;
import java.util.UUID;

public class Attacks implements Listener {
    private final GemOfWar plugin;

    public Attacks(GemOfWar main) {
        plugin = main;
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        Entity target = event.getTarget();
        Entity attacker = event.getEntity();
        if (target instanceof Player && attacker instanceof LivingEntity) {
            Player player = (Player) target;
            LivingEntity living = (LivingEntity) attacker;
            UUID uuid = player.getUniqueId();
            if (!plugin.spawnedMobs.isEmpty() && plugin.spawnedMobs.get(uuid) != null && !plugin.spawnedMobs.get(uuid).isEmpty() && plugin.spawnedMobs.get(uuid).contains(living)) {
                event.setTarget(null);
                event.setCancelled(true);
            }
        }
        if (target instanceof LivingEntity && attacker instanceof LivingEntity) {
            LivingEntity tar = (LivingEntity) target;
            LivingEntity att = (LivingEntity) attacker;
            if (!plugin.spawnedMobs.isEmpty()) {
                for (List<LivingEntity> entities : plugin.spawnedMobs.values()) {
                    if (entities.contains(tar) && entities.contains(att)) {
                        event.setTarget(null);
                        event.setCancelled(true);
                    }
                }
            }
        }
        if (target instanceof Skeleton && attacker instanceof Wolf) {
            Wolf att = (Wolf) attacker;
            Skeleton tar = (Skeleton) target;
            if (!plugin.spawnedMobs.isEmpty()) {
                for (List<LivingEntity> entities : plugin.spawnedMobs.values()) {
                    if (entities.contains(att) && entities.contains(tar)) {
                        event.setTarget(null);
                        event.setCancelled(true);
                    }
                }
            }
        }
        if (attacker instanceof Wolf) {
            Wolf wolf = (Wolf) attacker;
            if (!plugin.spawnedMobs.isEmpty()) {
                for (List<LivingEntity> entities : plugin.spawnedMobs.values()) {
                    for (Entity entity : entities) {
                        if (entity.equals(wolf) && target instanceof LivingEntity) {
                            LivingEntity living = (LivingEntity) target;
                            wolf.setVelocity(living.getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(3));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity deffender = event.getEntity();
        if (attacker instanceof Player) {
            Player playerAttacker = (Player) attacker;
            UUID uuid = playerAttacker.getUniqueId();
            if (deffender instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) deffender;
                if (!plugin.spawnedMobs.isEmpty()) {
                    if (plugin.spawnedMobs.get(uuid) != null && !plugin.spawnedMobs.get(uuid).isEmpty() && plugin.spawnedMobs.get(uuid).contains(living)) {
                        event.setCancelled(true);
                    } else if (plugin.spawnedMobs.get(uuid) != null && !plugin.spawnedMobs.get(uuid).isEmpty()) {
                        for (LivingEntity entity : plugin.spawnedMobs.get(uuid)) {
                            if (entity.getType().equals(EntityType.SKELETON)) {
                                Skeleton skeleton = (Skeleton) entity;
                                skeleton.setTarget(living);
                            } else if (entity.getType().equals(EntityType.SPIDER)) {
                                Spider spider = (Spider) entity;
                                spider.setTarget(living);
                            } else if (entity.getType().equals(EntityType.ZOMBIE)) {
                                Zombie zombie = (Zombie) entity;
                                zombie.setTarget(living);
                            } else if (entity.getType().equals(EntityType.WOLF)) {
                                Wolf wolf = (Wolf) entity;
                                wolf.setTarget(living);
                                wolf.setVelocity(living.getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(3));
                            }
                        }
                    }
                }
            }
        }
        if (deffender instanceof Player) {
            Player playerDeffender = (Player) deffender;
            UUID uuid = playerDeffender.getUniqueId();
            if (attacker instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) attacker;
                if (!plugin.spawnedMobs.isEmpty()) {
                    if (plugin.spawnedMobs.get(uuid) != null && !plugin.spawnedMobs.get(uuid).isEmpty() && plugin.spawnedMobs.get(uuid).contains(living)) {
                        if (living.getType().equals(EntityType.SKELETON)) {
                            Skeleton skeleton = (Skeleton) living;
                            if (skeleton.getTarget() != null && skeleton.getTarget().equals(playerDeffender)) {
                                skeleton.setTarget(null);
                                event.setCancelled(true);
                            }
                        } else if (living.getType().equals(EntityType.SPIDER)) {
                            Spider spider = (Spider) living;
                            if (spider.getTarget() != null && spider.getTarget().equals(playerDeffender)) {
                                spider.setTarget(null);
                                event.setCancelled(true);
                            }
                        } else if (living.getType().equals(EntityType.ZOMBIE)) {
                            Zombie zombie = (Zombie) living;
                            if (zombie.getTarget() != null && zombie.getTarget().equals(playerDeffender)) {
                                zombie.setTarget(null);
                                event.setCancelled(true);
                            }
                        } else if (living.getType().equals(EntityType.WOLF)) {
                            Wolf wolf = (Wolf) living;
                            if (wolf.getTarget() != null && wolf.getTarget().equals(playerDeffender)) {
                                wolf.setTarget(null);
                                event.setCancelled(true);
                            }
                        }
                    } else if (plugin.spawnedMobs.get(uuid) != null && !plugin.spawnedMobs.get(uuid).isEmpty()) {
                        for (LivingEntity entity : plugin.spawnedMobs.get(uuid)) {
                            if (entity.getType().equals(EntityType.SKELETON)) {
                                Skeleton skeleton = (Skeleton) entity;
                                skeleton.setTarget(living);
                            } else if (entity.getType().equals(EntityType.SPIDER)) {
                                Spider spider = (Spider) entity;
                                spider.setTarget(living);
                            } else if (entity.getType().equals(EntityType.ZOMBIE)) {
                                Zombie zombie = (Zombie) entity;
                                zombie.setTarget(living);
                            } else if (entity.getType().equals(EntityType.WOLF)) {
                                Wolf wolf = (Wolf) entity;
                                wolf.setTarget(living);
                                wolf.setVelocity(living.getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(3));
                            }
                        }
                    }
                }
            }
        }
        if (attacker instanceof Arrow) {
            Arrow arrow = (Arrow) attacker;
            ProjectileSource shooter = arrow.getShooter();
            if (shooter instanceof Skeleton && deffender instanceof Player) {
                Skeleton skeleton1 = (Skeleton) shooter;
                Player player = (Player) deffender;
                if (!plugin.spawnedMobs.isEmpty() && !plugin.spawnedMobs.get(player.getUniqueId()).isEmpty()) {
                    for (LivingEntity entity : plugin.spawnedMobs.get(player.getUniqueId())) {
                        if (entity.getType().equals(EntityType.SKELETON)) {
                            Skeleton skeleton = (Skeleton) entity;
                            skeleton.setTarget(skeleton1);
                        } else if (entity.getType().equals(EntityType.SPIDER)) {
                            Spider spider = (Spider) entity;
                            spider.setTarget(skeleton1);
                        } else if (entity.getType().equals(EntityType.ZOMBIE)) {
                            Zombie zombie = (Zombie) entity;
                            zombie.setTarget(skeleton1);
                        } else if (entity.getType().equals(EntityType.WOLF)) {
                            Wolf wolf = (Wolf) entity;
                            wolf.setTarget(skeleton1);
                            wolf.setVelocity(skeleton1.getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(3));
                        }
                    }
                }
            }
            if (shooter instanceof Player) {
                Player player = (Player) shooter;
                UUID uuid = player.getUniqueId();
                if (player.hasPermission("battlegem.canhave") && deffender instanceof LivingEntity) {
                    LivingEntity living = (LivingEntity) deffender;
                    if (!plugin.spawnedMobs.isEmpty() && plugin.spawnedMobs.get(uuid) != null) {
                        for (LivingEntity entity : plugin.spawnedMobs.get(uuid)) {
                            if (entity.getType().equals(EntityType.SKELETON)) {
                                Skeleton skeleton = (Skeleton) entity;
                                skeleton.setTarget(living);
                            } else if (entity.getType().equals(EntityType.SPIDER)) {
                                Spider spider = (Spider) entity;
                                spider.setTarget(living);
                            } else if (entity.getType().equals(EntityType.ZOMBIE)) {
                                Zombie zombie = (Zombie) entity;
                                zombie.setTarget(living);
                            } else if (entity.getType().equals(EntityType.WOLF)) {
                                Wolf wolf = (Wolf) entity;
                                wolf.setTarget(living);
                                wolf.setVelocity(living.getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(3));
                            }
                        }
                    }
                }
            }
        }
    }
}
