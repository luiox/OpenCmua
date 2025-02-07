package me.cmua.event.client;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;
import net.minecraft.client.gui.screen.Screen;

public class OpenScreenEvent extends Event {
    public Screen screen;

    public OpenScreenEvent(Screen screen) {
        super(EventStage.Pre);
        this.screen = screen;
    }
}

