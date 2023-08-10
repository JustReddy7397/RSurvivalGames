package ga.justreddy.wiki.rsurvivalgames.model.game.map;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.manager.WorldManager;
import ga.justreddy.wiki.rsurvivalgames.utils.FileUtil;
import ga.justreddy.wiki.rsurvivalgames.utils.FutureUtil;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 * @author JustReddy
 */
public class BukkitGameMap implements GameMap<World> {

    private final File sourceWorldFolder;
    private File activeWorldFolder;
    private World bukkitWorld;

    private final RSurvivalGames plugin = RSurvivalGames.getPlugin(RSurvivalGames.class);

    public BukkitGameMap(File worldFolder, String worldName, boolean loadOnInit) {
        sourceWorldFolder = new File(worldFolder, worldName);
        if (loadOnInit) load();
    }

    @SneakyThrows
    @Override
    public CompletableFuture<World> load() {
        if (isLoaded()) return FutureUtil.futureAsync(() -> null);
        activeWorldFolder = new File(Bukkit.getWorldContainer(), sourceWorldFolder.getName());
        FileUtil.copy(sourceWorldFolder, activeWorldFolder);
        return FutureUtil.future(() -> plugin.getWorldManager().createNewWorld(activeWorldFolder.getName()));
    }

    @Override
    public CompletableFuture<World> generateWorld(World world) {
        if (world == null) return CompletableFuture.supplyAsync(() -> null);
        return FutureUtil.future(() -> bukkitWorld = world);
    }

    @Override
    public CompletableFuture<Void> unload() {
        return FutureUtil.futureAsync(() -> {
            if (bukkitWorld == null) return;
            Bukkit.unloadWorld(bukkitWorld, false);
            if (activeWorldFolder == null) return;
            FileUtil.delete(activeWorldFolder);
            bukkitWorld = null;
            activeWorldFolder = null;
        });
    }

    @Override
    public CompletableFuture<Boolean> restoreFromSource() {
        unload().thenAccept(unused -> {
            load().thenAccept(this::generateWorld);
        });
        return FutureUtil.delayedAsync(this::isLoaded, 3);
    }

    @Override
    public boolean isLoaded() {
        return bukkitWorld != null;
    }

    @Override
    public World getClassWorld() {
        return bukkitWorld;
    }

    @Override
    public World getBukkitWorld() {
        return bukkitWorld;
    }


}
