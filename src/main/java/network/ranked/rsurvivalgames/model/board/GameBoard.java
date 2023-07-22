package network.ranked.rsurvivalgames.model.board;

import network.ranked.rsurvivalgames.RSurvivalGames;
import network.ranked.rsurvivalgames.model.creator.BoardCreator;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.model.language.Language;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author JustReddy
 */
public class GameBoard {

    private static GameBoard scoreboard;

    public static GameBoard getScoreboard() {
        return scoreboard == null ? scoreboard = new GameBoard() : scoreboard;
    }

    private final Map<UUID, Integer> data;

    private GameBoard() {
        this.data = new HashMap<>();
    }

    public void setLobbyBoard(GamePlayer player) {
        if (player == null) return;
        removeScoreboard(player);
        Language language = player.getSettings().getLanguage();
        FileConfiguration config = language.getConfig();
        ConfigurationSection section = config.getConfigurationSection("scoreboard.lobby");
        if (!section.getBoolean("enabled")) return;
        BoardCreator creator = new BoardCreator(player) {
            @Override
            protected String setPlaceholders(String text) {

                // TODO add placeholders

                return text;
            }
        };

        creator.setTitle(section.getString("title"));
        int id = Bukkit.getScheduler().runTaskTimerAsynchronously(RSurvivalGames.getInstance(), () -> {
            creator.setLines(section.getStringList("lines"));
        }, 0L, 10 * 20L).getTaskId();
        data.put(player.getUniqueId(), id);
    }

    public void removeScoreboard(GamePlayer player) {
        UUID uuid = player.getUniqueId();
        if (!data.containsKey(uuid)) return;
        player.getPlayer().setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
        if (data.containsKey(uuid)) {
            Bukkit.getServer().getScheduler().cancelTask(data.get(uuid));
        }
        data.remove(uuid);
    }

    public void shutdown() {
        for (Integer integer : data.values()) {
            Bukkit.getScheduler().cancelTask(integer);
        }
        data.clear();
    }


}
