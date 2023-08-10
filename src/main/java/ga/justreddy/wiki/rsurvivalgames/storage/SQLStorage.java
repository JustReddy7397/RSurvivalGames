package ga.justreddy.wiki.rsurvivalgames.storage;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.storage.type.Storage;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author JustReddy
 */
public class SQLStorage implements Storage {

    private final Connection connection;

    @SneakyThrows
    public SQLStorage(RSurvivalGames plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        this.connection = DriverManager
                .getConnection("jdbc:sqlite:" + plugin.getDataFolder()
                        .getAbsolutePath() + "/data.db");
    }

    @Override
    public void createPlayer(GamePlayer gamePlayer) {

    }

    @Override
    public boolean doesPlayerExists(GamePlayer gamePlayer) {
        return false;
    }

    @Override
    public void loadPlayer(GamePlayer gamePlayer) {

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
        connection.close();
    }
}
