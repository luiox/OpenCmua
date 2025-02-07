package me.cmua;

//import me.cmua.api.imgui.GlfwWindow;

import me.cmua.client.LogManager;
import me.cmua.client.ThreadManager;
import me.cmua.command.CommandManager;
import me.cmua.config.ConfigManager;
import me.cmua.friend.FriendManager;
import me.cmua.gui.imgui.ImguiLoader;
import me.cmua.module.ModuleManager;

import java.lang.invoke.MethodHandles;

public class ManagerLoader {
    // 日志管理器
    public static LogManager logManager;
    // client
    // module管理器
    public static ModuleManager moduleManager;
    // command管理器
    public static CommandManager commandManager;
    // friend管理器
    public static FriendManager friendManager;
    // 线程管理器
    public static ThreadManager threadManager;
    // 配置管理器
    public static ConfigManager configManager;
    // 是否已经加载Mod
    public static boolean loaded = false;

    public static void load() {
        // 加载日志管理器
        logManager = new LogManager(Cmua.MOD_NAME);
        logManager.info("LogManager has been loaded");
        // 检查Mod数据是否已经成功加载
        logManager.info("Check Mod Data Load Status, MOD_NAME: " + Cmua.MOD_NAME + " MOD_VERSION: " + Cmua.MOD_VERSION);
        // 初始化EventBus
        Cmua.EVENT_BUS.registerLambdaFactory((lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        logManager.info("EventBus has been loaded");

        // 线程管理器
        threadManager = new ThreadManager();
        logManager.info("ThreadManager has been loaded");

        // 初始化模块管理器
        moduleManager = ModuleManager.getInstance();
        logManager.info("ModuleManager has been loaded");

        // 命令管理器
        commandManager = new CommandManager();
        logManager.info("CommandManager has been loaded");

        // friend管理器
        friendManager = new FriendManager();
        logManager.info("FriendManager has been loaded");

        // 加载配置
        configManager = new ConfigManager();
        configManager.readConfig();

        logManager.info("Config has been loaded");
        // 设置自动保存配置
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (loaded) {
                configManager.writeConfig();
                ImguiLoader.saveImGuiConfig();
                logManager.info("Auto Save Config.");
            }
        }));
        logManager.info("Auto Save Config has been set");

        logManager.info("[" + Cmua.MOD_NAME + "] loaded");

        loaded = true;
    }
}
