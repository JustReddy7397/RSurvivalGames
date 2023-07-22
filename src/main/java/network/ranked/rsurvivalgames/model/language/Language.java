package network.ranked.rsurvivalgames.model.language;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author JustReddy
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class Language {

    FileConfiguration config;


    public void sendMessage(GamePlayer gamePlayer, Message message) {
        gamePlayer.sendMessage(config.getString(message.getPath()));
    }

    public void sendMessage(CommandSender sender, Message message) {
        ChatUtil.sendMessage(config.getString(message.getPath()), sender);
    }

}
