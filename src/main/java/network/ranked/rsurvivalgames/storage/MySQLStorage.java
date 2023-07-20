package network.ranked.rsurvivalgames.storage;

import lombok.SneakyThrows;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.storage.type.Storage;

/**
 * @author JustReddy
 */
public class MySQLStorage implements Storage {

    @Override
    public void createPlayer(GamePlayer gamePlayer) {

    }

    @Override
    public boolean doesPlayerExists(GamePlayer gamePlayer) {
        return false;
    }

    @Override
    public void savePlayer(GamePlayer gamePlayer) {

    }

    @Override
    public void deletePlayer(GamePlayer gamePlayer) {

    }

    @SneakyThrows
    @Override
    public void close() {

    }
}
