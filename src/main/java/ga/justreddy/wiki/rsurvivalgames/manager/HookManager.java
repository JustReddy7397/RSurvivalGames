package ga.justreddy.wiki.rsurvivalgames.manager;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import ga.justreddy.wiki.rsurvivalgames.model.hook.PlaceholderHook;
import ga.justreddy.wiki.rsurvivalgames.model.hook.type.Hook;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JustReddy
 */
public class HookManager implements Manager {

    private final Map<String, Hook> hooks;

    private final RSurvivalGames plugin;

    public HookManager(RSurvivalGames plugin) {
        this.plugin = plugin;
        this.hooks = new HashMap<>();
    }

    @Override
    public void start() {
        register(new PlaceholderHook());
    }

    @Override
    public void reload() {

    }

    @Override
    public void stop() {

    }

    private void register(Hook hook) {
        if (!hook.canHook()) return;
        hook.hook(plugin);
        hooks.put(hook.id(), hook);
        ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &aLoaded hook: " + hook.id());

    }

    public boolean hasHook(String hook) {
        return hooks.containsKey(hook);
    }


}
