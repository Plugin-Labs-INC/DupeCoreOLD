package reportcards.dupecore.Config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigurationManager {

    public Plugin plugin;
    public FileConfiguration config;

    public ConfigurationManager(Plugin plugin) {
        this.plugin = plugin;
        config = this.plugin.getConfig();
    }

    public void setDefaults() {
        plugin.saveDefaultConfig();
    }

    public void setConfigValue(String setting, Object value) {
        config.set(setting, value);
        saveConfig();
    }

    public void saveConfig() {
        try {
            plugin.saveConfig();
            Bukkit.getConsoleSender().sendMessage("Saved configuration file");
        } catch(Error e) {
            e.printStackTrace();
        }
    }

}
