package ga.justreddy.wiki.rsurvivalgames.events.type;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author JustReddy
 */
public abstract class SurvivalEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
