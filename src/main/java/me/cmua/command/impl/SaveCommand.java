package me.cmua.command.impl;

import me.cmua.ManagerLoader;
import me.cmua.api.command.CommandInfo;
import me.cmua.command.Command;
import me.cmua.utils.ChatUtil;

import java.util.List;

@CommandInfo(name = "save", description = "Save config")
public class SaveCommand extends Command {
    @Override
    public void onCommand(String[] parameters) {
        if (parameters.length == 1) {
            ChatUtil.sendChatMessage("§fSaving config named " + parameters[0]);
            ManagerLoader.configManager.setConfigName(ManagerLoader.configManager.getConfigName(), parameters[0]);
            ManagerLoader.configManager.writeConfig();
        } else {
            ChatUtil.sendChatMessage("§fSaving..");
        }
    }

    @Override
    public String[] getAutocorrect(int count, List<String> seperated) {
        return null;
    }
}
