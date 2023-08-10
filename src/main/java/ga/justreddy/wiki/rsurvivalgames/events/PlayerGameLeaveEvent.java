package ga.justreddy.wiki.rsurvivalgames.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ga.justreddy.wiki.rsurvivalgames.events.type.SurvivalEvent;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.model.game.Game;
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
