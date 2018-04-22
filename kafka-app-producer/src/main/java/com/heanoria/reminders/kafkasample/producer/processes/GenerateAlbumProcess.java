package com.heanoria.reminders.kafkasample.producer.processes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heanoria.reminders.kafkasample.models.Album;
import com.heanoria.reminders.kafkasample.models.Albums;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

public class GenerateAlbumProcess {

    private static final Logger logger = LoggerFactory.getLogger(GenerateAlbumProcess.class);
    private static final String ASSETS_ALBUMS_JSON_FILENAME = "assets/albums.json";
    private final ObjectMapper objectMapper;
    private final Albums albums;

    public GenerateAlbumProcess() {
        this.objectMapper = new ObjectMapper();
        this.albums = getAlbums();
    }

    public Album getRandomAlbum() {
        Random r = new Random();
        Album randomAlbum = albums.get(r.nextInt(albums.getSize()));
        randomAlbum.setId(ObjectId.get().toString());
        return randomAlbum;
    }

    private Albums getAlbums() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String albums = IOUtils.toString(classLoader.getResourceAsStream(ASSETS_ALBUMS_JSON_FILENAME), Charset.defaultCharset());
            return objectMapper.readValue(albums, Albums.class);
        } catch (IOException e) {
            logger.error("Error", e);
        }
        return null;
    }

}
