package ga.justreddy.wiki.rsurvivalgames.storage.type;


import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;

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
