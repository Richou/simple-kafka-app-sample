package com.heanoria.reminders.kafkasample.consumer.handlers;

import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;
import com.heanoria.reminders.kafkasample.consumer.services.AlbumService;
import com.heanoria.reminders.kafkasample.models.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlbumMessageHandler implements MessageHandler<Album> {

    private final static Logger logger = LoggerFactory.getLogger(AlbumMessageHandler.class);

    private final AlbumService albumService;

    public AlbumMessageHandler(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public void onMessageReceivedHandle(MessageContainer<Album> messageContainer) {
        logger.info("Message received : {}", messageContainer.getValue());
        this.albumService.saveAlbum(messageContainer.getValue());
    }
}
