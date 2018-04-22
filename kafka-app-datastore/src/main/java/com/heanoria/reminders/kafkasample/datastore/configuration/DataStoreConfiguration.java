package com.heanoria.reminders.kafkasample.datastore.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.heanoria.reminders.kafkasample.datastore.repositories")
@EntityScan("com.heanoria.reminders.kafkasample.datastore.entities")
public class DataStoreConfiguration {
}
