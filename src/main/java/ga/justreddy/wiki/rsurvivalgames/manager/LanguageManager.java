package ga.justreddy.wiki.rsurvivalgames.manager;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;
import ga.justreddy.wiki.rsurvivalgames.model.language.Language;
import ga.justreddy.wiki.rsurvivalgames.utils.text.ChatUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JustReddy
 */
public class LanguageManager implements Manager {

    private final Map<String, Language> languages;
    private final File fileLanguages;

    public LanguageManager(RSurvivalGames plugin) {
        this.fileLanguages = new File(plugin.getDataFolder().getAbsolutePath() + "/languages");
        if (!fileLanguages.exists()) {
            fileLanguages.mkdirs();
        }
        this.languages = new HashMap<>();

        File defaultLanguage = new File(fileLanguages.getAbsolutePath() + "/language_en.yml");
        if (!defaultLanguage.exists()) {
            plugin
                    .saveResource("languages/language_en.yml", false);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(defaultLanguage);
        String id = config.getString("settings.id");
        String displayname = config.getString("settings.displayname");
        register(id, config);
        ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &aRegistered language: " + id + " (" + displayname + ")");
    }

    @Override
    public void start() {
        File[] files = fileLanguages.listFiles();
        if (files == null) return;
        for (File file : files) {
            String name = file.getName();
            if (!name.endsWith(".yml")) continue;
            name = name.replaceAll(".yml", "");
            if (name.equalsIgnoreCase("language_en")) continue;
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            String id = config.getString("settings.id");
            String displayname = config.getString("settings.displayname");
            if (id == null) {
                ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &cFailed to load " + name + "! ID is null!");
                continue;
            }

            if (displayname == null) {
                ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &cFailed to load " + name + "! DISPLAYNAME is null!");
                continue;
            }

            if (languages.containsKey(id)) {
                ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &cFailed to load " + name + "! ID already exists!");
                continue;
            }
            register(id, config);
            ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &aRegistered language: " + id + " (" + displayname + ")");
        }

        ChatUtil.sendConsole("&7[&dRSurvivalGames&7] &aLoaded " + languages.size() + " languages!");

    }

    @Override
    public void reload() {

    }

    @Override
    public void stop() {

    }

    private void register(String name, FileConfiguration config) {
        languages.put(name, new Language(config));
    }

    public Language getLanguage(String id) {
        return languages.getOrDefault(id, null);
    }

}
