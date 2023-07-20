package network.ranked.rsurvivalgames.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.storage.type.Storage;
import org.bson.Document;

/**
 * @author JustReddy
 */
public class MongoStorage implements Storage {

    private final MongoCollection<Document> collection;

    public MongoStorage(String url) {
        try (MongoClient client = MongoClients.create(url)) {
            MongoDatabase database = client.getDatabase("survivalgames");
            collection = database.getCollection("stats");
        }
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

    @Override
    public void close() {
        // EMPTY
    }
}
