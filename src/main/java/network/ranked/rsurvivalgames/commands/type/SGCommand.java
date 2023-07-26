package network.ranked.rsurvivalgames.commands.type;

import network.ranked.rsurvivalgames.commands.SGExecutor;

import java.util.List;

/**
 * @author JustReddy
 */
public interface SGCommand {

    String name();

    String description();

    String syntax();

    String permission();

    List<String> aliases();

    void onCommand(SGExecutor executor, String[] args);

}
