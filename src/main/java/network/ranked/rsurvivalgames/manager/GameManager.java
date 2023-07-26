package network.ranked.rsurvivalgames.manager;

import lombok.Getter;
import network.ranked.rsurvivalgames.RSurvivalGames;
import network.ranked.rsurvivalgames.model.game.Game;
import network.ranked.rsurvivalgames.utils.ChatUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JustReddy
 */
public class GameManager implements Manager {

    private static GameManager manager;

    public static GameManager getManager() {
        return manager == null ? manager = new GameManager() : manager;
    }

    @Getter
    private final File gamesFolder;

    private final Map<String, Game> games;

    public GameManager() {
        this.gamesFolder = new File(RSurvivalGames.getInstance().getDataFolder().getAbsolutePath() + "/data/games");
        if (!this.gamesFolder.exists()) {
            this.gamesFolder.mkdirs();
        }

        games = new HashMap<>();

    }

    @Override
    public void start() {
        File[] files = gamesFolder.listFiles();
        if (files == null) return;
        for (File file : files) {
            String name = file.getName();
            if (!name.endsWith(".yml")) continue;
            name = name.replaceAll(".yml", "");
            if (games.containsKey(name)) continue;
            register(name, YamlConfiguration.loadConfiguration(file));
        }
        ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &aLoaded " + games.size() + " games!");
    }

    @Override
    public void reload() {
        stop();
        start();
    }

    @Override
    public void stop() {
        games.clear();
    }

    public void register(String name, FileConfiguration config) {
        games.put(name, new Game(name, config));
    }

}
