package ga.justreddy.wiki.rsurvivalgames.storage;

import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.storage.type.Storage;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author JustReddy
 */
public class MySQLStorage implements Storage {

    private final Connection connection;

    @SneakyThrows
    public MySQLStorage(String host, int port, String username, String password, String database) {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&characterEncoding=utf8", username, password);
        ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &aConnected to the MySQL database");
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

    }
}
