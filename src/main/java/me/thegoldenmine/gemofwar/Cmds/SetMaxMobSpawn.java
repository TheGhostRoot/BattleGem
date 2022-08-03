package me.thegoldenmine.gemofwar.Cmds;

import me.thegoldenmine.gemofwar.GemOfWar;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMaxMobSpawn implements CommandExecutor {
    private final GemOfWar plugin;

    public SetMaxMobSpawn(GemOfWar main) {
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //setIntMob("max_mob_spawn", 10);
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
        if (args.length >= 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("battlegem.canhave")) {
                    String amount = args[0];
                    try {
                        int num = Integer.parseInt(amount);
                        plugin.config.setIntMob("max_mob_spawn", num);
                        plugin.config.saveMob();
                        plugin.config.reloadMob();
                        player.sendMessage(NORMAL+"You have set the max amount of mob to spawn by the gem to " + num);
                    } catch (Exception e) {
                        player.sendMessage(ERROR+"Only numbers!");
                    }
                } else {
                    player.sendMessage(ERROR+"You don't have battlegem.canhave permission.");
                }
            } else {
                String amount = args[0];
                try {
                    int num = Integer.parseInt(amount);
                    plugin.config.setIntMob("max_mob_spawn", num);
                    plugin.config.saveMob();
                    plugin.config.reloadMob();
                    sender.sendMessage("- BattleGem - You have set the max amount of mob to spawn by the gem to " + num);
                } catch (Exception e) {
                    sender.sendMessage("- BattleGem - Only numbers!");
                }
            }
        }
        return true;
    }
}
