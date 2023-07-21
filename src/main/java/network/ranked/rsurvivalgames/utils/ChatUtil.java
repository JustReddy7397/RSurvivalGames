package network.ranked.rsurvivalgames.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author JustReddy
 */
public class ChatUtil {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendMessage(String message, CommandSender sender) {
        sender.sendMessage(format(message));
    }

    public static void sendConsole(String message) {
        sendMessage(message, Bukkit.getConsoleSender());
    }

}
