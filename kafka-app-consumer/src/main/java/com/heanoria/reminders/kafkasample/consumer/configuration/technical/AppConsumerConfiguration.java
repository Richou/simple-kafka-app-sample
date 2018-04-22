package com.heanoria.reminders.kafkasample.consumer.configuration.technical;

import com.heanoria.reminders.kafkasample.consumer.internal.GroupConsumers;
import com.heanoria.reminders.kafkasample.datastore.configuration.DataStoreConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@Configuration
@EnableScheduling
@Import({DataStoreConfiguration.class})
public class AppConsumerConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(AppConsumerConfiguration.class);

	private final GroupConsumers groupConsumers;

	public AppConsumerConfiguration(GroupConsumers groupConsumers) {
		this.groupConsumers = groupConsumers;
	}

	@Scheduled(fixedRate = 1000000)
	public void keepAlive() {
		logger.info("I'm still alive !");
	}

	@PostConstruct
	public void launchConsumers() {
		groupConsumers.lauchConsumers();
	}
}
