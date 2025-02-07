package me.cmua.event.client;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;

public class MousePressEvent extends Event {
    private int key;

    public MousePressEvent(EventStage stage, int key) {
        super(stage);
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}