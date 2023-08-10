package ga.justreddy.wiki.rsurvivalgames.model.timer.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author JustReddy
 */
@RequiredArgsConstructor
@Getter
public abstract class AbstractTimer implements Runnable {

    private final int seconds;
    protected int ticksExceed = 0;
    private int task;
    private final JavaPlugin plugin;
    private boolean started = false;

    /**
     * Proper method to use to start the timer is start()
     */

    @Override
    public void run() {
        if (ticksExceed == 0) {
            onEnd();
            stop();
            return;
        }
        --ticksExceed;
        onTick();
    }

    public void start() {
        ticksExceed = seconds;
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 20L, 20L);
        started = true;
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
        started = false;
    }

    protected abstract void onTick();

    protected abstract void onEnd();

}
