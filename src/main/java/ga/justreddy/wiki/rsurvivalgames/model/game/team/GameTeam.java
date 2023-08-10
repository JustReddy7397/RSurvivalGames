package ga.justreddy.wiki.rsurvivalgames.model.game.team;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JustReddy
 */

@Getter
@EqualsAndHashCode
@ToString
public class GameTeam {

    private final String id;
    private final int size;
    private final List<GamePlayer> players;

    public GameTeam(String id, int size) {
        this.id = id;
        this.size = size;
        this.players = new ArrayList<>();
    }

    public void addPlayer(GamePlayer player) {
        if (hasPlayer(player)) return;
        players.add(player);
        player.setTeam(this);
    }

    public boolean hasPlayer(GamePlayer player) {
        return players.contains(player);
    }

    public void removePlayer(GamePlayer player) {
        if (!hasPlayer(player)) return;
        players.remove(player);
        player.setTeam(null);
    }

    public List<GamePlayer> getAlive() {
        return players.stream()
                .filter(
                        player -> !player.isDead()
                ).collect(Collectors.toList());
    }

    public List<GamePlayer> getDead() {
        return players.stream()
                .filter(
                        GamePlayer::isDead
                ).collect(Collectors.toList());
    }


    public boolean isFull() {
        return players.size() >= size;
    }

}
