package com.heanoria.reminders.kafkasample.producer.services;

import com.heanoria.reminders.kafkasample.models.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class ProducerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

	private final KafkaTemplate<String, Album> kafkaTemplate;
	private final String topic;

	public ProducerService(String topic, KafkaTemplate<String, Album> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(Album payload) {
		LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
		kafkaTemplate.send(this.topic, payload);
	}
}
