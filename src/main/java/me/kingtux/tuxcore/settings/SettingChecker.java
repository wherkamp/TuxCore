package me.kingtux.tuxcore.settings;

@FunctionalInterface
public interface SettingChecker {
    boolean isValid(String value);
}

