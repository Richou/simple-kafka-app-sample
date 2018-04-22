package com.heanoria.reminders.kafkasample.consumer.internal;

import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicsProperties;
import com.heanoria.reminders.kafkasample.consumer.datas.ConsumersTopicContext;
import com.heanoria.reminders.kafkasample.consumer.factories.TopicMessageHandlerFactory;

public class GroupConsumers {

	private final TopicsProperties topicsProperties;
	private final ConsumerProperties consumerProperties;
	private final BrokerProperties brokerProperties;
	private final TopicMessageHandlerFactory topicMessageHandlerFactory;

	public GroupConsumers(TopicsProperties topicsProperties, ConsumerProperties consumerProperties, BrokerProperties brokerProperties, TopicMessageHandlerFactory topicMessageHandlerFactory) {
		this.topicsProperties = topicsProperties;
		this.consumerProperties = consumerProperties;
		this.brokerProperties = brokerProperties;
        this.topicMessageHandlerFactory = topicMessageHandlerFactory;
    }

	public void lauchConsumers() {
		this.topicsProperties.getTopics().stream()
				.map(topicProperties -> new ConsumersTopic(ConsumersTopicContext.builder()
                        .brokerProperties(brokerProperties)
                        .consumerProperties(consumerProperties)
                        .topicProperties(topicProperties)
                        .topicMessageHandlerFactory(topicMessageHandlerFactory).build()))
                .forEach(ConsumersTopic::subscribes);
	}
}
