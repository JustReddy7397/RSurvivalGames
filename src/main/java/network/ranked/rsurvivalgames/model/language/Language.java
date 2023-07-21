package network.ranked.rsurvivalgames.model.language;

import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.nio.charset.StandardCharsets;

/**
 * @author JustReddy
 */
public class Language {

    private final FileConfiguration config;

    public Language(FileConfiguration config) {
        this.config = config;
    }

    public void sendMessage(GamePlayer gamePlayer, Message message) {
        gamePlayer.sendMessage(config.getString(message.getPath()));
    }

    public void sendMessage(CommandSender sender, Message message) {
        ChatUtil.sendMessage(config.getString(message.getPath()), sender);
    }

}
