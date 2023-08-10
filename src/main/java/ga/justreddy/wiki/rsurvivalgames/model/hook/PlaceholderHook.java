package ga.justreddy.wiki.rsurvivalgames.model.hook;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.hook.type.Hook;
import ga.justreddy.wiki.rsurvivalgames.utils.PlaceholderUtil;
import org.bukkit.Bukkit;

/**
 * @author JustReddy
 */
public class PlaceholderHook implements Hook {

    @Override
    public String id() {
        return "PlaceholderAPI";
    }

    @Override
    public boolean canHook() {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    @Override
    public void hook(RSurvivalGames plugin) {
        PlaceholderUtil.PAPI = true;
    }
}
