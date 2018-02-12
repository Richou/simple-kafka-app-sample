package com.heanoria.reminders.kafkasample.consumer.deserializers;

import com.heanoria.reminders.kafkasample.models.Album;

public class AlbumDeserializer extends JsonDeserializer<Album> {

    public AlbumDeserializer() {
        super(Album.class);
    }
}
