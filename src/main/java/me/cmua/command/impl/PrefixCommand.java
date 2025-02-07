package me.cmua.command.impl;

import me.cmua.ManagerLoader;
import me.cmua.api.command.CommandInfo;
import me.cmua.command.Command;
import me.cmua.utils.ChatUtil;

import java.util.List;

@CommandInfo(name = "prefix", description = "Change prefix", syntax = "prefix [prefix]")
public class PrefixCommand extends Command {
    @Override
    public void onCommand(String[] parameters) {
        if (parameters.length == 0) {
            sendUsage();
            return;
        }
        if (parameters[0].startsWith("/")) {
            ChatUtil.sendChatMessage("§fPlease specify a valid §bprefix.");
            return;
        }
        ManagerLoader.commandManager.setPrefix(parameters[0]);
        ChatUtil.sendChatMessage("§bPrefix §fset to §e" + parameters[0]);
    }

    @Override
    public String[] getAutocorrect(int count, List<String> seperated) {
        return null;
    }
}
