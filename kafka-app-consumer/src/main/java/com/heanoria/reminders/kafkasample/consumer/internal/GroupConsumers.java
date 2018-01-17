package com.heanoria.reminders.kafkasample.consumer.internal;

import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicsProperties;

public class GroupConsumers {

	private final TopicsProperties topicsProperties;
	private final ConsumerProperties consumerProperties;
	private final BrokerProperties brokerProperties;

	public GroupConsumers(TopicsProperties topicsProperties, ConsumerProperties consumerProperties, BrokerProperties brokerProperties) {
		this.topicsProperties = topicsProperties;
		this.consumerProperties = consumerProperties;
		this.brokerProperties = brokerProperties;
	}

	public void lauchConsumers() {
		this.topicsProperties.getTopics().stream().map(topicProperties -> new ConsumersTopic(consumerProperties, brokerProperties, topicProperties)).forEach(ConsumersTopic::subscribes);
	}
}
