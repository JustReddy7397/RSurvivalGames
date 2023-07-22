package network.ranked.rsurvivalgames.storage;

import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.storage.type.Storage;

/**
 * @author JustReddy
 */
public class YamlStorage implements Storage {

    // This is not fun :(

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

    @Override
    public void close() {

    }
}
