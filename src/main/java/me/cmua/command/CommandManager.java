package me.cmua.command;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.cmua.api.command.DefaultCommandManager;
import me.cmua.api.config.IConfig;
import me.cmua.command.impl.FriendCommand;
import me.cmua.command.impl.LoadCommand;
import me.cmua.command.impl.PrefixCommand;
import me.cmua.command.impl.SaveCommand;
import me.cmua.utils.Wrapper;

public class CommandManager extends DefaultCommandManager implements Wrapper, IConfig {
    public static final String syncCode = "§)";
    private String defaultPrefix = ".";

    public CommandManager() {
        registerCommand(new PrefixCommand());
        registerCommand(new FriendCommand());
        registerCommand(new LoadCommand());
        registerCommand(new SaveCommand());

        setPrefix(defaultPrefix);
    }

    @Override
    public String getConfigName() {
        return "Command";
    }

    @Override
    public JsonElement toConfig() {
        var config = new JsonObject();
        config.addProperty("prefix", getPrefix());
        return config;
    }

    @Override
    public void fromConfig(JsonElement jsonElement) {
        var config = jsonElement.getAsJsonObject();
        for (var entry : config.getAsJsonObject().entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            if (key.equals("prefix")) {
                setPrefix(value.getAsString());
            }
        }
    }
}
