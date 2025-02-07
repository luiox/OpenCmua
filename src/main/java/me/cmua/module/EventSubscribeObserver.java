package me.cmua.module;

import me.cmua.Cmua;

public class EventSubscribeObserver implements INotifyObserver {
    @Override
    public void notify(Module module) {
        if (module.isOff()) {
            Cmua.EVENT_BUS.subscribe(module);
        } else if (module.isOff()) {
            Cmua.EVENT_BUS.unsubscribe(module);
        }
    }
}
