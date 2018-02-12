package com.heanoria.reminders.kafkasample.consumer.datas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.heanoria.reminders.kafkasample.consumer.observer.AlbumsTopicObserver;
import com.heanoria.reminders.kafkasample.consumer.observer.UnimplementedObserver;

import io.reactivex.Observer;

public enum TopicEnum {
	MUSICS("musics", AlbumsTopicObserver::new),
	UNKNOWN("unknown", UnimplementedObserver::new)
	;

	private final Supplier<Observer> observerSupplier;

	TopicEnum(String topics, Supplier<Observer> observerSupplier) {
		this.observerSupplier = observerSupplier;
		Indexer.index.put(topics, this);
	}

	public Observer getObserver() {
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
