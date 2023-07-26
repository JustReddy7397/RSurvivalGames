package network.ranked.rsurvivalgames.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import network.ranked.rsurvivalgames.events.type.SurvivalEvent;
import network.ranked.rsurvivalgames.model.entity.GamePlayer;
import network.ranked.rsurvivalgames.model.game.Game;
import org.jetbrains.annotations.NotNull;

/**
 * @author JustReddy
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
public class PlayerGameLeaveEvent extends SurvivalEvent {

    @NotNull GamePlayer gamePlayer;
    @NotNull Game game;

}
