package ga.justreddy.wiki.rsurvivalgames.model.language;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author JustReddy
 */
@RequiredArgsConstructor
@Getter
public enum Message {

    // Scoreboard          // Config path                   ID used for sending messages or something else
    SCOREBOARD_LOBBY_TITLE("scoreboard.lobby.title", "SCOREBOARD_LOBBY_TITLE"),
    SCOREBOARD_LOBBY_LINES("scoreboard.lobby.lines", "SCOREBOARD_LOBBY_LINES"),
    SCOREBOARD_GAME_WAITING_TITLE("scoreboard.waiting.title", "SCOREBOARD_GAME_WAITING_TITLE"),
    SCOREBOARD_GAME_WAITING_LINES("scoreboard.waiting.lines", "SCOREBOARD_GAME_WAITING_LINES"),
    SCOREBOARD_GAME_STARTING_TITLE("scoreboard.starting.title", "SCOREBOARD_GAME_STARTING_TITLE"),
    SCOREBOARD_GAME_STARTING_LINES("scoreboard.starting.lines", "SCOREBOARD_GAME_STARTING_LINES"),
    SCOREBOARD_GAME_PLAYING_SOLO_TITLE("scoreboard.playing-solo.title", "SCOREBOARD_GAME_PLAYING_SOLO_TITLE"),
    SCOREBOARD_GAME_PLAYING_SOLO_LINES("scoreboard.playing-solo.lines", "SCOREBOARD_GAME_PLAYING_SOLO_LINES"),
    SCOREBOARD_GAME_PLAYING_TEAM_TITLE("scoreboard.playing-team.title", "SCOREBOARD_GAME_PLAYING_TEAM_TITLE"),
    SCOREBOARD_GAME_PLAYING_TEAM_LINES("scoreboard.playing-team.lines", "SCOREBOARD_GAME_PLAYING_TEAM_LINES"),



    // Messages
    MESSAGES_COMMANDS_INVALID("messages.commands.invalid-command", "MESSAGES_COMMANDS_INVALID"),
    MESSAGES_COMMANDS_PERMISSION("messages.commands.missing-permission", "MESSAGES_COMMANDS_PERMISSION");

    private final String path;
    private final String id;


    public static Message getByPath(String path) {
        for (Message message : values()) {
            if (message.path.equalsIgnoreCase(path)) return message;
        }
        return null;
    }

    public static Message getById(String id) {
        for (Message message : values()) {
            if (message.id.equalsIgnoreCase(id)) return message;
        }
        return null;
    }

}
