package com.heanoria.reminders.kafkasample.consumer.services;

import com.heanoria.reminders.kafkasample.datastore.entities.AlbumEntity;
import com.heanoria.reminders.kafkasample.datastore.repositories.AlbumRepository;
import com.heanoria.reminders.kafkasample.models.Album;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void saveAlbum(Album album) {
        AlbumEntity entity = AlbumEntity.builder().artist(album.getArtist()).title(album.getTitle()).id(album.getId()).build();
        this.albumRepository.save(entity);
    }
}
