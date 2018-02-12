package com.heanoria.reminders.kafkasample.producer.configuration.technical;

import com.heanoria.reminders.kafkasample.producer.facades.SendAlbumFacade;
import com.heanoria.reminders.kafkasample.producer.services.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class KafkaProducerConfiguration {

    private final SendAlbumFacade sendAlbumFacade;
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerConfiguration.class);

    public KafkaProducerConfiguration(SendAlbumFacade sendAlbumFacade) {
        this.sendAlbumFacade = sendAlbumFacade;
    }


    @PostConstruct
	public void launchMessageSending() {
		sendAlbumFacade.sendMessages();
	}
}
