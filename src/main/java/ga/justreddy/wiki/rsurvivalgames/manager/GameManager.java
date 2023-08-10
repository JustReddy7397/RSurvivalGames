package ga.justreddy.wiki.rsurvivalgames.manager;

import com.google.common.collect.ImmutableList;
import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.game.Game;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JustReddy
 */
public class GameManager implements Manager {

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

    public Collection<Game> getGames() {
        return ImmutableList.copyOf(games.values());
    }

}
