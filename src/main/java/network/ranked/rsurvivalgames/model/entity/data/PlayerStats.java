package network.ranked.rsurvivalgames.model.entity.data;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * @author JustReddy
 */
public class PlayerStats implements ConfigurationSerializable {

    private int killsSolo;
    private int killsTeam;
    private int gamesWonSolo;
    private int gamesWonTeam;
    private int deathsSolo;
    private int deathsTeam;
    private int lossesSolo;
    private int lossesTeam;

    @Override
    public Map<String, Object> serialize() {
        // TODO
        return null;
    }
}
