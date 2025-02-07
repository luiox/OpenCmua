package me.cmua.asm.mixin.network;

import me.cmua.Cmua;
import me.cmua.ManagerLoader;
import me.cmua.event.chat.SendMessageEvent;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ClientPlayNetworkHandler.class)
public abstract class MixinClientPlayNetworkHandler {
    @Unique
    private boolean ignore;

    @Shadow
    public abstract void sendChatMessage(String content);

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo ci) {
        if (ignore) return;
        if (ManagerLoader.commandManager.onCommandIn(message)) {
            ci.cancel();
        } else {
            SendMessageEvent event = new SendMessageEvent(message);
            Cmua.EVENT_BUS.post(event);
            if (event.isCancelled()) {
                ci.cancel();
            } else if (!event.message.equals(event.defaultMessage)) {
                ignore = true;
                sendChatMessage(event.message);
                ignore = false;
                ci.cancel();
            }
        }
    }
}
