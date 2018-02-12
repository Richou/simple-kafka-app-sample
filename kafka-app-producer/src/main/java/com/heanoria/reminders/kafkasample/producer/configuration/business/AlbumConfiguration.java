package com.heanoria.reminders.kafkasample.producer.configuration.business;

import com.heanoria.reminders.kafkasample.producer.facades.SendAlbumFacade;
import com.heanoria.reminders.kafkasample.producer.services.ProducerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlbumConfiguration {

    @Bean
    public SendAlbumFacade sendAlbumFacade(ProducerService producerService) {
        return new SendAlbumFacade(producerService);
    }
}
