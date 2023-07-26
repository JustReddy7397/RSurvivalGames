package network.ranked.rsurvivalgames.manager;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

/**
 * @author JustReddy
 */
public class SerializationManager implements Manager {

    @Override
    public void start() {
        register();
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
