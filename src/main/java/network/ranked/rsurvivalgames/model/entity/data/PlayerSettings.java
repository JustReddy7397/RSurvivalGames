package network.ranked.rsurvivalgames.model.entity.data;

import lombok.Getter;
import lombok.Setter;
import network.ranked.rsurvivalgames.manager.LanguageManager;
import network.ranked.rsurvivalgames.model.language.Language;

/**
 * @author JustReddy
 */
@Setter
public class PlayerSettings {

    String language;

    public Language getLanguage() {
        Language l = LanguageManager.getManager().getLanguage(language);
        if (l == null) {
            l = LanguageManager.getManager().getLanguage("en");
            setLanguage("en");
        }
        return l;
    }
}
