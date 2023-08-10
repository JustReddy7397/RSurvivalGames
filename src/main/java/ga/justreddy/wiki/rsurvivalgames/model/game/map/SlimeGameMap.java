package ga.justreddy.wiki.rsurvivalgames.model.game.map;

import com.grinderwolf.swm.api.world.SlimeWorld;
import org.bukkit.World;

import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 * @author JustReddy
 */
public class SlimeGameMap implements GameMap<SlimeWorld> {

    /**
     * I really cannot be bothered to do this rn
     */

    @Override
    public CompletableFuture<SlimeWorld> load() {
        return null;
    }

    @Override
    public CompletableFuture<World> generateWorld(SlimeWorld slimeWorld) {
        return null;
    }

    @Override
    public CompletableFuture<Void> unload() {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> restoreFromSource() {
        return null;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public SlimeWorld getClassWorld() {
        return null;
    }

    @Override
    public World getBukkitWorld() {
        return null;
    }
}
