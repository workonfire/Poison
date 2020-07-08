package pl.workonfire.minecraft.poison;

import org.bukkit.plugin.java.JavaPlugin;
import pl.workonfire.minecraft.poison.commands.ConsoleHandler;
import pl.workonfire.minecraft.poison.commands.SystemHandler;

public final class Poison extends JavaPlugin {
    private static Poison instance;
    private static final String prefix = "§7[§4§l§oPOISON§7]§r ";
    private static String pluginVersion;

    public static String getPluginVersion() {
        return pluginVersion;
    }

    @Override
    public void onEnable() {
        instance = this;
        pluginVersion = getInstance().getDescription().getVersion();
        getInstance().getCommand("pc").setExecutor(new ConsoleHandler());
        getInstance().getCommand("ps").setExecutor(new SystemHandler());
    }

    public static Poison getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }
}
