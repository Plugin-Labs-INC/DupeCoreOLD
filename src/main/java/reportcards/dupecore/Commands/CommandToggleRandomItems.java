package reportcards.dupecore.Commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import reportcards.dupecore.Config.ConfigurationManager;
import reportcards.dupecore.RandomItemUtility.ToggleManager;

public class CommandToggleRandomItems implements CommandExecutor {

    Plugin plugin;
    ToggleManager toggleManager;
    ConfigurationManager configurationManager;

    public CommandToggleRandomItems(Plugin plugin, ToggleManager toggleManager, ConfigurationManager configurationManager) {
        this.plugin = plugin;
        this.toggleManager = toggleManager;
        this.configurationManager = configurationManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (toggleManager.getToggleSetting(player)) {
            toggleManager.setToggleSetting(player, false);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', configurationManager.getConfigString("pluginPrefix") + " &r" + configurationManager.getConfigString("toggleRandomItemOff")));
            return true;

        } else if (!toggleManager.getToggleSetting(player)) {
            toggleManager.setToggleSetting(player, true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', configurationManager.getConfigString("pluginPrefix") + " &r" + configurationManager.getConfigString("toggleRandomItemOn")));
            return true;
        }
        return false;
    }
}