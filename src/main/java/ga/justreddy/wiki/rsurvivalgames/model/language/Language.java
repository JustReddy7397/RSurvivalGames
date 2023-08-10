package ga.justreddy.wiki.rsurvivalgames.model.language;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

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

    public void sendTitle(Player player, Message title, Message subTitle) {
        player.sendTitle(config.getString(title.getPath()), config.getString(subTitle.getPath()), 20, 60, 20);
    }

    public void sendActionBar(Player player, Message actionBar) {
        PacketContainer chat = new PacketContainer(PacketType.Play.Server.CHAT);
        chat.getChatTypes().write(0, EnumWrappers.ChatType.GAME_INFO);
        chat.getChatComponents().write(0, WrappedChatComponent.fromText(config.getString(actionBar.getPath())));
        RSurvivalGames.getInstance().getProtocolManager().sendServerPacket(player, chat);
    }

    public String getString(Message message) {
        return config.getString(message.getPath());
    }

    public List<String> getStringList(Message message) {
        return config.getStringList(message.getPath());
    }

}
