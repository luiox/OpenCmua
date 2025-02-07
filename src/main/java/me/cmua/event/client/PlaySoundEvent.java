package me.cmua.event.client;

import me.cmua.api.event.Event;
import me.cmua.api.event.EventStage;
import net.minecraft.client.sound.SoundInstance;

public class PlaySoundEvent extends Event {
    public final SoundInstance sound;

    public PlaySoundEvent(SoundInstance soundInstance) {
        super(EventStage.Pre);
        sound = soundInstance;
    }
}
