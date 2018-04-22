package com.heanoria.reminders.kafkasample.consumer.configuration.technical;

import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicsProperties;
import com.heanoria.reminders.kafkasample.consumer.datas.TopicEnum;
import com.heanoria.reminders.kafkasample.consumer.factories.TopicMessageHandlerFactory;
import com.heanoria.reminders.kafkasample.consumer.handlers.AlbumMessageHandler;
import com.heanoria.reminders.kafkasample.consumer.handlers.UnimplementedMessageHandler;
import com.heanoria.reminders.kafkasample.consumer.internal.GroupConsumers;
import com.heanoria.reminders.kafkasample.consumer.services.AlbumService;
import com.heanoria.reminders.kafkasample.consumer.services.AlbumServiceImpl;
import com.heanoria.reminders.kafkasample.datastore.repositories.AlbumRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicsConsumerConfiguration {

    @Bean
    public AlbumService albumService(AlbumRepository albumRepository) {
        return new AlbumServiceImpl(albumRepository);
    }

    @Bean
    public AlbumMessageHandler albumMessageHandler(AlbumService albumService) {
        return new AlbumMessageHandler(albumService);
    }

	@Bean
	public GroupConsumers groupConsumers(TopicsProperties topicsProperties, ConsumerProperties consumerProperties, BrokerProperties brokerProperties, TopicMessageHandlerFactory topicMessageHandlerFactory) {
		return new GroupConsumers(topicsProperties, consumerProperties, brokerProperties, topicMessageHandlerFactory);
	}

	@Bean
    public TopicMessageHandlerFactory topicMessageHandlerFactory(AlbumMessageHandler album) {
        return TopicMessageHandlerFactory.factory()
                .registerMessageHandler(TopicEnum.ALBUMS, album)
                .registerMessageHandler(TopicEnum.UNKNOWN, new UnimplementedMessageHandler());
    }
}
