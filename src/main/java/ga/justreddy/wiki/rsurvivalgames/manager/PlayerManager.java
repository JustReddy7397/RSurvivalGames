package ga.justreddy.wiki.rsurvivalgames.manager;

import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.*;

/**
 * @author JustReddy
 */

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerManager implements Manager {

    Map<UUID, GamePlayer> players;

    public PlayerManager() {
        players = new HashMap<>();
    }

    public GamePlayer addGamePlayer(UUID uuid, String name) {
        GamePlayer gamePlayer = getGamePlayer(uuid);

        if (gamePlayer == null) {
            gamePlayer = new GamePlayer(uuid, name);
            players.put(uuid, gamePlayer);
            return gamePlayer;
        }

        return gamePlayer;
    }

    public GamePlayer getGamePlayer(UUID uuid) {
        return players.getOrDefault(uuid, null);
    }

    public void removeGamePlayer(UUID uuid) {
        players.remove(uuid);
    }

    public List<GamePlayer> getGamePlayers() {
        return new ArrayList<>(players.values());
    }


    @Override
    public void start() {
        // EMPTY
    }

    @Override
    public void reload() {
        // EMPTY
    }

    @Override
    public void stop() {
        players.clear();
    }
}
