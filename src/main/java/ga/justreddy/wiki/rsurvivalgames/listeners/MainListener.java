package ga.justreddy.wiki.rsurvivalgames.listeners;

import ga.justreddy.wiki.rsurvivalgames.manager.PlayerManager;
import ga.justreddy.wiki.rsurvivalgames.model.board.GameBoard;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author JustReddy
 */
public class MainListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        GamePlayer gamePlayer = PlayerManager.getManager().addGamePlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName());
        GameBoard.getScoreboard().setLobbyBoard(gamePlayer);
    }

}
