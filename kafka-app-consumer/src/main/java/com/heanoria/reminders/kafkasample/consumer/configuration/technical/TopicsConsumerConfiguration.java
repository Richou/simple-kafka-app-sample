package com.heanoria.reminders.kafkasample.consumer.configuration.technical;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicsProperties;
import com.heanoria.reminders.kafkasample.consumer.internal.GroupConsumers;

@Configuration
public class TopicsConsumerConfiguration {

	@Bean
	public GroupConsumers groupConsumers(TopicsProperties topicsProperties, ConsumerProperties consumerProperties, BrokerProperties brokerProperties) {
		return new GroupConsumers(topicsProperties, consumerProperties, brokerProperties);
	}
}
