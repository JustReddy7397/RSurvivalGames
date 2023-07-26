package network.ranked.rsurvivalgames.model.game;

import lombok.Getter;
import lombok.Setter;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.model.game.enums.GameState;
import network.ranked.rsurvivalgames.model.game.enums.GameType;
import network.ranked.rsurvivalgames.model.game.events.GameEvent;
import network.ranked.rsurvivalgames.model.game.map.GameMap;
import network.ranked.rsurvivalgames.model.game.team.GameTeam;
import network.ranked.rsurvivalgames.model.language.Language;
import network.ranked.rsurvivalgames.model.language.Message;
import network.ranked.rsurvivalgames.utils.Cuboid;
import network.ranked.rsurvivalgames.utils.LocationUtil;
import network.ranked.rsurvivalgames.utils.StringUtil;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author JustReddy
 */
@Getter
@Setter
public class Game {


    private final FileConfiguration config;

    private final String name;
    private final String displayname;

    private GameType type;
    private GameState gameState;

    private final GameMap map;


    private List<GamePlayer> players;
    private List<GamePlayer> spectators;
    private List<GameTeam> teams;
    private List<GameEvent> events;
    private List<Location> spawns;

    private Cuboid lobbyCuboid;
    private Cuboid gameCuboid;

    private final ConfigurationSection options;

    private final Map<String, Location> chests;
    private final Map<GamePlayer, Integer> kills;


    private int teamSize;
    private int max;
    private int min;

    public Game(String name, FileConfiguration config) {
        this.name = name;
        this.config = config;
        this.displayname = StringUtil.getDefault(config.getString("settings.displayname"), name);
        // TODO add game map
        this.options = config.getConfigurationSection("options");
        if (options.getBoolean("enabled")) {
            gameState = GameState.WAITING;
        } else {
            gameState = GameState.DISABLED;
        }
        this.map = new GameMap(null, null, true);
        this.min = options.getInt("min-players");
        this.teamSize = options.getInt("teamsize");
        this.type = teamSize == 1 ? GameType.SOLO : GameType.TEAM;

        chests = new HashMap<>();

        if (map.isLoaded()) {
            // TODO


            teams = new ArrayList<>();
            ConfigurationSection spawns = config.getConfigurationSection("spawns");
            for (String key : spawns.getKeys(false)) {
                ConfigurationSection section = spawns.getConfigurationSection(key);
                Location spawn = LocationUtil.getLocation(section.getString("location"));
                GameTeam team = new GameTeam(key, teamSize);
                this.spawns.add(spawn);
                this.teams.add(team);
            }

        }

        kills = new HashMap<>();

    }

    public boolean isGameState(GameState gameState) {
        return this.gameState == gameState;
    }

    public GameEvent getCurrentEvent() {
        for (GameEvent event : events) {
            if (event.isEnabled() && event.getTime() > 0) return event;
        }
        return null;
    }

    private void toSpawn(GamePlayer player) {
        Location location = spawns.remove(
                ThreadLocalRandom.current().nextInt(spawns.size())
        );
        player.teleport(location);
    }


    public void sendMessage(Message message) {
        for (GamePlayer player : players) {
            Language language = player.getSettings().getLanguage();
            language.sendMessage(player, message);
        }
    }

    public void sendTitle(Message title, Message subTitle) {
        for (GamePlayer player : players) {
            Language language = player.getSettings().getLanguage();
            if (player.getPlayer() == null) continue;
            language.sendTitle(player.getPlayer(), title, subTitle);
        }
    }

    public void sendActionBar(Message actionBar) {
        for (GamePlayer player : players) {
            Language language = player.getSettings().getLanguage();
            if (player.getPlayer() == null) continue;
            language.sendActionBar(player.getPlayer(), actionBar);
        }
    }

    public void onCountDown() {
        switch (gameState) {

        }
    }


}
