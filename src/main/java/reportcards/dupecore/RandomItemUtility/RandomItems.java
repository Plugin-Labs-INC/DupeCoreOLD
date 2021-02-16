package reportcards.dupecore.RandomItemUtility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import reportcards.dupecore.Config.ConfigurationManager;

import java.util.List;
import java.util.Random;

public class RandomItems {

    Plugin plugin;
    ConfigurationManager configurationManager;
    Random rand = new Random();

    public RandomItems (Plugin plugin, ConfigurationManager configurationManager) {
        this.plugin = plugin;
        this.configurationManager = configurationManager;
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
                List<String> list = configurationManager.getConfigList("blockedRandomItems");
                list.add(materialReturn.toString());
                configurationManager.setConfigValue("blockedRandomItems", list);
            }
            integer++;
            if (integer >= 300) {
                Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', configurationManager.getConfigString("pluginPrefix") + " &cWhilst obtaining a random item, the loop exceeded the max indexes. This may be an issue with the amount of random items blocks."), "*");
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lRANDOM ITEM LOOP EXCEEDED LIMIT"));
                materialReturn = null;
                loopError = true;
            }
        }


        Bukkit.broadcastMessage(integer.toString());

        return materialReturn;
    }

}
