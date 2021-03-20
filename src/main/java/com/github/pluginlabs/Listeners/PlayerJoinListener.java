package com.github.pluginlabs.Listeners;

import com.github.pluginlabs.RandomItemUtility.ToggleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {

    Plugin plugin;
    ToggleManager toggleManager;

    public PlayerJoinListener(Plugin plugin, ToggleManager toggleManager) {
        this.plugin = plugin;
        this.toggleManager = toggleManager;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        toggleManager.addToToggle(player);
    }

}
