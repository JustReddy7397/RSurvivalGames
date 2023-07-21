package network.ranked.rsurvivalgames.model.game;

import lombok.Getter;
import lombok.Setter;
import network.ranked.rsurvivalgames.utils.StringUtil;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author JustReddy
 */
@Getter
@Setter
public class Game {

    private final String name;
    private final FileConfiguration config;
    private final String displayname;

    public Game(String name, FileConfiguration config) {
        this.name = name;
        this.config = config;
        this.displayname = StringUtil.getDefault(config.getString("settings.displayname"), name);

    }

}
