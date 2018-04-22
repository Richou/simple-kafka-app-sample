package com.heanoria.reminders.kafkasample.consumer.factories;

import com.heanoria.reminders.kafkasample.consumer.datas.TopicEnum;
import com.heanoria.reminders.kafkasample.consumer.handlers.MessageHandler;

import java.util.HashMap;
import java.util.Map;

public class TopicMessageHandlerFactory {

    private final Map<TopicEnum, MessageHandler> messageHandlers;

    private TopicMessageHandlerFactory() {
        this.messageHandlers = new HashMap<>();
    }

    public static TopicMessageHandlerFactory factory() {
        return new TopicMessageHandlerFactory();
    }

    public TopicMessageHandlerFactory registerMessageHandler(TopicEnum forTopic, MessageHandler messageHandler) {
        this.messageHandlers.put(forTopic, messageHandler);
        return this;
    }

    public MessageHandler getMessageHandlerByTopic(TopicEnum topic) {
        return this.messageHandlers.get(topic);
    }
}
