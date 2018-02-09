package com.heanoria.reminders.kafkasample.consumer.deserializers;

import com.heanoria.reminders.kafkasample.models.Music;

public class MusicDeserializer extends JsonDeserializer<Music> {

    public MusicDeserializer() {
        super(Music.class);
    }
}
