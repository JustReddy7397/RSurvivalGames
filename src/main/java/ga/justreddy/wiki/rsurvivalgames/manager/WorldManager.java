package ga.justreddy.wiki.rsurvivalgames.manager;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.Random;

/**
 *
 * No implementation of the Manager interface needed.
 *
 * @author JustReddy
 */
@RequiredArgsConstructor
public class WorldManager implements Manager {

    private final RSurvivalGames plugin;

    public World createNewWorld(String name) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.generateStructures(false);
        worldCreator.generator(new ChunkGenerator() {
            @Override
            public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
                ChunkData chunkData = createChunkData(world);
                biome.setBiome(16, 16, Biome.THE_VOID);
                return chunkData;
            }
        });
        World world = worldCreator.createWorld();
        world.setDifficulty(Difficulty.NORMAL);
        world.setSpawnFlags(true, true);
        world.setPVP(true);
        world.setStorm(false);
        world.setThundering(false);
        world.setKeepSpawnInMemory(false);
        world.setAutoSave(false);
        world.setTicksPerSpawns(SpawnCategory.ANIMAL, 1);
        world.setTicksPerSpawns(SpawnCategory.MONSTER, 1);
        world.setWeatherDuration(Integer.MAX_VALUE);
        world.setSpawnLocation(0, 30, 0);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, true);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        return world;
    }

    @SneakyThrows
    public void copyWorld(World world) {
        File worldFolder = new File(Bukkit.getWorldContainer().getParent(), world.getName());
        File mapFolder = new File(plugin.getDataFolder(), "/data/worlds/" + world.getName());
        if (mapFolder.exists()) {
            FileUtil.delete(mapFolder);
        }
        FileUtil.copy(worldFolder, mapFolder);
    }

    @SneakyThrows
    public void copyWorldButDontDelete(String name, String newName) {
        File mapToCopy = new File(plugin.getDataFolder(), "/data/worlds/" + name);
        if (!mapToCopy.exists()) return;
        File newMap = new File(plugin.getDataFolder(), "/data/worlds/" + newName);
        FileUtil.copy(mapToCopy, newMap);

        File worldToCreate = new File(Bukkit.getWorldContainer().getParentFile(), newName);
        FileUtil.copy(mapToCopy, worldToCreate);
        createNewWorld(newName);
    }

    public void removeWorld(World world) {
        if (world == null) return;
        Bukkit.unloadWorld(world, false);
        File worldFolder = new File(Bukkit.getWorldContainer().getParent(), world.getName());
        if (worldFolder == null) return;
        FileUtil.delete(worldFolder);
    }


    @Override
    public void start() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void stop() {

    }
}
