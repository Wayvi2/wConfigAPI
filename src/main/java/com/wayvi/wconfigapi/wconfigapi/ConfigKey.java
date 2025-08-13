package com.wayvi.wconfigapi.wconfigapi;

public interface ConfigKey<T> {
    String getPath();
    T getDefaultValue();
}
