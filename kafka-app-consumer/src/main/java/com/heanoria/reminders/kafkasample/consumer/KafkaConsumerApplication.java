package com.heanoria.reminders.kafkasample.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerApplication {

	private final static Logger logger = LoggerFactory.getLogger(KafkaConsumerApplication.class);

	public static void main(String ... args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
		logger.info("=======================================================");
		logger.info("=   Kafka Consumer Application started");
		logger.info("=======================================================");
	}
}
