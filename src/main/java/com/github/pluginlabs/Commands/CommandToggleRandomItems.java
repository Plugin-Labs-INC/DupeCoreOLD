package com.github.pluginlabs.Commands;


import com.github.pluginlabs.RandomItemUtility.ToggleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.github.pluginlabs.Config.ConfigurationManager;

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
        if (configurationManager.getConfigBoolean("toggleCommand")) {
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

        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', configurationManager.getConfigString("pluginPrefix") + " &3This command has been disabled by an administrator"));
            return true;
        }
    }
}