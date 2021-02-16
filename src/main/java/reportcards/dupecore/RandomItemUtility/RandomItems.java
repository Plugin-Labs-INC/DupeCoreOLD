package reportcards.dupecore.RandomItemUtility;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import reportcards.dupecore.Config.ConfigurationManager;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class RandomItems {

    Plugin plugin;
    ConfigurationManager configurationManager;
    ToggleManager toggleManager;
    Random rand = new Random();
    public int serverLoopID;
    public RandomItems (Plugin plugin, ConfigurationManager configurationManager, ToggleManager toggleManager) {
        this.plugin = plugin;
        this.configurationManager = configurationManager;
        this.toggleManager = toggleManager;
    }

    public Material getRandomItem() {

        Material[] materials = Material.values();

        Material materialReturn = null;
        Boolean loopError = false;
        Integer integer = 0;

        while (materialReturn == null && loopError == false) {

            Material randomItem = materials[rand.nextInt(materials.length)];

            if (!configurationManager.getConfigList("blockedRandomItems").contains(randomItem.toString()) && !randomItem.equals(Material.AIR)) {
                materialReturn = randomItem;
            }
            integer++;
            if (integer >= 300) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lRANDOM ITEM LOOP EXCEEDED LIMIT"));
                materialReturn = null;
                loopError = true;
            }
        }

        return materialReturn;
    }

    public void startServerLoop() {
        serverLoopID = rand.nextInt(1000);;
        new BukkitRunnable() {
            int id = serverLoopID;
            long tick = 0L;
            long lastUnixTime = new Date().getTime();
            @Override
            public void run() {
               tick++;

               if (tick % 20 == 0) {
                   if (serverLoopID != id) this.cancel();
                   Bukkit.broadcastMessage(configurationManager.getConfigBoolean("doRandomItems") + " | ");
                   if (!configurationManager.getConfigBoolean("doRandomItems")) this.cancel();
               }
               if (tick % 3 == 0) {
                   long timeDifference = new Date().getTime() - lastUnixTime;
                   if (timeDifference >= configurationManager.getConfigDouble("randomItemInterval") * 1000) {
                       giveRandomItems();
                       lastUnixTime = new Date().getTime();
                   }
               }
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    public void giveRandomItems() {

        Material itemToGive = null;
        Boolean giveSameItem = configurationManager.getConfigBoolean("giveSameRandomItem");
        Boolean invFullNotif = configurationManager.getConfigBoolean("inventoryFullNotification");
        String invFullMessage = configurationManager.getConfigString("inventoryFullMessage");
        String pluginPrefix = configurationManager.getConfigString("pluginPrefix");


        if(giveSameItem) {
            itemToGive = getRandomItem();
        }


        for (Player player : Bukkit.getOnlinePlayers()) {
            // player.getInventory().firstEmpty() returns -1 if full

            if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR){

                if (toggleManager.getToggleSetting(player)){

                    if (!giveSameItem) itemToGive = getRandomItem();
                    Map<Integer, ItemStack> remaining = player.getInventory().addItem(new ItemStack(itemToGive, 1));
                    if (remaining.size() != 0 && invFullNotif) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', pluginPrefix + " &r" + invFullMessage)));
                    }

                }

            }

        }

    }

}
