package network.ranked.rsurvivalgames.storage;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import network.ranked.rsurvivalgames.RSurvivalGames;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.model.entity.data.PlayerKits;
import network.ranked.rsurvivalgames.model.entity.data.PlayerSettings;
import network.ranked.rsurvivalgames.model.entity.data.PlayerStats;
import network.ranked.rsurvivalgames.storage.type.Storage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

/**
 * @author JustReddy
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class YamlStorage implements Storage {

    // This is not fun :(

    RSurvivalGames plugin;
    File dataFolder;

    public YamlStorage(RSurvivalGames plugin) {
        this.plugin = plugin;
        this.dataFolder = new File(plugin.getDataFolder().getAbsolutePath() + "/data/players");
        if (!dataFolder.exists()) dataFolder.mkdirs();
    }

    @Override
    public void createPlayer(GamePlayer gamePlayer) {
        if (doesPlayerExists(gamePlayer)) return;
    }

    @Override
    public boolean doesPlayerExists(GamePlayer gamePlayer) {
        return getPlayerFile(gamePlayer) != null;
    }

    @Override
    public void loadPlayer(GamePlayer gamePlayer) {
        if (!doesPlayerExists(gamePlayer)) {
            createPlayer(gamePlayer);
            return;
        }

        FileConfiguration config = getPlayerConfig(gamePlayer);
        if (config == null) {
            throw new NullPointerException("STATS FILE OF " + gamePlayer.getName() + " IS NULL!");
        }

        PlayerSettings settings = (PlayerSettings) config.get("data.settings");
        PlayerStats stats = (PlayerStats) config.get("data.stats");
        PlayerKits kits = (PlayerKits) config.get("data.kits");

    }

    @Override
    public void savePlayer(GamePlayer gamePlayer) {

    }

    @Override
    public void deletePlayer(GamePlayer gamePlayer) {

    }

    @Override
    public void close() {

    }

    private File getPlayerFile(GamePlayer gamePlayer) {
        UUID uuid = gamePlayer.getUniqueId();
        File file = new File(dataFolder.getAbsolutePath() + "/" + uuid + ".yml");
        if (!file.exists()) return null;
        return file;
    }

    private FileConfiguration getPlayerConfig(GamePlayer gamePlayer) {
        File file = getPlayerFile(gamePlayer);
        if (file == null) return null;
        return YamlConfiguration.loadConfiguration(file);
    }

}
