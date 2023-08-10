package ga.justreddy.wiki.rsurvivalgames.commands.sub;

import ga.justreddy.wiki.rsurvivalgames.commands.SGExecutor;
import ga.justreddy.wiki.rsurvivalgames.commands.type.SGCommand;
import ga.justreddy.wiki.rsurvivalgames.manager.LanguageManager;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.model.language.Language;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JustReddy
 */
public class LanguageCommand implements SGCommand {
    @Override
    public String name() {
        return "language";
    }

    @Override
    public String description() {
        return "Test command for setting a language";
    }

    @Override
    public String syntax() {
        return "/sg language <language>";
    }

    @Override
    public String permission() {
        return null;
    }

    @Override
    public List<String> aliases() {
        return new ArrayList<>();
    }

    @Override
    public void onCommand(SGExecutor executor, String[] args) {

        if (args.length < 1) return;
        if (!executor.isPlayer()) return;

        GamePlayer player = executor.asGamePlayer();

        Language language = player.getSettings().getLanguage();
        String lang = args[1];
        if (LanguageManager.getManager().getLanguage(lang) == null) {
            player.sendMessage("&cThis language is invalid");
            return;
        }
        player.getSettings().setLanguage(lang);
        player.sendMessage("&aSuccessfully set your language to " + lang);
    }
}
