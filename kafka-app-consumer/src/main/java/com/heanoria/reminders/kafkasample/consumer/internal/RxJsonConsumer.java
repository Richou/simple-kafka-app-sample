package com.heanoria.reminders.kafkasample.consumer.internal;

import com.heanoria.reminders.kafkasample.consumer.configuration.properties.BrokerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.ConsumerProperties;
import com.heanoria.reminders.kafkasample.consumer.configuration.properties.TopicProperties;
import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Properties;

public class RxJsonConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RxJsonConsumer.class);

    private final ConsumerProperties consumerProperties;
    private final BrokerProperties brokerProperties;
    private final TopicProperties topicProperties;
    private final KafkaConsumer<String, Serializable> consumer;
    private final int pollTimeoutMs;

    public RxJsonConsumer(ConsumerProperties consumerProperties, BrokerProperties brokerProperties, TopicProperties topicProperties) {
        this.consumerProperties = consumerProperties;
        this.brokerProperties = brokerProperties;
        this.topicProperties = topicProperties;
        this.consumer = new KafkaConsumer<>(createConsumerConfig());
        this.pollTimeoutMs = consumerProperties.getPollTimeoutMs();
    }

    public Observable toObservable() {
        return Observable.create(emitter -> {
            logger.info("Creating Observable on {} topic", topicProperties.getNames());
            this.consumer.subscribe(Arrays.asList(topicProperties.getNames().split(",")));
            Boolean isRunning = true;
            while (isRunning) {
                try {
                    ConsumerRecords<String, Serializable> records = consumer.poll(pollTimeoutMs);
                    if (!records.isEmpty()) logger.info("Received {} message from {} topic(s)", records.count(), topicProperties.getNames());
                    for (ConsumerRecord<String, Serializable> record : records) {
                        if (record.value() != null) {
                            logger.info("Received message, with key {} and offset {} with value {} on topic {}", record.key(), record.offset(), record.value(), record.topic());
                            emitter.onNext(MessageContainer.builder().value(record.value()).buildValidMessage());
                        } else {
                            logger.error("Error value not well formatted");
                            emitter.onNext(MessageContainer.builder().exception(new IllegalArgumentException("Invalid Message")).buildErrorMessage());
                        }
                    }
                    this.consumer.commitAsync();
                } catch (Exception exception) {
                    logger.error("Caught exception Stopping consumer immediately");
                    emitter.onNext(MessageContainer.builder().exception(exception).buildErrorMessage());
                    this.consumer.close();
                    isRunning = false;
                }
            }
        }).subscribeOn(Schedulers.newThread());
    }

    private Properties createConsumerConfig() {
        Properties consumerConfig = new Properties();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerProperties.getHosts());
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, topicProperties.getGroup());
        consumerConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        consumerConfig.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerProperties.getSessionTimeoutMs());
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, topicProperties.getDeserializer());
        consumerConfig.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, consumerProperties.getMaxPollIntervalMs());
        consumerConfig.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerProperties.getMaxPollRecords());
        return consumerConfig;
    }
}
