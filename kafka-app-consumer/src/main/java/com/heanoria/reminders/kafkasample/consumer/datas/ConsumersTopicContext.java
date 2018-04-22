package com.heanoria.reminders.kafkasample.consumer.datas;

import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicProperties;
import com.heanoria.reminders.kafkasample.consumer.factories.TopicMessageHandlerFactory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsumersTopicContext {

    private final ConsumerProperties consumerProperties;
    private final BrokerProperties brokerProperties;
    private final TopicProperties topicProperties;
    private final TopicMessageHandlerFactory topicMessageHandlerFactory;
}
