package com.heanoria.reminders.kafkasample.consumer.internal;

import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicProperties;
import com.heanoria.reminders.kafkasample.consumer.datas.ConsumersTopicContext;
import com.heanoria.reminders.kafkasample.consumer.datas.TopicEnum;
import com.heanoria.reminders.kafkasample.consumer.factories.TopicMessageHandlerFactory;
import com.heanoria.reminders.kafkasample.consumer.observer.TopicObserver;
import io.reactivex.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumersTopic {

	private final static Logger logger = LoggerFactory.getLogger(ConsumersTopic.class);

	private final ConsumerProperties consumerProperties;
	private final BrokerProperties brokerProperties;
	private final TopicProperties topicProperties;
	private final TopicMessageHandlerFactory topicMessageHandlerFactory;
	private final List<RxJsonConsumer> consumers;

	public ConsumersTopic(ConsumersTopicContext consumersTopicContext) {
		this.consumerProperties = consumersTopicContext.getConsumerProperties();
		this.brokerProperties = consumersTopicContext.getBrokerProperties();
		this.topicProperties = consumersTopicContext.getTopicProperties();
		this.topicMessageHandlerFactory = consumersTopicContext.getTopicMessageHandlerFactory();
		this.consumers = new ArrayList<>();
		createRxConsumer();
	}

	private void createRxConsumer() {
	    logger.info("Creating {} consumers", topicProperties.getConsumerNumber());
		for (int index = 0; index < topicProperties.getConsumerNumber(); index ++) {
			this.consumers.add(new RxJsonConsumer(consumerProperties, brokerProperties, topicProperties));
		}
	}

	public void subscribes() {
		this.consumers.stream().map(RxJsonConsumer::toObservable).collect(Collectors.toList()).forEach(observable -> observable.subscribe(pickupObserver()));
	}

	private Observer pickupObserver() {
        TopicEnum byTopics = TopicEnum.findByTopics(topicProperties.getNames());
        TopicObserver topicObserver = byTopics.getObserver();
        topicObserver.registerMessageHandler(topicMessageHandlerFactory.getMessageHandlerByTopic(byTopics));
        return topicObserver;
	}
}
