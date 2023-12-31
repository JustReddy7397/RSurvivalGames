package ga.justreddy.wiki.rsurvivalgames.model.game.events;

import ga.justreddy.wiki.rsurvivalgames.model.game.Game;
import lombok.Getter;
import lombok.Setter;

/**
 * @author JustReddy
 */
@Getter
@Setter
public abstract class GameEvent {

    protected Game game;
    protected String name;
    protected boolean enabled;
    protected int time;

    public GameEvent(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public abstract void start();

    public abstract void stop();

    public void update() {
        if (time > 0) time--;
    }


}
