package com.heanoria.reminders.kafkasample.consumer.configuration.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "messaging")
public class TopicsProperties {

	private List<TopicProperties> topics;

	public List<TopicProperties> getTopics() {
		return topics;
	}

	public void setTopics(List<TopicProperties> topics) {
		this.topics = topics;
	}
}
