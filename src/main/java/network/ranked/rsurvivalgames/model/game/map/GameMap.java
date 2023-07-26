package network.ranked.rsurvivalgames.model.game.map;

import lombok.SneakyThrows;
import network.ranked.rsurvivalgames.manager.WorldManager;
import network.ranked.rsurvivalgames.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;

/**
 * @author JustReddy
 */
public class GameMap {

    private final File sourceWorldFolder;
    private File activeWorldFolder;
    private World bukkitWorld;

    public GameMap(File worldFolder, String worldName, boolean loadOnInit) {
        sourceWorldFolder = new File(worldFolder, worldName);
        if (loadOnInit) load();
    }

    @SneakyThrows
    public boolean load() {
        if (isLoaded()) return true;
        activeWorldFolder = new File(Bukkit.getWorldContainer(), sourceWorldFolder.getName());
        FileUtil.copy(sourceWorldFolder, activeWorldFolder);
        bukkitWorld = WorldManager.getManager().createNewWorld(activeWorldFolder.getName());
        return isLoaded();
    }

    public void unload() {
        if (bukkitWorld == null) return;
        Bukkit.unloadWorld(bukkitWorld, false);
        if (activeWorldFolder == null) return;
        FileUtil.delete(activeWorldFolder);
        bukkitWorld = null;
        activeWorldFolder = null;
    }

    public boolean restoreFromSource() {
        unload();
        return load();
    }

    public boolean isLoaded() {
        return bukkitWorld != null;
    }

    public World getWorld() {
        return bukkitWorld;
    }


}
