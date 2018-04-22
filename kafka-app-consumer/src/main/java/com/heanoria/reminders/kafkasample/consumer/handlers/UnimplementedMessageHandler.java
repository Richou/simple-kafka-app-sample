package com.heanoria.reminders.kafkasample.consumer.handlers;

import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;

public class UnimplementedMessageHandler implements MessageHandler {
    @Override
    public void onMessageReceivedHandle(MessageContainer messageContainer) {
        // Nothing to do
    }
}
