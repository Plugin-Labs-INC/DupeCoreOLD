package reportcards.dupecore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import reportcards.dupecore.Commands.CommandDupe;
import reportcards.dupecore.Commands.CommandToggleRandomItems;
import reportcards.dupecore.Config.ConfigurationManager;
import reportcards.dupecore.Listeners.PlayerInteractListener;
import reportcards.dupecore.Listeners.PlayerJoinListener;
import reportcards.dupecore.Listeners.PlayerLeaveListener;
import reportcards.dupecore.RandomItemUtility.RandomItems;
import reportcards.dupecore.RandomItemUtility.ToggleManager;

import java.util.HashMap;

public final class DupeCore extends JavaPlugin {

    HashMap<String, Long> iterationList = new HashMap<String, Long>();
    private ConfigurationManager configurationManager;
    private ToggleManager toggleManager;
    private RandomItems randomItems;

    @Override
    public void onEnable() {
        int max = 4; // Max for the iteration function on startup
        // Plugin startup logic
        log(iteration("Startup", max) + " &eStarting config manager");
        configurationManager = new ConfigurationManager(this);
        configurationManager.setDefaults();
        toggleManager = new ToggleManager(this);

        for (Player player : Bukkit.getOnlinePlayers()) {
            toggleManager.addToToggle(player);
        }

        randomItems = new RandomItems(this, configurationManager, toggleManager);

        //Initialize commands
        log(iteration("Startup", max) + " &eInitalizing commands");
        this.getCommand("dupe").setExecutor(new CommandDupe(this, configurationManager));
        this.getCommand("toggleitems").setExecutor(new CommandToggleRandomItems(this, toggleManager, configurationManager));

        //Register events
        log(iteration("Startup", max) + " &eRegistering events");
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this, configurationManager), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, toggleManager), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this, toggleManager), this);

        //Start random item loop
        randomItems.startServerLoop();

        //Done
        log(iteration("Startup", max) + " &aPlugin started completely");
    }

    @Override
    public void onDisable() {
        int max = 3; // Max for the iteration function on startup
        // Plugin shutdown logic
        log(iteration("stop", max) + " &aPlugin started completely");
        log(iteration("Startup", max) + " &aPlugin started completely");
    }

    private String iteration(String id, long max) {
        if (!iterationList.containsKey(id)) iterationList.put(id, (long) 1);
        String returnString = "&9[&b" + iterationList.get(id) + "&9/&b" + max + "&9]";
        try {
            iterationList.put(id, iterationList.get(id) + (long) 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    private void log(String text) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

}