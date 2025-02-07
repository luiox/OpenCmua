package me.cmua.asm.mixin.client;

import me.cmua.Cmua;
import me.cmua.api.event.EventStage;
import me.cmua.event.client.KeyPressEvent;
import me.cmua.event.client.KeyReleaseEvent;
import me.cmua.utils.Wrapper;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Keyboard.class)
public class MixinKeyboard implements Wrapper {

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (action == 1) {
            var event = new KeyPressEvent(EventStage.Post, key);
            Cmua.EVENT_BUS.post(event);
        }
        if (action == 0) {
            var event = new KeyReleaseEvent(EventStage.Post, key);
            Cmua.EVENT_BUS.post(event);
        }
    }
}
