package me.cmua.client;

import me.cmua.Cmua;
import me.cmua.ManagerLoader;
import me.cmua.api.concurrent.executor.SimpleExecutor;
import me.cmua.api.event.eventbus.EventHandler;
import me.cmua.api.event.eventbus.EventPriority;
import me.cmua.event.world.TickEvent;
import me.cmua.utils.MinecraftUtil;

public class ThreadManager {
    // 任务调度器
    private final SimpleExecutor executor;

    public ThreadManager() {
        Cmua.EVENT_BUS.subscribe(this);

        executor = new SimpleExecutor("SimpleExecutor");
        executor.start();
    }

    public SimpleExecutor getExecutor() {
        return executor;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(TickEvent event) {
        if (event.isPre()) {
            if (MinecraftUtil.playerOrWorldIsNull()) {
                return;
            }

            // 调用模块的onUpdate
            ManagerLoader.moduleManager.updateModules();
        }
    }

}
