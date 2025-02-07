package me.cmua.asm.mixin.render;

import me.cmua.Cmua;
import me.cmua.api.event.EventStage;
import me.cmua.event.render.Render2DEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V", remap = false, ordinal = 3)}, method = {"render(Lnet/minecraft/client/gui/DrawContext;F)V"})
    private void onRender(DrawContext context, float tickDelta, CallbackInfo ci) {
        Cmua.EVENT_BUS.post(new Render2DEvent(EventStage.Pre, context, tickDelta));
    }
}
