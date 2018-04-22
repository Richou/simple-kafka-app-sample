package com.heanoria.reminders.kafkasample.datastore.repositories;

import com.heanoria.reminders.kafkasample.datastore.entities.AlbumEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<AlbumEntity, String> {
}
