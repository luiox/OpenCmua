package me.cmua.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.Window;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;

public class MinecraftUtil {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    // 私有构造函数，防止被实例化
    private MinecraftUtil() {
    }

    public static MinecraftClient getMinecraft() {
        return mc;
    }

    public static ClientPlayerEntity getPlayer() {
        return mc.player;
    }

    public static Vec3d getPlayerEyePos() {
        return mc.player.getEyePos();
    }

    public static ClientWorld getWorld() {
        return mc.world;
    }

    public static boolean isPlayerInGame() {
        return !playerIsNull() && mc.player.isAlive();
    }

    public static boolean playerIsNull() {
        return mc.player == null;
    }

    public static boolean worldIsNull() {
        return mc.world == null;
    }

    public static boolean playerOrWorldIsNull() {
        return mc.player == null || mc.world == null;
    }

    // 获取运行路径
    public static String getRunPath() {
        return mc.runDirectory.getPath();
    }

    // 获取游戏窗口
    public static Window getGameWindow() {
        return mc.getWindow();
    }

    // 获取当前窗口
    public static Screen getCurrentScreen() {
        return mc.currentScreen;
    }
}
