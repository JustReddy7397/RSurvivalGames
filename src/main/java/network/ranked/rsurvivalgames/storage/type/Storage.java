package network.ranked.rsurvivalgames.storage.type;


import network.ranked.rsurvivalgames.model.entity.GamePlayer;

/**
 * @author JustReddy
 */
public interface Storage {

    void createPlayer(GamePlayer gamePlayer);

    boolean doesPlayerExists(GamePlayer gamePlayer);

    void loadPlayer(GamePlayer gamePlayer);

    void savePlayer(GamePlayer gamePlayer);

    void deletePlayer(GamePlayer gamePlayer);

    void close();

}
