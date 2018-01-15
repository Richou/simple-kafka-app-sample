package com.heanoria.reminders.kafkasample.consumer.internal;

import java.util.Map;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class RxSimpleConsumer {

	private static final int POLL_TIMEOUT_MS = 1000;
	private final KafkaConsumer<String, String> consumer;

	public RxSimpleConsumer() {
		this.consumer = new KafkaConsumer<>(createConsumerConfig());
	}

	private Map<String, Object> createConsumerConfig() {
		return null;
	}
}
