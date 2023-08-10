package ga.justreddy.wiki.rsurvivalgames.tasks;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import lombok.RequiredArgsConstructor;
import ga.justreddy.wiki.rsurvivalgames.manager.PlayerManager;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.storage.type.Storage;

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
        if (storage == null) return;
        for (GamePlayer gamePlayer : RSurvivalGames.getInstance().getPlayerManager()
                .getGamePlayers()) {
            storage.savePlayer(gamePlayer);
        }
    }
}
