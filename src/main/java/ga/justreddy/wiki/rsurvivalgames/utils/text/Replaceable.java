package ga.justreddy.wiki.rsurvivalgames.utils.text;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author JustReddy
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class Replaceable {

    String key;
    String value;

}
