package network.ranked.rsurvivalgames.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author JustReddy
 */
public class NumberUtil {

    public static double randomDouble(double min, double max) {
        return min + ThreadLocalRandom.current().nextDouble(Math.abs(max - min + 1));
    }

}
