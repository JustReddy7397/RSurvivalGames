package network.ranked.rsurvivalgames.commands;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import network.ranked.rsurvivalgames.manager.PlayerManager;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
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
