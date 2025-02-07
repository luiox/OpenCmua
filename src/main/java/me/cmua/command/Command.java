package me.cmua.command;

import me.cmua.api.command.AbstractCommand;
import me.cmua.utils.ChatUtil;
import me.cmua.utils.Wrapper;

public abstract class Command extends AbstractCommand implements Wrapper {

    public void sendUsage() {
        ChatUtil.sendChatMessage("§fusage: §r" + getName() + " " + getSyntax());
    }
}
