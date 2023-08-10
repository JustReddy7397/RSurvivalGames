package ga.justreddy.wiki.rsurvivalgames;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import ga.justreddy.wiki.rsurvivalgames.commands.BaseCommand;
import ga.justreddy.wiki.rsurvivalgames.listeners.MainListener;
import ga.justreddy.wiki.rsurvivalgames.manager.*;
import ga.justreddy.wiki.rsurvivalgames.tasks.PlayerTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ga.justreddy.wiki.rsurvivalgames.config.YamlConfig;
import network.ranked.rsurvivalgames.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public final class RSurvivalGames extends JavaPlugin {

    @Getter
    private static RSurvivalGames instance;

    private List<Manager> managers = new ArrayList<>();

    private BukkitTask playerTask;

    ProtocolManager protocolManager;

    YamlConfig levelsConfig;

    // Managers
    GameManager gameManager;
    HookManager hookManager;
    LanguageManager languageManager;
    LevelManager levelManager;
    PlayerManager playerManager;
    SerializationManager serializationManager;
    WorldManager worldManager;

    @Override
    public void onEnable() {
        instance = this;
        if (!loadConfigs()) return;
        registerManagers();
        managers.forEach(Manager::start);
        protocolManager = ProtocolLibrary.getProtocolManager();
        getServer().getPluginManager().registerEvents(new MainListener(), this);
        getCommand("sg").setExecutor(new BaseCommand());
        playerTask = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new PlayerTask(null), 0, 10 * 20L);
    }

    @Override
    public void onDisable() {
        playerTask.cancel();
        managers.forEach(Manager::stop);
    }

    private boolean loadConfigs() {
        String current = "Config File";
        try {
            current = "levels.yml";
            levelsConfig = new YamlConfig(current);
            if (levelsConfig.isOutdated(1)) return false;
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void registerManagers() {
        addManagers(languageManager = new LanguageManager(),
                playerManager = new PlayerManager(),
                gameManager = new GameManager(),
                levelManager = new LevelManager(),
                serializationManager = new SerializationManager(),
                hookManager = new HookManager(),
                worldManager = new WorldManager()
        );
    }

    private void addManagers(Manager... managers) {
        this.managers.addAll(Arrays.asList(managers));
    }

}
