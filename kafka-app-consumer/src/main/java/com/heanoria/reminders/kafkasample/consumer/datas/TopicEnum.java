package com.heanoria.reminders.kafkasample.consumer.datas;

import com.heanoria.reminders.kafkasample.consumer.observer.AlbumsTopicObserver;
import com.heanoria.reminders.kafkasample.consumer.observer.TopicObserver;
import com.heanoria.reminders.kafkasample.consumer.observer.UnimplementedObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public enum TopicEnum {
	ALBUMS("albums", AlbumsTopicObserver::new),
	UNKNOWN("unknown", UnimplementedObserver::new)
	;

	private final Supplier<TopicObserver> observerSupplier;

	TopicEnum(String topics, Supplier<TopicObserver> observerSupplier) {
		this.observerSupplier = observerSupplier;
		Indexer.index.put(topics, this);
	}

	public TopicObserver getObserver() {
		return observerSupplier.get();
	}

	public static TopicEnum findByTopics(String topics) {
		if (Indexer.index.containsKey(topics)) return Indexer.index.get(topics);
		return UNKNOWN;
	}

	private static class Indexer {
		private static Map<String, TopicEnum> index = new HashMap<>();
	}
}
