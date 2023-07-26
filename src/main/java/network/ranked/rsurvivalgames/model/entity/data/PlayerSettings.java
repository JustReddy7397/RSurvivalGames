package network.ranked.rsurvivalgames.model.entity.data;

import lombok.Getter;
import lombok.Setter;
import network.ranked.rsurvivalgames.manager.LanguageManager;
import network.ranked.rsurvivalgames.model.language.Language;
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
