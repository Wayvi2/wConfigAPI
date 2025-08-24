package com.wayvi.wconfigapi.wconfigapi;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigAPI<E extends Enum<E> & ConfigKey<?>> {

    private final File file;
    private FileConfiguration config;
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

    @SuppressWarnings("unchecked")
    public <T> T get(E key) {
        Object defaultValue = key.getDefaultValue();

        if (defaultValue instanceof Boolean) {
            return (T) Boolean.valueOf(config.getBoolean(key.getPath(), (Boolean) defaultValue));
        } else if (defaultValue instanceof Byte) {
            return (T) Byte.valueOf((byte) config.getInt(key.getPath(), ((Byte) defaultValue).intValue()));
        } else if (defaultValue instanceof Short) {
            return (T) Short.valueOf((short) config.getInt(key.getPath(), ((Short) defaultValue).intValue()));
        } else if (defaultValue instanceof Integer) {
            return (T) Integer.valueOf(config.getInt(key.getPath(), (Integer) defaultValue));
        } else if (defaultValue instanceof Long) {
            return (T) Long.valueOf(config.getLong(key.getPath(), (Long) defaultValue));
        } else if (defaultValue instanceof Float) {
            return (T) Float.valueOf((float) config.getDouble(key.getPath(), ((Float) defaultValue).doubleValue()));
        } else if (defaultValue instanceof Double) {
            return (T) Double.valueOf(config.getDouble(key.getPath(), (Double) defaultValue));
        } else if (defaultValue instanceof Character) {
            String str = config.getString(key.getPath(), String.valueOf(defaultValue));
            return (T) Character.valueOf(str.length() > 0 ? str.charAt(0) : (Character) defaultValue);
        } else if (defaultValue instanceof String) {
            return (T) config.getString(key.getPath(), (String) defaultValue);
        } else if (defaultValue instanceof java.util.List) {
            return (T) config.getList(key.getPath(), (java.util.List<?>) defaultValue);
        } else if (defaultValue instanceof java.util.Map) {
            return (T) config.getConfigurationSection(key.getPath()).getValues(true);
        } else {
            return (T) config.get(key.getPath(), defaultValue);
        }
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

        this.config = YamlConfiguration.loadConfiguration(file);
        checkAndAddMissing();
    }


    public FileConfiguration getRaw() {
        return config;
    }
}
