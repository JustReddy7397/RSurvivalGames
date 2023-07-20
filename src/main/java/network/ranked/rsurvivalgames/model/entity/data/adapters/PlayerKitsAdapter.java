package network.ranked.rsurvivalgames.model.entity.data.adapters;

import com.google.gson.*;
import network.ranked.rsurvivalgames.model.entity.data.PlayerKits;

import java.lang.reflect.Type;

/**
 * Since we use GSON for storing, and it doesn't really like classes
 * we will use this as an alternative
 *
 * @author JustReddy
 */

public class PlayerKitsAdapter implements JsonSerializer<PlayerKits>, JsonDeserializer<PlayerKits> {


    @Override
    public PlayerKits deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(PlayerKits playerKits, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
