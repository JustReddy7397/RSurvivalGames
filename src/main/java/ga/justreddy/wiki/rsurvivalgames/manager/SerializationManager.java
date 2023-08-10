package ga.justreddy.wiki.rsurvivalgames.manager;

import ga.justreddy.wiki.rsurvivalgames.model.entity.data.PlayerKits;
import ga.justreddy.wiki.rsurvivalgames.model.entity.data.PlayerSettings;
import ga.justreddy.wiki.rsurvivalgames.model.entity.data.PlayerStats;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

/**
 * @author JustReddy
 */
public class SerializationManager implements Manager {

    @Override
    public void start() {
        register(PlayerKits.class, PlayerStats.class, PlayerSettings.class);
    }

    @Override
    public void reload() {

    }

    @Override
    public void stop() {

    }

    @SafeVarargs
    private final void register(Class<? extends ConfigurationSerializable>... ser) {
        for (Class<? extends ConfigurationSerializable> i : ser) {
            ConfigurationSerialization.registerClass(i);
        }
    }

}
