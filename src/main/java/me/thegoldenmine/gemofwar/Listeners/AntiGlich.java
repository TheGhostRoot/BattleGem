package me.thegoldenmine.gemofwar.Listeners;

import me.thegoldenmine.gemofwar.GemOfWar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class AntiGlich implements Listener {
    private final GemOfWar plugin;

    public AntiGlich(GemOfWar main) {
        plugin = main;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (plugin.spawnedMobs.get(uuid) != null && !plugin.spawnedMobs.isEmpty()) {
            for (LivingEntity entity : plugin.spawnedMobs.get(uuid)) {
                entity.setHealth(0);
            }
        }
        plugin.spawnedMobs.remove(uuid);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID uuid = player.getUniqueId();
        if (plugin.spawnedMobs.get(uuid) != null && !plugin.spawnedMobs.isEmpty()) {
            for (LivingEntity entity : plugin.spawnedMobs.get(uuid)) {
                entity.setHealth(0);
            }
        }
        plugin.spawnedMobs.remove(uuid);
    }
}
