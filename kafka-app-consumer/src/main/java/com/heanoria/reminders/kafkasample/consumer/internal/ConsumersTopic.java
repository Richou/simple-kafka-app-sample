package com.heanoria.reminders.kafkasample.consumer.internal;

import java.util.ArrayList;
import java.util.List;

import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicProperties;

public class ConsumersTopic {

	private final ConsumerProperties consumerProperties;
	private final BrokerProperties brokerProperties;
	private final List<RxSimpleConsumer> consumers;
	private final TopicProperties topicProperties;

	public ConsumersTopic(ConsumerProperties consumerProperties, BrokerProperties brokerProperties, TopicProperties topicProperties) {
		this.consumerProperties = consumerProperties;
		this.brokerProperties = brokerProperties;
		this.topicProperties = topicProperties;
		this.consumers = new ArrayList<>();
		createRxConsumer();
	}

	private void createRxConsumer() {
		for (int index = 0; index < topicProperties.getConsumerNumber(); index ++) {
			this.consumers.add(new RxSimpleConsumer());
		}
	}
}
