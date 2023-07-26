package network.ranked.rsurvivalgames.listeners;

import network.ranked.rsurvivalgames.manager.PlayerManager;
import network.ranked.rsurvivalgames.model.board.GameBoard;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
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
