package network.ranked.rsurvivalgames;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import network.ranked.rsurvivalgames.manager.LanguageManager;
import network.ranked.rsurvivalgames.manager.Manager;
import network.ranked.rsurvivalgames.manager.PlayerManager;
import network.ranked.rsurvivalgames.model.language.Language;
import network.ranked.rsurvivalgames.model.language.Message;
import network.ranked.rsurvivalgames.tasks.PlayerTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

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

    @Override
    public void onEnable() {
        playerTask.cancel();
        instance = this;
        registerManagers();
        managers.forEach(Manager::start);

        playerTask = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new PlayerTask(null), 0, 10 * 20L);
    }

    @Override
    public void onDisable() {
        playerTask.cancel();
    }

    private void registerManagers() {
        addManagers(new LanguageManager(), new PlayerManager());
    }

    private void addManagers(Manager... managers) {
        this.managers.addAll(Arrays.asList(managers));
    }

}
