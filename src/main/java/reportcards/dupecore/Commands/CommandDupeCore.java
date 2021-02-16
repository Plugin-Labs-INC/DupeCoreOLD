package reportcards.dupecore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import reportcards.dupecore.Config.ConfigurationManager;

public class CommandDupeCore implements CommandExecutor {

    Plugin plugin;
    ConfigurationManager configurationManager;

    public CommandDupeCore(Plugin plugin, ConfigurationManager configurationManager) {
        this.plugin = plugin;
        this.configurationManager = configurationManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage(new String[] {color(configurationManager.getConfigString("pluginPrefix") + " &ePlugin information:"),
                    color("&eVersion: &f" + plugin.getDescription().getVersion()),
                    color("&eAuthors: &f" + String.join(", ", plugin.getDescription().getAuthors())),
                    color("&eGithub: &f" + plugin.getDescription().getWebsite()),
                    color("&eCommands: &f/dupe&e, &f/toggleitems&e, &f/dupecore [reload]")});
        }


        return true;
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
