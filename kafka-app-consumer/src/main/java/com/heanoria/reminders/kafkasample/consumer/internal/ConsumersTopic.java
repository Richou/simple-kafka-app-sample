package com.heanoria.reminders.kafkasample.consumer.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicProperties;
import com.heanoria.reminders.kafkasample.consumer.datas.TopicEnum;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class ConsumersTopic {

	private final ConsumerProperties consumerProperties;
	private final BrokerProperties brokerProperties;
	private final List<RxSimpleConsumer> consumers;
	private final TopicProperties topicProperties;

	public ConsumersTopic(ConsumerProperties consumerProperties, BrokerProperties brokerProperties, TopicProperties topicProperties) {
		this.consumerProperties = consumerProperties;
		this.brokerProperties = brokerProperties;
		this.topicProperties = topicProperties;
		this.consumers = new ArrayList<>();
		createRxConsumer();
	}

	private void createRxConsumer() {
		for (int index = 0; index < topicProperties.getConsumerNumber(); index ++) {
			this.consumers.add(new RxSimpleConsumer(consumerProperties, brokerProperties, topicProperties));
		}
	}

	public void subscribes() {
		this.consumers.stream().map(RxSimpleConsumer::toObservable).collect(Collectors.toList()).forEach(observable -> observable.subscribe(pickupObserver()));
	}

	private Observer pickupObserver() {
		return TopicEnum.findByTopics(topicProperties.getNames()).getObserver();
	}
}
