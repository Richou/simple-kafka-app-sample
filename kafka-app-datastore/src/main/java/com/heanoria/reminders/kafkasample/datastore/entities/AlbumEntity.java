package com.heanoria.reminders.kafkasample.datastore.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "albums")
public class AlbumEntity {
    @Id
    private String id;
    private String title;
    private String artist;
}
