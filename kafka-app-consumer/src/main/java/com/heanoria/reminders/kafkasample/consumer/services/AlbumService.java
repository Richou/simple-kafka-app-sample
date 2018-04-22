package com.heanoria.reminders.kafkasample.consumer.services;

import com.heanoria.reminders.kafkasample.models.Album;

public interface AlbumService {

    void saveAlbum(Album album);
}
