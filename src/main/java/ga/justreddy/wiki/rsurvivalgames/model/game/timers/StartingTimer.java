package ga.justreddy.wiki.rsurvivalgames.model.game.timers;

import ga.justreddy.wiki.rsurvivalgames.model.game.Game;
import ga.justreddy.wiki.rsurvivalgames.model.game.timers.type.GameTimer;
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
