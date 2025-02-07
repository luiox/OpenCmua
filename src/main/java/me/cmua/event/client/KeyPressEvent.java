package me.cmua.event.client;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;

public class KeyPressEvent extends Event {
    private int key;

    public KeyPressEvent(EventStage stage, int key) {
        super(stage);
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
