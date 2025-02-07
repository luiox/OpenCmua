package me.cmua.command.impl;

import me.cmua.ManagerLoader;
import me.cmua.api.command.CommandInfo;
import me.cmua.command.Command;
import me.cmua.utils.ChatUtil;

import java.util.ArrayList;
import java.util.List;

@CommandInfo(name = "friend", description = "Friend management", syntax = "[name/reset/list] | [add/remove] [name]")
public class FriendCommand extends Command {
    @Override
    public void onCommand(String[] parameters) {
        if (parameters.length == 0) {
            sendUsage();
            return;
        }
        switch (parameters[0]) {
            case "reset" -> {
                ManagerLoader.friendManager.friendList.clear();
                ChatUtil.sendChatMessage("§fFriends list got reset");
                return;
            }
            case "list" -> {
                if (ManagerLoader.friendManager.friendList.isEmpty()) {
                    ChatUtil.sendChatMessage("§fFriends list is empty");
                    return;
                }
                StringBuilder friends = new StringBuilder();
                int time = 0;
                boolean first = true;
                boolean start = true;
                for (String name : ManagerLoader.friendManager.friendList) {
                    if (!first) {
                        friends.append(", ");
                    }
                    friends.append(name);
                    first = false;
                    time++;
                    if (time > 3) {
                        ChatUtil.sendChatMessage((start ? "§eFriends §a" : "§a") + friends);
                        friends = new StringBuilder();
                        start = false;
                        first = true;
                        time = 0;
                    }
                }
                if (first) {
                    ChatUtil.sendChatMessage("§a" + friends);
                }
                return;
            }
            case "add" -> {
                if (parameters.length == 2) {
                    ManagerLoader.friendManager.addFriend(parameters[1]);
                    ChatUtil.sendChatMessage("§f" + parameters[1] + (ManagerLoader.friendManager.isFriend(parameters[1]) ? " §ahas been friended" : " §chas been unfriended"));
                    return;
                }
                sendUsage();
                return;
            }
            case "remove" -> {
                if (parameters.length == 2) {
                    ManagerLoader.friendManager.removeFriend(parameters[1]);
                    ChatUtil.sendChatMessage("§f" + parameters[1] + (ManagerLoader.friendManager.isFriend(parameters[1]) ? " §ahas been friended" : " §chas been unfriended"));
                    return;
                }
                sendUsage();
                return;
            }
        }

        if (parameters.length == 1) {
            ChatUtil.sendChatMessage("§f" + parameters[0] + (ManagerLoader.friendManager.isFriend(parameters[0]) ? " §ais friended" : " §cisn't friended"));
            return;
        }

        sendUsage();
    }

    @Override
    public String[] getAutocorrect(int count, List<String> seperated) {
        if (count == 1) {
            String input = seperated.get(seperated.size() - 1).toLowerCase();
            List<String> correct = new ArrayList<>();
            List<String> list = List.of("add", "remove", "list", "reset");
            for (String x : list) {
                if (input.equalsIgnoreCase(ManagerLoader.commandManager.getPrefix() + "friend") || x.toLowerCase().startsWith(input)) {
                    correct.add(x);
                }
            }
            int numCmds = correct.size();
            String[] commands = new String[numCmds];

            int i = 0;
            for (String x : correct) {
                commands[i++] = x;
            }

            return commands;
        }
        return null;
    }
}
