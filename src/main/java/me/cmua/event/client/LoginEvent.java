package me.cmua.event.client;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;

public class LoginEvent extends Event {
    public LoginEvent(EventStage stage) {
        super(stage);
    }
}
