package me.cmua.module;

import me.cmua.utils.ChatUtil;

public class ChatNotifyObserver {

    public static class DefaultChatNotifyObserver implements INotifyObserver {
        @Override
        public void notify(Module module) {
            var id = module.hashCode();
            if (module.isOff()) {
                // module要打开了的消息
                ChatUtil.sendChatMessage("§f" + module.getDisplayName() + " §aEnabled");

            } else {
                // module要关闭了的消息
                ChatUtil.sendChatMessage("§f" + module.getDisplayName() + " §aDisabled");
            }
        }
    }

}
