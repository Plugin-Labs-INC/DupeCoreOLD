package reportcards.dupecore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import reportcards.dupecore.Config.ConfigurationManager;

public class CommandDupe implements CommandExecutor {

    public Plugin plugin;
    private ConfigurationManager configurationManager;

    public CommandDupe(Plugin plugin, ConfigurationManager configurationManager) {
        this.plugin = plugin;
        this.configurationManager = configurationManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.getType().equals(Material.AIR)) {
            player.sendMessage(ChatColor.RED + "You cannot dupe air! Hold an item in your hand to dupe it!");
            return false;
        }

        if (configurationManager.getConfigList("blockedDupeItems").contains(String.valueOf(heldItem.getType()))) {
            player.sendMessage(ChatColor.RED + "This item has been blocked by a server administrator, if you think this is a mistake, ask an admin to remove it!");
            return false;
        }
        player.getInventory().addItem(heldItem);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', configurationManager.getConfigString("pluginPrefix") + " &r" + configurationManager.getConfigString("dupeMessage")));

        return true;
    }
}
