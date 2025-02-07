package me.cmua.event.chat;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;

public class SendMessageEvent extends Event {

    public final String defaultMessage;
    public String message;

    public SendMessageEvent(String message) {
        super(EventStage.Pre);
        this.defaultMessage = message;
        this.message = message;
    }
}