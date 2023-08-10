package ga.justreddy.wiki.rsurvivalgames.model.actions;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.model.language.Message;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import ga.justreddy.wiki.rsurvivalgames.model.actions.type.Action;

/**
 * @author JustReddy
 */
public class MessageAction implements Action {
    @Override
    public String id() {
        return "message";
    }

    @Override
    public void onActionActivate(RSurvivalGames plugin, GamePlayer gamePlayer, String data) {
        Message message = Message.getByPath(data);

        if (message == null) {
            message = Message.getById(data);
        }

        if (message == null) {
            ChatUtil.sendConsoleError("Message " + data + " is not valid!");
            return;
        }

        gamePlayer.sendMessage(message);

    }
}
