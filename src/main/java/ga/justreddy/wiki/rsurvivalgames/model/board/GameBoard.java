package ga.justreddy.wiki.rsurvivalgames.model.board;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.creator.BoardCreator;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.model.language.Message;
import ga.justreddy.wiki.rsurvivalgames.model.language.Language;
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

        int id = Bukkit.getScheduler().runTaskTimerAsynchronously(RSurvivalGames.getInstance(), () -> {
            Language newLanguage = player.getSettings().getLanguage();
            creator.setTitle(newLanguage.getString(Message.SCOREBOARD_LOBBY_TITLE));
            creator.setLines(newLanguage.getStringList(Message.SCOREBOARD_LOBBY_LINES));
        }, 0L, 5 * 20L).getTaskId();
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
