package me.cmua.event.world;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;

public class TickEvent extends Event {
    public TickEvent(EventStage stage) {
        super(stage);
    }
}
