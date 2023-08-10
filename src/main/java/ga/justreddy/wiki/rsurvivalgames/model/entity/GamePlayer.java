package ga.justreddy.wiki.rsurvivalgames.model.entity;

import ga.justreddy.wiki.rsurvivalgames.model.game.Game;
import ga.justreddy.wiki.rsurvivalgames.model.game.team.GameTeam;
import ga.justreddy.wiki.rsurvivalgames.model.language.Message;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ga.justreddy.wiki.rsurvivalgames.model.entity.data.PlayerSettings;
import ga.justreddy.wiki.rsurvivalgames.model.language.Language;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author JustReddy
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class GamePlayer {

    final UUID uniqueId;
    final String name;
    final Player player;
    boolean dead = false;
    Game game = null;
    GameTeam team = null;
    PlayerSettings settings;

    public GamePlayer(UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.player = Bukkit.getPlayer(uniqueId);

        this.settings = new PlayerSettings();
    }

    public boolean isPlaying() {
        return game != null;
    }

    public void sendLegacyMessage(String message) {
        ChatUtil.sendMessage(message, player);
    }

    public void sendMessage(Message message) {
        Language language = settings.getLanguage();
        language.sendMessage(this, message);
    }

    public void sendLegacyTitle(String title, String subTitle) {
        // TODO
    }

    public void sendTitle(Message title, Message subTitle) {
        Language language = settings.getLanguage();
        language.sendTitle(player, title, subTitle);
    }

    public void sendLegacyActionBar(String actionBar) {
        // TODO
    }

    public void sendActionBar(Message actionBar) {
        Language language = settings.getLanguage();
        language.sendActionBar(player, actionBar);
    }

    public void teleport(Location location) {
        player.teleport(location);
    }


}
