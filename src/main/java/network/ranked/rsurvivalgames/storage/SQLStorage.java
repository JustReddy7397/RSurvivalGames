package network.ranked.rsurvivalgames.storage;

import lombok.SneakyThrows;
import network.ranked.rsurvivalgames.RSurvivalGames;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.storage.type.Storage;

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
