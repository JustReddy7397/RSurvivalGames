package ga.justreddy.wiki.rsurvivalgames.model.menu;

import com.cryptomorin.xseries.XMaterial;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.utils.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author JustReddy
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractMenu implements Listener {

    RSurvivalGames plugin;
    @NonFinal boolean refresh = false;
    List<UUID> inventories;

    public AbstractMenu(RSurvivalGames plugin) {
        this.plugin = plugin;
        this.inventories = new ArrayList<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void setInventoryRefresh(long value) {
        if (value <= 0) return;
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new InventoryTask(this), 0L, value);
        refresh = true;
    }

    public abstract void init();

    protected abstract Inventory getInventory();

    public Inventory refreshInventory(GamePlayer gamePlayer, Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = getInventory().getItem(i);
            if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()) continue;

            ItemBuilder newItem = new ItemBuilder(item.clone());
            if (!item.hasItemMeta()) continue;
            ItemMeta meta = item.getItemMeta();
            if (meta == null) continue;
            if (meta.hasDisplayName()) newItem.withName(item.getItemMeta().getDisplayName(), gamePlayer.getPlayer());
            if (meta.hasLore()) newItem.withLore(item.getItemMeta().getLore(), gamePlayer.getPlayer());
            if (item.getType() == XMaterial.PLAYER_HEAD.parseMaterial()) {
                SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
                if (!skullMeta.hasOwner()) continue;
                newItem.setSkullOwner(gamePlayer.getPlayer().getName());
            }
            inventory.setItem(i, newItem.build());
        }
        return inventory;
    }

    public void openInventory(Player player) {
        if (getInventory() == null) return;

        player.openInventory(refreshInventory(player, getInventory()));
        if (refreshEnabled && !openInventories.contains(player.getUniqueId())) {
            openInventories.add(player.getUniqueId());
        }
    }

    public List<UUID> getOpenInventories() {
        return openInventories;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTopInventory().getHolder() instanceof InventoryBuilder && refreshEnabled) {
            openInventories.remove(event.getPlayer().getUniqueId());
        }
    }


}
