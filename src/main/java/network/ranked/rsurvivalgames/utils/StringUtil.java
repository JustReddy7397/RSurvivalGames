package network.ranked.rsurvivalgames.utils;

/**
 * @author JustReddy
 */
public class StringUtil {

    /**
     * Checks if the string is null
     * @param str The string to check for null
     * @return true if the string is null, false if not
     */
    public static boolean isNull(String str) {
        return str == null;
    }

    /**
     * If the string is null, return the default string that should be given
     * <p> </p>
     * Found out FileConfiguration#getString(String str, String def) exists lol
     * <p>
     * @param str The string to check if its null
     * @param def The default string to use if the string is null
     * @return The string if not null, the default string if null
     */
    public static String getDefault(String str, String def) {
        if (isNull(str)) return def;
        return str;
    }

}
