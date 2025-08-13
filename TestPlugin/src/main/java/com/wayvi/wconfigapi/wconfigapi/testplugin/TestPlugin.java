package com.wayvi.wconfigapi.wconfigapi.testplugin;

import com.wayvi.wconfigapi.wconfigapi.ConfigAPI;
import com.wayvi.wconfigapi.wconfigapi.testplugin.configenum.config;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigAPI<config> configFile = new ConfigAPI<>(this, config.class, "config.yml");
        String version = configFile.get(config.VERSION);
        System.out.println(version);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
