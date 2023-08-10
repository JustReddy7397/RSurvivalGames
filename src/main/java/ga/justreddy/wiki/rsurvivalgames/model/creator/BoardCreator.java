package ga.justreddy.wiki.rsurvivalgames.model.creator;

import ga.justreddy.wiki.rsurvivalgames.model.board.lib.BoardHelper;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JustReddy
 */
public abstract class BoardCreator {

    private final BoardHelper scoreboard;

    public BoardCreator(GamePlayer gamePlayer) {
        this.scoreboard = new BoardHelper(gamePlayer.getPlayer());
    }

    public void setTitle(String title) {
        title = ChatUtil.format(title);

        if (title.length() > 32) {
            title = title.substring(0, 32);
        }

        scoreboard.updateTitle(title);
    }

    public void setLine(int line, String text) {
        text = ChatUtil.format(setPlaceholders(text));

        if (text.length() > 32) {
            text = text.substring(0, 32);
        }

        scoreboard.updateLine(line, text);
    }

    public void setLines(List<String> lines) {
        List<String> list = new ArrayList<>();
        for (String line : lines) {
            list.add(ChatUtil.format(setPlaceholders(line)));
        }
        scoreboard.updateLines(list);
    }

    public void setLines(String... lines) {
        List<String> list = new ArrayList<>();
        for (String line : lines) {
            list.add(ChatUtil.format(setPlaceholders(line)));
        }
        scoreboard.updateLines(list);
    }

    public void removeLine(int line) {
        scoreboard.removeLine(line);
    }

    protected abstract String setPlaceholders(String text);


}
