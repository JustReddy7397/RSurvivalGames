package ga.justreddy.wiki.rsurvivalgames.model.timer;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.game.Game;
import ga.justreddy.wiki.rsurvivalgames.model.timer.type.AbstractTimer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author JustReddy
 */
public class GameTimer extends AbstractTimer {

    private final Game game;

    public GameTimer(Game game) {
        super(0, RSurvivalGames.getPlugin(RSurvivalGames.class));
        this.game = game;
    }

    @Override
    public void run() {
        game.onCountDown();
    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onEnd() {

    }
}
