package ga.justreddy.wiki.rsurvivalgames.commands;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ga.justreddy.wiki.rsurvivalgames.manager.PlayerManager;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author JustReddy
 */

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SGExecutor {

    CommandSender sender;

    public CommandSender asSender() {
        return sender;
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public Player asPlayer() {
        return (Player) sender;
    }

    public GamePlayer asGamePlayer() {
        return PlayerManager.getManager().getGamePlayer(asPlayer().getUniqueId());
    }

}
