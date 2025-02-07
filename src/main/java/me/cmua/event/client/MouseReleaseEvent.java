package me.cmua.event.client;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;

public class MouseReleaseEvent extends Event {
    private int key;

    public MouseReleaseEvent(EventStage stage, int key) {
        super(stage);
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
