package network.ranked.rsurvivalgames.tasks;

import lombok.RequiredArgsConstructor;
import network.ranked.rsurvivalgames.manager.PlayerManager;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.storage.type.Storage;

/**
 * To prevent data losses with crashes
 * we will save the players data every 10 seconds
 *
 * @author JustReddy
 */

@RequiredArgsConstructor
public class PlayerTask implements Runnable {

    private final Storage storage;

    @Override
    public void run() {
        for (GamePlayer gamePlayer : PlayerManager.getManager().getGamePlayers()) {
            storage.savePlayer(gamePlayer);
        }
    }
}
