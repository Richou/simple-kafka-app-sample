package com.heanoria.reminders.kafkasample.producer.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class KafkaProducerConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerConfiguration.class);

	@Scheduled(fixedRate = 100000)
	public void keepAlive() {
		logger.info("I'm alive !");
	}
}
