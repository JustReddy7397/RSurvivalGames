package ga.justreddy.wiki.rsurvivalgames.utils;

import com.google.gson.*;
import lombok.SneakyThrows;

/**
 * @author JustReddy
 */
public class GsonUtil {

    public static String toJson(Object object, Class<?> clazz) {
        return (new Gson()).toJson(object, clazz);
    }

    @SneakyThrows
    public static String toJson(Object object, Class<? extends JsonSerializer<?>> adapter, Class<?> clazz) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(clazz, adapter.newInstance());
        Gson gson = builder.create();
        return gson.toJson(object, clazz);
    }

    public static <T> T fromJson(String str, Class<T> clazz) {
        return new Gson().fromJson(str, clazz);
    }

    @SneakyThrows
    public static <T> T fromJson(String str, Class<? extends JsonDeserializer<T>> adapter, Class<T> clazz) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(clazz, adapter.newInstance());
        Gson gson = builder.create();
        return gson.fromJson(str, clazz);
    }

    public static <T> T fromJson(JsonElement element, Class<T> clazz) {
        return new Gson().fromJson(element, clazz);
    }


}
