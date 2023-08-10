package ga.justreddy.wiki.rsurvivalgames.model.level;

import ga.justreddy.wiki.rsurvivalgames.manager.LevelManager;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author JustReddy
 */

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class Level {

    int level;
    int xp;
    List<String> commands;

    public String getFormatted() {
        return ""; // TODO
    }

    boolean canLevelUp() {
        return getExperienceUntil(xp) <= 0;
    }

    public double getExperienceUntil(double current) {
        Level level = LevelManager.getManager().getNextLevel(this.level);
        if (level == null) {
            return 0.0;
        }

        return level.getXp() - current;
    }

}
