package network.ranked.rsurvivalgames.model.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import network.ranked.rsurvivalgames.model.game.Game;

import java.util.UUID;

/**
 * @author JustReddy
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GamePlayer {

    final UUID uuid;
    final String name;
    boolean dead = false;
    Game game = null;

    public GamePlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public boolean isPlaying() {
        return game != null;
    }

}
