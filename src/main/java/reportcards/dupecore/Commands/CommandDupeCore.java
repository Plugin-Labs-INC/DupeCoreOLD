package reportcards.dupecore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import reportcards.dupecore.Config.ConfigurationManager;
import reportcards.dupecore.RandomItemUtility.RandomItems;

public class CommandDupeCore implements CommandExecutor {

    Plugin plugin;
    ConfigurationManager configurationManager;
    RandomItems randomItems;

    public CommandDupeCore(Plugin plugin, ConfigurationManager configurationManager, RandomItems randomItems) {
        this.plugin = plugin;
        this.configurationManager = configurationManager;
        this.randomItems = randomItems;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage(new String[] {color(configurationManager.getConfigString("pluginPrefix") + " &ePlugin information:"),
                    color("&eVersion: &f" + plugin.getDescription().getVersion()),
                    color("&eAuthors: &f" + String.join(", ", plugin.getDescription().getAuthors())),
                    color("&eGithub: &f" + plugin.getDescription().getWebsite()),
                    color("&eCommands: &f/dupe&e, &f/toggleitems&e, &f/dupecore [reload]")});
            return true;
        } else if (args[0].equals("reload")) {
            if (commandSender.hasPermission("dupecore.reload")) {
                commandSender.sendMessage(color(configurationManager.getConfigString("pluginPrefix") + " &eReloading configuration file"));
                try {
                    plugin.reloadConfig();
                    configurationManager.getNewConfig();
                    randomItems.startServerLoop();

                } catch (Exception e) {
                    commandSender.sendMessage(color("&cError while reloading config, check logs."));
                    e.printStackTrace();
                    return true;
                }
                commandSender.sendMessage(color(configurationManager.getConfigString("pluginPrefix") + " &aReloaded configuration file."));

            } else {
                commandSender.sendMessage("&cInvalid Permissions.");
            }
            return true;
        }


        return false;
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
