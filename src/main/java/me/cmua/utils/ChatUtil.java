package me.cmua.utils;

import me.cmua.Cmua;
import me.cmua.command.CommandManager;
import net.minecraft.text.Text;

public class ChatUtil implements Wrapper {

    public static void sendRawChatMessage(String str) {
        if (MinecraftUtil.playerOrWorldIsNull()) {
            return;
        }
        mc.inGameHud.getChatHud().addMessage(Text.of(str));
    }

    public static void sendChatMessage(String message) {
        if (MinecraftUtil.playerOrWorldIsNull()) return;
        mc.inGameHud.getChatHud().addMessage(Text.of(CommandManager.syncCode + "§r" + Cmua.MOD_NAME + "§f " + message));
    }
}
