package me.thegoldenmine.gemofwar.Cmds;

import me.thegoldenmine.gemofwar.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveGem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("battlegem.canhave")) {
                if (player.getInventory().contains(ItemManager.Gem)) {
                    player.sendMessage(WARN+"You already have the Gem.");
                } else {
                    player.sendMessage(NORMAL+"You have the Gem.");
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    player.getInventory().addItem(ItemManager.Gem);
                }
            } else {
                if (player.getInventory().contains(ItemManager.Gem)) {
                    player.getInventory().remove(ItemManager.Gem);
                    player.sendMessage(ERROR+"You are not suppose to have that Gem.");
                }
                player.sendMessage(ERROR+"You don't have battlegem.canhave permission.");
            }
        } else {
            sender.sendMessage("Only players can have the power of the Gem.");
        }
        return true;
    }
}
