package reportcards.dupecore.RandomItemUtility;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import reportcards.dupecore.Config.ConfigurationManager;

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

        while (materialReturn == null) {

            Material randomItem = materials[rand.nextInt(materials.length)];

            if (!configurationManager.getConfigList("bannedRandomItems").contains(randomItem)) {
                materialReturn = randomItem;
            }
        }

        return materialReturn;
    }

}
