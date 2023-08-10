package ga.justreddy.wiki.rsurvivalgames.utils.item;

import com.cryptomorin.xseries.XMaterial;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.utils.PlaceholderUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JustReddy
 */
public class ItemBuilder {

    private final ItemStack ITEM_STACK;
    private static boolean hasUsername;

    public boolean isHasUsername() {
        return hasUsername;
    }

    public ItemBuilder(ItemStack item) {
        this.ITEM_STACK = item;
    }

    public static ItemBuilder getItemStack(ConfigurationSection section, GamePlayer player) {
        ItemStack item = XMaterial.matchXMaterial(section.getString("material").toUpperCase()).get().parseItem();

        ItemBuilder builder = new ItemBuilder(item);

        if (section.contains("amount")) {
            builder.withAmount(section.getInt("amount"));
        }

        if (section.contains("username") && player != null) {
            builder.setSkullOwner(section.getString("username").replace("<player>", player.getName()));
            hasUsername = true;
        }

        if (section.contains("display_name")) {
            if (player != null) builder.withName(section.getString("display_name"), player.getPlayer());
            else builder.withName(section.getString("display_name"));
        }

        if (section.contains("lore")) {
            if (player != null) builder.withLore(section.getStringList("lore"), player.getPlayer());
            else builder.withLore(section.getStringList("lore"));
        }

        if (section.contains("glow") && section.getBoolean("glow")) {
            builder.withGlow();
        }

        if (section.contains("item_flags")) {
            List<ItemFlag> flags = new ArrayList<>();
            section.getStringList("item_flags").forEach(text -> {
                try {
                    ItemFlag flag = ItemFlag.valueOf(text);
                    flags.add(flag);
                } catch (IllegalArgumentException ignored) {
                }
            });
            builder.withFlags(flags.toArray(new ItemFlag[0]));
        }

        return builder;
    }

    public static ItemBuilder getItemStack(ConfigurationSection section) {
        return getItemStack(section, null);
    }

    public ItemBuilder withAmount(int amount) {
        ITEM_STACK.setAmount(amount);
        return this;
    }

    public ItemBuilder withFlags(ItemFlag... flags) {
        ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.addItemFlags(flags);
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemBuilder withName(String name) {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.setDisplayName(ChatUtil.format(name));
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemBuilder withName(String name, Player player) {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.setDisplayName(ChatUtil.format(PlaceholderUtil.parse(player, name)));
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        try {
            SkullMeta im = (SkullMeta) ITEM_STACK.getItemMeta();
            im.setOwner(owner);
            ITEM_STACK.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public ItemBuilder withLore(List<String> lore, Player player) {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        List<String> coloredLore = new ArrayList<String>();
        for (String s : lore) {
            s = PlaceholderUtil.parse(player, s);
            coloredLore.add(ChatUtil.format(s));
        }
        meta.setLore(coloredLore);
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemBuilder withLore(List<String> lore) {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        List<String> coloredLore = new ArrayList<String>();
        for (String s : lore) {
            coloredLore.add(TextUtil.color(s));
        }
        meta.setLore(coloredLore);
        ITEM_STACK.setItemMeta(meta);
        return this;
    }


    @SuppressWarnings("deprecation")
    public ItemBuilder withDurability(int durability) {
        ITEM_STACK.setDurability((short) durability);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder withData(int data) {
        ITEM_STACK.setDurability((short) data);
        return this;
    }

    public ItemBuilder withEnchantment(Enchantment enchantment, final int level) {
        ITEM_STACK.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder withEnchantment(Enchantment enchantment) {
        ITEM_STACK.addUnsafeEnchantment(enchantment, 1);
        return this;
    }

    public ItemBuilder withGlow() {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ITEM_STACK.setItemMeta(meta);
        ITEM_STACK.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        return this;
    }

    public ItemBuilder withType(Material material) {
        ITEM_STACK.setType(material);
        return this;
    }

    public ItemBuilder clearLore() {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.setLore(new ArrayList<String>());
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemBuilder clearEnchantments() {
        for (Enchantment enchantment : ITEM_STACK.getEnchantments().keySet()) {
            ITEM_STACK.removeEnchantment(enchantment);
        }
        return this;
    }

    public ItemBuilder withColor(Color color) {
        Material type = ITEM_STACK.getType();
        if (type == Material.LEATHER_BOOTS || type == Material.LEATHER_CHESTPLATE || type == Material.LEATHER_HELMET || type == Material.LEATHER_LEGGINGS) {
            LeatherArmorMeta meta = (LeatherArmorMeta) ITEM_STACK.getItemMeta();
            meta.setColor(color);
            ITEM_STACK.setItemMeta(meta);
            return this;
        } else {
            throw new IllegalArgumentException("withColor is only applicable for leather armor!");
        }
    }

    public ItemUtil withPotion(PotionType potionType, int level, boolean splash, boolean hasExtendedDuration) {
        Material type = ITEM_STACK.getType();
        if (type != Material.POTION) {
            throw new IllegalArgumentException("withPotion is only applicable for potions!");
        }
        Potion potion = new Potion(potionType);
        potion.setLevel(level);
        potion.setSplash(splash);
        if (hasExtendedDuration) {
            potion.setHasExtendedDuration(true);
        }
        potion.apply(ITEM_STACK);
        return this;
    }

    public ItemUtil withPotion(PotionType potion, PotionEffect effect) {
        Material type = ITEM_STACK.getType();
        if (type != Material.POTION) {
            throw new IllegalArgumentException("withPotion is only applicable for potions!");
        }
        Potion potion1 = new Potion(potion);
        potion1.setSplash(true);
        potion1.apply(ITEM_STACK);
        PotionMeta meta = (PotionMeta) ITEM_STACK.getItemMeta();
        meta.addCustomEffect(effect, true);
        if (!potion.getEffectType().equals(effect.getType())) {
            meta.removeCustomEffect(potion.getEffectType());
        }
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemUtil withPotion(PotionType potion, boolean splash, PotionEffect effect) {
        Material type = ITEM_STACK.getType();
        if (type != Material.POTION) {
            throw new IllegalArgumentException("withPotion is only applicable for potions!");
        }
        Potion potion1 = new Potion(potion);
        potion1.setSplash(splash);
        potion1.apply(ITEM_STACK);
        PotionMeta meta = (PotionMeta) ITEM_STACK.getItemMeta();
        meta.addCustomEffect(effect, true);
        if (!potion.getEffectType().equals(effect.getType())) {
            meta.removeCustomEffect(potion.getEffectType());
        }
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemUtil withBookEnchantments(Enchantment enchantment, int level) {
        Material type = ITEM_STACK.getType();
        if (type != Material.ENCHANTED_BOOK) {
            throw new IllegalArgumentException("withBookEnchantment is only applicable for enchantment books!");
        }
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) ITEM_STACK.getItemMeta();
        meta.addStoredEnchant(enchantment, level, false);
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemUtil withBase64(String texture) {
        Material type = ITEM_STACK.getType();
        if (type != XMaterial.PLAYER_HEAD.parseMaterial()) {
            throw new IllegalArgumentException("withBase64 is only applicable for player heads!");
        }

        final SkullMeta meta = (SkullMeta) ITEM_STACK.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", texture));
        meta.setDisplayName("");
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (final IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return ITEM_STACK;
    }


}
