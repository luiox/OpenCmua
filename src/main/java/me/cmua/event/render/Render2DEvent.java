package me.cmua.event.render;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;
import net.minecraft.client.gui.DrawContext;

public class Render2DEvent extends Event {

    private final float partialTicks;
    private final DrawContext drawContext;

    public Render2DEvent(EventStage stage, DrawContext drawContext, float partialTicks) {
        super(stage);
        this.drawContext = drawContext;
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public DrawContext getDrawContext() {
        return drawContext;
    }

}

