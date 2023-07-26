package network.ranked.rsurvivalgames;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import network.ranked.rsurvivalgames.commands.BaseCommand;
import network.ranked.rsurvivalgames.config.YamlConfig;
import network.ranked.rsurvivalgames.listeners.MainListener;
import network.ranked.rsurvivalgames.manager.LanguageManager;
import network.ranked.rsurvivalgames.manager.Manager;
import network.ranked.rsurvivalgames.manager.PlayerManager;
import network.ranked.rsurvivalgames.model.language.Language;
import network.ranked.rsurvivalgames.model.language.Message;
import network.ranked.rsurvivalgames.tasks.PlayerTask;
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
    static RSurvivalGames instance;

    private List<Manager> managers = new ArrayList<>();

    private BukkitTask playerTask;

    @Getter
    ProtocolManager protocolManager;

    @Getter
    YamlConfig levelsConfig;

    @Override
    public void onEnable() {
        instance = this;
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
        }catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void registerManagers() {
        addManagers(new LanguageManager(), new PlayerManager());
    }

    private void addManagers(Manager... managers) {
        this.managers.addAll(Arrays.asList(managers));
    }

}
