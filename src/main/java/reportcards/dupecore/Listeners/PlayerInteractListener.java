package reportcards.dupecore.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.SpawnEgg;
import org.bukkit.plugin.Plugin;
import reportcards.dupecore.Config.ConfigurationManager;

public class PlayerInteractListener implements Listener {

    public Plugin plugin;
    private ConfigurationManager configurationManager;

    public PlayerInteractListener(Plugin plugin, ConfigurationManager configurationManager) {
        this.plugin = plugin;
        this.configurationManager = configurationManager;
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if ((player.getGameMode() != GameMode.CREATIVE && !player.isOp()) && configurationManager.getConfigBoolean("disableSpawnEggs")) {
            if (String.valueOf(event.getItem().getType()).contains("SPAWN_EGG")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "The server administrators have disabled spawn eggs!");
            }
        }
    }
}