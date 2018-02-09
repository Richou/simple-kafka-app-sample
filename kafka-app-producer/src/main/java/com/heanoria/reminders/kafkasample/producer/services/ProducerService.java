package com.heanoria.reminders.kafkasample.producer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import com.heanoria.reminders.kafkasample.models.Music;

public class ProducerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

	private final KafkaTemplate<String, Music> kafkaTemplate;
	private final String topic;

	public ProducerService(String topic, KafkaTemplate<String, Music> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(Music payload) {
		LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
		kafkaTemplate.send(this.topic, payload);
	}
}
