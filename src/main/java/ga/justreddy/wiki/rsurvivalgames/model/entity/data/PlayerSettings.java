package ga.justreddy.wiki.rsurvivalgames.model.entity.data;

import ga.justreddy.wiki.rsurvivalgames.manager.LanguageManager;
import ga.justreddy.wiki.rsurvivalgames.model.language.Language;
import lombok.Setter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * @author JustReddy
 */
@Setter
public class PlayerSettings implements ConfigurationSerializable {

    String language;

    public Language getLanguage() {
        Language l = LanguageManager.getManager().getLanguage(language);
        if (l == null) {
            l = LanguageManager.getManager().getLanguage("en");
            setLanguage("en");
        }
        return l;
    }

    @Override
    public Map<String, Object> serialize() {
        // TODO
        return null;
    }
}
