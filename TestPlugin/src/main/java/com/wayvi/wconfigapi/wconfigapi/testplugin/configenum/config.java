package com.wayvi.wconfigapi.wconfigapi.testplugin.configenum;


import com.wayvi.wconfigapi.wconfigapi.ConfigKey;

public enum config implements ConfigKey<Object> {

    VERSION("version", "1.0.2.4"),
    DELAY("delay", 3),
    FLY_DECREMENT_DISABLED("fly-decrement-disabled-by-static", false);

    private final String path;
    private final Object defaultValue;

    config(String path, Object defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getPath() { return path; }

    @Override
    public Object getDefaultValue() { return defaultValue; }
}