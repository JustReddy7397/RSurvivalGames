package network.ranked.rsurvivalgames.model.game.timers;

import network.ranked.rsurvivalgames.model.game.Game;
import network.ranked.rsurvivalgames.model.game.timers.type.GameTimer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author JustReddy
 */
public class StartingTimer extends GameTimer {

    public StartingTimer(int seconds, JavaPlugin plugin, Game game) {
        super(seconds, plugin, game);
    }

    @Override
    protected void onTick() {
        int seconds = getTicksExceed();

    }

    @Override
    protected void onEnd() {

    }
}
