package com.heanoria.reminders.kafkasample.consumer.handlers;

import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;

public interface MessageHandler<T> {
    void onMessageReceivedHandle(MessageContainer<T> messageContainer);
}
