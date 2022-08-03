package me.thegoldenmine.gemofwar.Listeners;

import me.thegoldenmine.gemofwar.ItemManager;
import me.thegoldenmine.gemofwar.GemOfWar;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UseGem implements Listener {
    private final GemOfWar plugin;

    public UseGem(GemOfWar main) {
        plugin = main;
    }

    @EventHandler
    public void onOpenGUI(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getInventory().getItemInHand().equals(ItemManager.Gem)) {
                player.openInventory(plugin.getGemGUI());
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        ChatColor darkGray = ChatColor.DARK_GRAY;
        ChatColor strikethrough = ChatColor.STRIKETHROUGH;
        ChatColor gold = ChatColor.GOLD;
        ChatColor bold = ChatColor.BOLD;
        ChatColor yellow = ChatColor.YELLOW;
        ChatColor green = ChatColor.GREEN;
        ChatColor italic = ChatColor.ITALIC;
        ChatColor aqua = ChatColor.AQUA;
        ChatColor red = ChatColor.RED;
        String s = " BattleGem ";
        String WARN = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + yellow + "" + bold + "WARN " + darkGray + "" + strikethrough + "-" + yellow + "" + italic + " ";
        String INFO = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + aqua + "" + bold + "INFO " + darkGray + "" + strikethrough + "-" + aqua + "" + italic + " ";
        String NORMAL = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + darkGray + "" + strikethrough + "-" + green + "" + italic + " ";
        String ERROR = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + red + "" + bold + "ERROR" + darkGray + "" + strikethrough + "-" + red + "" + italic + " ";
        HumanEntity entity = event.getWhoClicked();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (event.getInventory().contains(ItemManager.PigGemGUI)) {
                event.setCancelled(true);
                if (event.getCurrentItem() == null) {
                    player.closeInventory();
                    player.sendMessage(INFO + "You have to choose a mob to spawn.");
                    return;
                }
                UUID uuid = player.getUniqueId();
                switch (Objects.requireNonNull(event.getCurrentItem()).getType()) {
                    case ARROW:
                        // skeleton
                        if (plugin.spawnedMobs.get(uuid) != null && plugin.spawnedMobs.get(uuid).size() >= plugin.config.getIntMob("max_mob_spawn")) {
                            player.sendMessage(WARN+"You have reached the spawn limit.");
                        } else {
                            Skeleton skeleton = player.getWorld().spawn(player.getLocation(), Skeleton.class);
                            skeleton.setCanPickupItems(false);
                            skeleton.setCustomName(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + player.getName() + "'s " + ChatColor.GRAY + "" + ChatColor.ITALIC + "Skeleton");
                            skeleton.setMaxHealth(plugin.config.getIntMob("Skeleton_Health"));
                            skeleton.setHealth(plugin.config.getIntMob("Skeleton_Health"));
                            skeleton.setCustomNameVisible(true);
                            ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
                            ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
                            skeleton.getEquipment().setHelmet(helmet);
                            skeleton.getEquipment().setChestplate(chestplate);
                            if (plugin.spawnedMobs.get(uuid) == null) {
                                List<LivingEntity> list = new ArrayList<>();
                                list.add(skeleton);
                                plugin.spawnedMobs.put(uuid, list);
                            } else {
                                List<LivingEntity> list = plugin.spawnedMobs.get(uuid);
                                list.add(skeleton);
                                if (list.size() <= plugin.config.getIntMob("max_mob_spawn")) {
                                    plugin.spawnedMobs.put(uuid, list);
                                } else {
                                    player.sendMessage(WARN + "You have reached the spawn limit.");
                                }
                            }
                        }
                        break;
                    case ROTTEN_FLESH:
                        // zombie
                        if (plugin.spawnedMobs.get(uuid) != null && plugin.spawnedMobs.get(uuid).size() >= plugin.config.getIntMob("max_mob_spawn")) {
                            player.sendMessage(WARN+"You have reached the spawn limit.");
                        } else {
                            Zombie zombie = player.getWorld().spawn(player.getLocation(), Zombie.class);
                            zombie.setCanPickupItems(false);
                            zombie.setCustomName(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + player.getName() + "'s " + ChatColor.GRAY + "" + ChatColor.ITALIC + "Zombie");
                            zombie.setMaxHealth(plugin.config.getIntMob("Zombie_Health"));
                            zombie.setHealth(plugin.config.getIntMob("Zombie_Health"));
                            zombie.setCustomNameVisible(true);
                            ItemStack helmetZ = new ItemStack(Material.LEATHER_HELMET, 1);
                            ItemStack chestplateZ = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                            ItemStack legginsZ = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                            ItemStack bootsZ = new ItemStack(Material.LEATHER_BOOTS, 1);
                            zombie.getEquipment().setHelmet(helmetZ);
                            zombie.getEquipment().setChestplate(chestplateZ);
                            zombie.getEquipment().setLeggings(legginsZ);
                            zombie.getEquipment().setBoots(bootsZ);
                            if (plugin.spawnedMobs.get(uuid) == null) {
                                List<LivingEntity> list = new ArrayList<>();
                                list.add(zombie);
                                plugin.spawnedMobs.put(uuid, list);
                            } else {
                                List<LivingEntity> list = plugin.spawnedMobs.get(uuid);
                                list.add(zombie);
                                if (list.size() <= plugin.config.getIntMob("max_mob_spawn")) {
                                    plugin.spawnedMobs.put(uuid, list);
                                } else {
                                    player.sendMessage(WARN + "You have reached the spawn limit.");
                                }
                            }
                        }
                        break;
                    case SPIDER_EYE:
                        // spider
                        if (plugin.spawnedMobs.get(uuid) != null && plugin.spawnedMobs.get(uuid).size() >= plugin.config.getIntMob("max_mob_spawn")) {
                            player.sendMessage(WARN+"You have reached the spawn limit.");
                        } else {
                            Spider spider = player.getWorld().spawn(player.getLocation(), Spider.class);
                            spider.setCanPickupItems(false);
                            spider.setCustomName(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + player.getName() + "'s " + ChatColor.GRAY + "" + ChatColor.ITALIC + "Spider");
                            spider.setMaxHealth(plugin.config.getIntMob("Spider_Health"));
                            spider.setHealth(plugin.config.getIntMob("Spider_Health"));
                            spider.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, 99999999));
                            spider.setCustomNameVisible(true);
                            if (plugin.spawnedMobs.get(uuid) == null) {
                                List<LivingEntity> list = new ArrayList<>();
                                list.add(spider);
                                plugin.spawnedMobs.put(uuid, list);
                            } else {
                                List<LivingEntity> list = plugin.spawnedMobs.get(uuid);
                                list.add(spider);
                                if (list.size() <= plugin.config.getIntMob("max_mob_spawn")) {
                                    plugin.spawnedMobs.put(uuid, list);
                                } else {
                                    player.sendMessage(WARN + "You have reached the spawn limit.");
                                }
                            }
                        }
                        break;
                    case POTATO:
                        // pig
                        if (plugin.spawnedMobs.get(uuid) != null && plugin.spawnedMobs.get(uuid).size() >= plugin.config.getIntMob("max_mob_spawn")) {
                            player.sendMessage(WARN+"You have reached the spawn limit.");
                        } else {
                            Pig pig = player.getWorld().spawn(player.getLocation(), Pig.class);
                            pig.setCanPickupItems(false);
                            pig.setCustomName(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + player.getName() + "'s " + ChatColor.GRAY + "" + ChatColor.ITALIC + "Pig");
                            pig.setMaxHealth(plugin.config.getIntMob("Pig_Health"));
                            pig.setHealth(plugin.config.getIntMob("Pig_Health"));
                            pig.setCustomNameVisible(true);
                            if (plugin.spawnedMobs.get(uuid) == null) {
                                List<LivingEntity> list = new ArrayList<>();
                                list.add(pig);
                                plugin.spawnedMobs.put(uuid, list);
                            } else {
                                List<LivingEntity> list = plugin.spawnedMobs.get(uuid);
                                list.add(pig);
                                if (list.size() <= plugin.config.getIntMob("max_mob_spawn")) {
                                    plugin.spawnedMobs.put(uuid, list);
                                } else {
                                    player.sendMessage(WARN + "You have reached the spawn limit.");
                                }
                            }
                        }
                        break;
                    case BONE:
                        // wolf
                        if (plugin.spawnedMobs.get(uuid) != null && plugin.spawnedMobs.get(uuid).size() >= plugin.config.getIntMob("max_mob_spawn")) {
                            player.sendMessage(WARN+"You have reached the spawn limit.");
                        } else {
                            Wolf wolf = player.getWorld().spawn(player.getLocation(), Wolf.class);
                            wolf.setCanPickupItems(false);
                            wolf.setCustomName(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + player.getName() + "'s " + ChatColor.GRAY + "" + ChatColor.ITALIC + "Wolf");
                            wolf.setTamed(true);
                            wolf.setCollarColor(DyeColor.CYAN);
                            wolf.setOwner(player);
                            wolf.setCustomNameVisible(true);
                            if (plugin.spawnedMobs.get(uuid) == null) {
                                List<LivingEntity> list = new ArrayList<>();
                                list.add(wolf);
                                plugin.spawnedMobs.put(uuid, list);
                            } else {
                                List<LivingEntity> list = plugin.spawnedMobs.get(uuid);
                                list.add(wolf);
                                if (list.size() <= plugin.config.getIntMob("max_mob_spawn")) {
                                    plugin.spawnedMobs.put(uuid, list);
                                } else {
                                    player.sendMessage(WARN + "You have reached the spawn limit.");
                                }
                            }
                        }
                        break;
                    case BARRIER:
                        player.closeInventory();
                }
            }
        }
    }
}
