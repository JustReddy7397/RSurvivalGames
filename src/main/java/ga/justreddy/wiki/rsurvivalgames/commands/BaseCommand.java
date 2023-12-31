package ga.justreddy.wiki.rsurvivalgames.commands;

import ga.justreddy.wiki.rsurvivalgames.commands.sub.LanguageCommand;
import ga.justreddy.wiki.rsurvivalgames.commands.type.SGCommand;
import ga.justreddy.wiki.rsurvivalgames.manager.LanguageManager;
import ga.justreddy.wiki.rsurvivalgames.manager.PlayerManager;
import ga.justreddy.wiki.rsurvivalgames.model.entity.GamePlayer;
import ga.justreddy.wiki.rsurvivalgames.model.language.Language;
import ga.justreddy.wiki.rsurvivalgames.model.language.Message;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author JustReddy
 */
public class BaseCommand implements CommandExecutor {

    private List<SGCommand> commands;

    public BaseCommand() {
        this.commands = new ArrayList<>();
        commands.add(new LanguageCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            ChatUtil.sendMessage("&dThis server is running &7RSurvivalGames &dby &7JustReddy", sender);
            return true;
        }

        SGCommand command = getCommand(args[0]);

        if (command == null) {
            if (sender instanceof Player) {
                GamePlayer player = PlayerManager.getManager().getGamePlayer(((Player) sender).getUniqueId());
                Language language = player.getSettings().getLanguage();
                language.sendMessage(player, Message.MESSAGES_COMMANDS_INVALID);
                return true;
            }

            Language language = LanguageManager.getManager().getLanguage("en");
            language.sendMessage(sender, Message.MESSAGES_COMMANDS_INVALID);

            return true;
        }

        if (command.permission() != null && !sender.hasPermission(command.permission())) {
            if (sender instanceof Player) {
                GamePlayer player = PlayerManager.getManager().getGamePlayer(((Player) sender).getUniqueId());
                Language language = player.getSettings().getLanguage();
                language.sendMessage(player, Message.MESSAGES_COMMANDS_PERMISSION);
                return true;
            }

            Language language = LanguageManager.getManager().getLanguage("en");
            language.sendMessage(sender, Message.MESSAGES_COMMANDS_PERMISSION);

            return true;
        }

        command.onCommand(new SGExecutor(sender), args);

        return true;
    }

    public SGCommand getCommand(String name) {
        return commands
                .stream()
                .filter(cmd -> (cmd.name().equalsIgnoreCase(name))
                        || (cmd.aliases().contains(name)))
                .findFirst().orElse(null);
    }

}
