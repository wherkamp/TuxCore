package me.kingtux.tuxcore.discord;

import me.kingtux.tuxcore.settings.SettingChecker;

public class DiscordSettingChecker implements SettingChecker {
    public static final DiscordSettingChecker DISCORD_SETTING_CHECKER = new DiscordSettingChecker();

    @Override
    public boolean isValid(String value) {
        return DiscordUtils.isValidLong(value);
    }
}
