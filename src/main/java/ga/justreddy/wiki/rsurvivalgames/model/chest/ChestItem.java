package ga.justreddy.wiki.rsurvivalgames.model.chest;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

/**
 * @author JustReddy
 */
public class ChestItem {

    private final ItemStack itemStack;

    public ChestItem(ConfigurationSection section) {
        Material material;
        try {
            material = Material.matchMaterial(section.getString("material"));
        } catch (Exception e) {
            throw new IllegalStateException("Material " + section.getString("material") + " does not exist");
        }

        if (material == null) {
            throw new IllegalStateException("Material " + section.getString("material") + " is null");
        }

        itemStack = new ItemStack(material);



    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
