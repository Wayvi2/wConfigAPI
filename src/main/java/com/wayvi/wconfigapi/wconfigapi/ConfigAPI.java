package com.wayvi.wconfigapi.wconfigapi;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigAPI<E extends Enum<E> & ConfigKey<?>> {

    private final File file;
    private final FileConfiguration config;
    private final Class<E> enumClass;

    public ConfigAPI(Plugin plugin, Class<E> enumClass, String fileName) {
        this.enumClass = enumClass;
        this.file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }

        this.config = YamlConfiguration.loadConfiguration(file);
        checkAndAddMissing();
    }

    private void checkAndAddMissing() {
        boolean changed = false;

        for (E key : enumClass.getEnumConstants()) {
            if (!config.contains(key.getPath())) {
                config.set(key.getPath(), key.getDefaultValue());
                changed = true;
            }
        }

        if (changed) save();
    }

    public <T> T get(E key) {
        Object value = config.get(key.getPath(), key.getDefaultValue());
        return (T) value;
    }

    public <T> void set(E key, T value) {
        config.set(key.getPath(), value);
        save();
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        FileConfiguration newConfig = YamlConfiguration.loadConfiguration(file);

        config.setDefaults(newConfig);

        for (String key : newConfig.getKeys(true)) {
            config.set(key, newConfig.get(key));
        }

        checkAndAddMissing();
    }


    public FileConfiguration getRaw() {
        return config;
    }
}
