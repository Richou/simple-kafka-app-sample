package com.heanoria.reminders.kafkasample.consumer.observer;

import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;
import com.heanoria.reminders.kafkasample.consumer.handlers.AlbumMessageHandler;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlbumsTopicObserver implements TopicObserver<AlbumMessageHandler> {

    private static final Logger logger = LoggerFactory.getLogger(AlbumsTopicObserver.class);

    private AlbumMessageHandler albumMessageHandler;


    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onNext(MessageContainer messageContainer) {
        if (messageContainer.isError()) {
            logger.error("Error received", messageContainer.getException());
        } else {
            logger.info("Received {}", messageContainer.getValue());
            if (this.albumMessageHandler != null) this.albumMessageHandler.onMessageReceivedHandle(messageContainer);
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void registerMessageHandler(AlbumMessageHandler messageHandler) {
        this.albumMessageHandler = messageHandler;
    }
}
