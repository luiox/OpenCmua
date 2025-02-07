package me.cmua.command.impl;

import me.cmua.ManagerLoader;
import me.cmua.api.command.CommandInfo;
import me.cmua.command.Command;
import me.cmua.utils.ChatUtil;

import java.util.List;

@CommandInfo(name = "load", description = "Load a config", syntax = "[config]")
public class LoadCommand extends Command {

    @Override
    public void onCommand(String[] parameters) {
        if (parameters.length == 0) {
            sendUsage();
            return;
        }
        ChatUtil.sendChatMessage("§fLoading..");
//        ConfigManager.options = ConfigUtil.getFile(parameters[0] + ".json");
//        ManagerLoader.configManager = new ConfigManager();
//        ManagerLoader.commandManager.setPrefix(ManagerLoader.configManager.getString("prefix", ManagerLoader.commandManager.getPrefix()));
//        ManagerLoader.configManager.loadSettings();
//        ConfigManager.options = ConfigUtil.getFile("options.txt");
//        ManagerLoader.save();
//        var res = ManagerLoader.configManager.loadConfig(parameters[0]);
//        if (res) {
//            ChatUtil.sendChatMessage("§aLoaded " + parameters[0] + " successfully!");
//        } else {
//            ChatUtil.sendChatMessage("§cFailed to load " + parameters[0] + "!");
//        }
        ManagerLoader.configManager.setConfigName(ManagerLoader.configManager.getConfigName(), parameters[0]);
        ManagerLoader.configManager.readConfig();
        ChatUtil.sendChatMessage("§aLoaded " + parameters[0] + " successfully!");
    }

    @Override
    public String[] getAutocorrect(int count, List<String> seperated) {
        return null;
    }
}
