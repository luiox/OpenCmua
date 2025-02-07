package me.cmua.event.client;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;

public class LogoutEvent extends Event {
    public LogoutEvent(EventStage stage) {
        super(stage);
    }
}