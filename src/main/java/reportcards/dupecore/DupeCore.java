package reportcards.dupecore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import reportcards.dupecore.Commands.CommandDupe;
import reportcards.dupecore.Config.ConfigurationManager;

import java.util.HashMap;

public final class DupeCore extends JavaPlugin {

    private HashMap<String, Integer> iterationList = new HashMap<String, Integer>();
    private ConfigurationManager configurationManager;

    @Override
    public void onEnable() {
        int max = 3; // Max for the iteration function on startup

        // Plugin startup logic
        log(iteration("startup", max) + " &eStarting config manager");
        configurationManager = new ConfigurationManager(this);
        configurationManager.setDefaults();

        //Initialize commands
        log(iteration("startup", max) + " &eInitalizing commands");
        this.getCommand("dupe").setExecutor(new CommandDupe(this));

        log("GAY")

        //Done
        log(iteration("startup", max) + " &aPlugin started completely");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private String iteration(String id, int max) {
        if (!iterationList.containsKey(id)) iterationList.put(id, 1);
        String returnString = "&7[&f" + iterationList.get(id) + "&7/&f" + max + "&7]";
        iterationList.put(id, iterationList.get("id") + 1);
        return null;
    }

    private void log(String text) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

}