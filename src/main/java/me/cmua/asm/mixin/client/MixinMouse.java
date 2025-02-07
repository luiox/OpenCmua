package me.cmua.asm.mixin.client;

import me.cmua.Cmua;
import me.cmua.api.event.EventStage;
import me.cmua.event.client.MousePressEvent;
import me.cmua.event.client.MouseReleaseEvent;
import me.cmua.event.client.MouseUpdateEvent;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MixinMouse {
    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void onMouse(long window, int button, int action, int mods, CallbackInfo ci) {
        int key = -(button + 2);
        if (action == 1) {
            Cmua.EVENT_BUS.post(new MousePressEvent(EventStage.Post, key));
        }
        if (action == 0) {
            Cmua.EVENT_BUS.post(new MouseReleaseEvent(EventStage.Post, key));
        }
    }

    @Inject(method = "updateMouse", at = @At("RETURN"))
    private void updateHook(CallbackInfo ci) {
        Cmua.EVENT_BUS.post(new MouseUpdateEvent());
    }
}
