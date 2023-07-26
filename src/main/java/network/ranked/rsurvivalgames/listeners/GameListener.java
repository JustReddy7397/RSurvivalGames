package network.ranked.rsurvivalgames.listeners;

import network.ranked.rsurvivalgames.manager.PlayerManager;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.model.game.Game;
import network.ranked.rsurvivalgames.model.game.enums.GameState;
import network.ranked.rsurvivalgames.model.game.events.GameEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author JustReddy
 */
public class GameListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        GamePlayer player = PlayerManager.getManager().getGamePlayer(event.getPlayer().getUniqueId());
        Game game = player.getGame();
        if (game == null) return;
        if (player.isDead()) return;
        if (!game.isGameState(GameState.PLAYING)) return;
        Location from = event.getFrom();
        if (from == null) return;
        Location to = event.getTo();
        if (to == null) return;
        GameEvent current = game.getCurrentEvent();
        if (from.getBlockX() == to.getBlockX()
                && from.getBlockY() == to.getBlockY()
                && from.getBlockZ() == to.getBlockZ()
                && current != null && current.getName().equalsIgnoreCase("releasing")
        ) {
            player.teleport(from);
            return;
        }
    }



}
