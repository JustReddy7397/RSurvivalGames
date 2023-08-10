package ga.justreddy.wiki.rsurvivalgames.manager;

import com.google.common.collect.ImmutableList;
import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import ga.justreddy.wiki.rsurvivalgames.model.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JustReddy
 */
public class LevelManager implements Manager {

    private final Set<Level> levels;
    private final FileConfiguration config;
    private final boolean enabled;
    private final RSurvivalGames plugin;

    public LevelManager(RSurvivalGames plugin) {
        this.plugin = plugin;
        this.levels = new HashSet<>();
        this.config = plugin.getLevelsConfig().getConfig();
        this.enabled = config.getBoolean("enabled");
    }

    @Override
    public void start() {
        if (!enabled) {
            ChatUtil.sendConsole("&7[&dRSkyWars&7] &cLevels are disabled, not registering...");
            return;
        }
        ConfigurationSection levels = config.getConfigurationSection("levels");
        if (levels == null) {
            throw new IllegalStateException("LEVELS MUST NOT BE NULL");
        }
        for (String key : levels.getKeys(false)) {
            ConfigurationSection prestigeSection = levels.getConfigurationSection(key);
            if (prestigeSection == null) continue;
            for (String str : prestigeSection.getKeys(false)) {
                if (str.equalsIgnoreCase("displayname")) continue;
                ConfigurationSection section = prestigeSection.getConfigurationSection(str);
                if (section == null) continue;
                int level = 0;
                try {
                    level = Integer.parseInt(str);
                } catch (NumberFormatException ex) {
                    ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &cFailed to load level: " + level + "! Reason: LEVEL NOT AN INTEGER");
                    Bukkit.getPluginManager().disablePlugin(plugin);
                    break;
                }

                int xp = section.getInt("xp");
                List<String> commands = section.getStringList("commands");
                Level lvl = new Level(level, xp, commands);
                register(lvl);
            }
        }

        ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &aLoaded " + this.levels.size() + " levels!");

    }

    @Override
    public void reload() {

    }

    @Override
    public void stop() {

    }

    private void register(Level level) {
        levels.add(level);
    }

    public Level getNextLevel(int level) {
        return levels.stream().filter(filtered -> filtered.getLevel() == level + 1)
                .findFirst().orElse(null);
    }

    public Level getByLevel(int level) {
        return levels.stream().filter(filtered -> filtered.getLevel() == level)
                .findFirst().orElse(null);
    }

    public List<Level> getLevels() {
        return ImmutableList.copyOf(levels);
    }

}
