package ga.justreddy.wiki.rsurvivalgames.model.hook.type;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;

/**
 * @author JustReddy
 */
public interface Hook {

    String id();

    boolean canHook();

    void hook(RSurvivalGames plugin);

}
