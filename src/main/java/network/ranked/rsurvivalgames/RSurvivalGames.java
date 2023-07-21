package network.ranked.rsurvivalgames;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import network.ranked.rsurvivalgames.manager.LanguageManager;
import network.ranked.rsurvivalgames.model.language.Language;
import network.ranked.rsurvivalgames.model.language.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public final class RSurvivalGames extends JavaPlugin {

    @Getter static RSurvivalGames instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        LanguageManager.getManager().start();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
