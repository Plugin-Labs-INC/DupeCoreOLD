package reportcards.dupecore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import reportcards.dupecore.Config.ConfigurationManager;
import reportcards.dupecore.RandomItemUtility.RandomItems;

public class CommandTestRandomItem implements CommandExecutor
{

    public Plugin plugin;
    private ConfigurationManager configurationManager;
    RandomItems randomItems;

    public CommandTestRandomItem(Plugin plugin, ConfigurationManager configurationManager) {
        this.plugin = plugin;
        this.configurationManager = configurationManager;
        randomItems = new RandomItems(plugin, configurationManager);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage(String.valueOf(randomItems.getRandomItem()));


        return false;
    }
}
