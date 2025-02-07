package me.cmua.module.client;

import me.cmua.ManagerLoader;
import me.cmua.api.event.eventbus.EventHandler;
import me.cmua.event.render.Render2DEvent;
import me.cmua.event.render.Render3DEvent;
import me.cmua.gui.clickgui.ClickGuiScreen;
import me.cmua.module.Module;
import me.cmua.module.ModuleCategory;
import me.cmua.module.ModuleInfo;
import me.cmua.utils.MinecraftUtil;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

@ModuleInfo(name = "Debug", description = "For dev debug", category = ModuleCategory.Client)
public class Debug extends Module {

    private static Debug INSTANCE;

    public static Debug getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Debug();
        }
        return INSTANCE;
    }


    @Override
    public void onEnable() {
        if (MinecraftUtil.playerOrWorldIsNull()) {
            disable();
            return;
        }
        mc.setScreen(ClickGuiScreen.getInstance());
    }

    @Override
    public void onUpdate() {
        // 测试一些需要大量计算的情况，比如水晶计算，模拟一下，
        // 不直接放在onUpdate，而是利用SimpleExecutor提交任务的形式来做
        ManagerLoader.threadManager.getExecutor().submit(() -> {
            if (MinecraftUtil.playerOrWorldIsNull()) {
                return;
            }
            // 获取所有的玩家实体
            List<PlayerEntity> targets = new ArrayList<>();
            var self = MinecraftUtil.getPlayer();
            for (var player : MinecraftUtil.getWorld().getPlayers()) {
                // 忽略距离太远的
                if (self.getPos().distanceTo(player.getPos()) > 6) continue;
                // 忽略friend
                if (ManagerLoader.friendManager.isFriend(player.getName().getString())) continue;
                // 忽略自己
                if (player.getName().getString().equals(self.getName().getString())) continue;
                // 加入目标列表
                targets.add(player);
            }

            // 做更多的计算
            // ...
        });

        if (!(mc.currentScreen instanceof ClickGuiScreen)) {
            disable();
        }
    }

    @Override
    public void onDisable() {
        if (mc.currentScreen instanceof ClickGuiScreen) {
            mc.setScreen(null);
        }
    }

    @EventHandler
    public void onRender2DEvent(Render2DEvent event) {
        // 渲染2d
    }

    @EventHandler
    public void onRender3DEvent(Render3DEvent event) {
        // 渲染3d
    }
}