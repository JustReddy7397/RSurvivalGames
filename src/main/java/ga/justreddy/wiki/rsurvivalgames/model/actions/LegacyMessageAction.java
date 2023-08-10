package ga.justreddy.wiki.rsurvivalgames.model.actions;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.actions.type.Action;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;

/**
 * @author JustReddy
 */
public class LegacyMessageAction implements Action {

    @Override
    public String id() {
        return "legacymessage";
    }

    @Override
    public void onActionActivate(RSurvivalGames plugin, GamePlayer gamePlayer, String data) {
        gamePlayer.sendLegacyMessage(data);
    }
}
