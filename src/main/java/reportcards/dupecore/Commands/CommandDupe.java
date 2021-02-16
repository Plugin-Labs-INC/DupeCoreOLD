package reportcards.dupecore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class CommandDupe implements CommandExecutor {

    public Plugin plugin;

    public CommandDupe(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.equals(Material.AIR)) {
            player.sendMessage(ChatColor.RED + "You cannot dupe air! Hold an item in your hand to dupe it!");
            return false;
        }
        player.getInventory().addItem(heldItem);


        return true;
    }
}
