package com.heanoria.reminders.kafkasample.consumer.internal;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicProperties;
import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RxSimpleConsumer {

	private static final Logger logger = LoggerFactory.getLogger(RxSimpleConsumer.class);

	private static final int POLL_TIMEOUT_MS = 1000;
	private final ConsumerProperties consumerProperties;
	private final BrokerProperties brokerProperties;
	private final TopicProperties topicProperties;
	private final KafkaConsumer<String, String> consumer;

	public RxSimpleConsumer(ConsumerProperties consumerProperties, BrokerProperties brokerProperties, TopicProperties topicProperties) {
		this.consumerProperties = consumerProperties;
		this.brokerProperties = brokerProperties;
		this.topicProperties = topicProperties;
		this.consumer = new KafkaConsumer<>(createConsumerConfig());
	}

	public Observable toObservable() {
		return Observable.create(emitter -> {
			this.consumer.subscribe(Arrays.asList(topicProperties.getNames().split(",")));
			while (true) {
				try {
					ConsumerRecords<String, String> records = consumer.poll(POLL_TIMEOUT_MS);
					for (ConsumerRecord<String, String> record : records) {
						if (record.value() != null) {
							logger.debug("Received message, with key {} and offset {} with value {} on topic {}", record.key(), record.offset(), record.value(), record.topic());
							emitter.onNext(MessageContainer.builder().value(record.value()).buildValidMessage());
						} else {
							emitter.onNext(MessageContainer.builder().exception(new IllegalArgumentException("Invalid Message")).buildErrorMessage());
						}
					}
				} catch(Exception exception) {
					emitter.onNext(MessageContainer.builder().exception(exception).buildErrorMessage());
				} finally {
					this.consumer.commitAsync();
				}
			}
		}).subscribeOn(Schedulers.io());
	}

	private Properties createConsumerConfig() {
		Properties consumerConfig = new Properties();
		consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerProperties.getHosts());
		consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, topicProperties.getGroup());
		consumerConfig.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerProperties.getAutoCommitIntervalMs());
		consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerProperties.getOffsetReset());
		consumerConfig.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerProperties.getSessionTimeoutMs());
		consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerConfig.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, consumerProperties.getMaxPollIntervalMs());
		return consumerConfig;
	}
}
