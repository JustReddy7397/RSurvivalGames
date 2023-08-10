package ga.justreddy.wiki.rsurvivalgames.model.game.map;

import org.bukkit.World;

import java.util.concurrent.CompletableFuture;

/**
 * @author JustReddy
 */
public interface GameMap<T> {

    CompletableFuture<T> load();

    CompletableFuture<World> generateWorld(T t);

    CompletableFuture<Void> unload();

    CompletableFuture<Boolean> restoreFromSource();

    boolean isLoaded();

    T getClassWorld();

    World getBukkitWorld();

}
