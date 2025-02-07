package me.cmua.asm.mixin.client;

import me.cmua.Cmua;
import me.cmua.api.event.EventStage;
import me.cmua.client.FontManager;
import me.cmua.event.world.TickEvent;
import me.cmua.gui.clickgui.ClickGuiScreen;
import me.cmua.gui.imgui.ImguiLoader;
import me.cmua.utils.MinecraftUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Shadow
    @Final
    private Window window;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void initImGui(RunArgs args, CallbackInfo ci) {
        ImguiLoader.create(window.getHandle());
    }

    @Inject(method = "close", at = @At("RETURN"))
    public void closeImGui(CallbackInfo ci) {
        if (MinecraftUtil.getCurrentScreen() instanceof ClickGuiScreen) {
            ImguiLoader.dispose();
        }
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    void postWindowInit(RunArgs args, CallbackInfo ci) {
        try {
            FontManager.initFont();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Inject(at = @At("HEAD"), method = "tick()V")
    public void tickHead(CallbackInfo info) {
        Cmua.EVENT_BUS.post(new TickEvent(EventStage.Pre));
    }

    @Inject(at = @At("TAIL"), method = "tick()V")
    public void tickTail(CallbackInfo info) {
        Cmua.EVENT_BUS.post(new TickEvent(EventStage.Post));
    }

}