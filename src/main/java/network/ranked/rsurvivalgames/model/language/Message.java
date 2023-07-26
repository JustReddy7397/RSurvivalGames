package network.ranked.rsurvivalgames.model.language;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author JustReddy
 */
@RequiredArgsConstructor
@Getter
public enum Message {

    // Scoreboard
    SCOREBOARD_LOBBY_TITLE("scoreboard.lobby.title"),
    SCOREBOARD_LOBBY_LINES("scoreboard.lobby.lines"),
    SCOREBOARD_GAME_WAITING_TITLE("scoreboard.waiting.title"),
    SCOREBOARD_GAME_WAITING_LINES("scoreboard.waiting.lines"),
    SCOREBOARD_GAME_STARTING_TITLE("scoreboard.starting.title"),
    SCOREBOARD_GAME_STARTING_LINES("scoreboard.starting.lines"),
    SCOREBOARD_GAME_PLAYING_SOLO_TITLE("scoreboard.playing-solo.title"),
    SCOREBOARD_GAME_PLAYING_SOLO_LINES("scoreboard.playing-solo.lines"),
    SCOREBOARD_GAME_PLAYING_TEAM_TITLE("scoreboard.playing-team.title"),
    SCOREBOARD_GAME_PLAYING_TEAM_LINES("scoreboard.playing-team.lines"),



    // Messages
    MESSAGES_COMMANDS_INVALID("messages.commands.invalid-command"),
    MESSAGES_COMMANDS_PERMISSION("messages.commands.missing-permission");

    private final String path;



}
