package ga.justreddy.wiki.rsurvivalgames.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ga.justreddy.wiki.rsurvivalgames.events.type.SurvivalEvent;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.model.game.Game;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author JustReddy
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
public class PlayerGameDeathEvent extends SurvivalEvent {

    @Nullable GamePlayer killer;
    @NotNull GamePlayer victim;
    @NotNull Game game;

}
