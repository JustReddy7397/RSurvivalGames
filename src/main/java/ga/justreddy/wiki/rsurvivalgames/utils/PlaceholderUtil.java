package ga.justreddy.wiki.rsurvivalgames.utils;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

/**
 * @author JustReddy
 */
public class PlaceholderUtil {

    public static boolean PAPI = false;

    private static final RSurvivalGames PLUGIN = RSurvivalGames.getPlugin(RSurvivalGames.class);

    public static String parse(Player player, String text) {

        final GamePlayer gamePlayer = PLUGIN.getPlayerManager().getGamePlayer(player.getUniqueId());

        if (text.contains("<kills>") && gamePlayer != null) {
            // TODO
        }

        if (PAPI && gamePlayer != null && gamePlayer.getPlayer() != null) {
            text = PlaceholderAPI.setPlaceholders(gamePlayer.getPlayer(), text);
        }

        return text;
    }

}
