package com.github.pluginlabs.RandomItemUtility;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class ToggleManager {

    Plugin plugin;
    public HashMap<Player, Boolean> randomItemToggleList = new HashMap<Player, Boolean>();

    public ToggleManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void addToToggle(Player player) { randomItemToggleList.put(player, true); }

    public Boolean getToggleSetting(Player player) { return randomItemToggleList.get(player); }

    public void removeFromToggle(Player player) { randomItemToggleList.remove(player); }

    public void setToggleSetting(Player player, Boolean value) {
        randomItemToggleList.put(player, value);
    }
}
