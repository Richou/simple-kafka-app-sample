package com.heanoria.reminders.kafkasample.consumer.observer;

import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;
import com.heanoria.reminders.kafkasample.consumer.handlers.MessageHandler;
import io.reactivex.Observer;

public interface TopicObserver<T extends MessageHandler> extends Observer<MessageContainer> {

    void registerMessageHandler(T messageHandler);
}
