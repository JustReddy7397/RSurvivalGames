package ga.justreddy.wiki.rsurvivalgames.model.actions.type;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;

/**
 * @author JustReddy
 */
public interface Action {

    String id();

    void onActionActivate(RSurvivalGames plugin, GamePlayer gamePlayer, String data);

}
