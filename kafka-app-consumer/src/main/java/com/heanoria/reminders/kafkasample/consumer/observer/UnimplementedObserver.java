package com.heanoria.reminders.kafkasample.consumer.observer;

import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;
import com.heanoria.reminders.kafkasample.consumer.handlers.UnimplementedMessageHandler;
import io.reactivex.disposables.Disposable;

public class UnimplementedObserver implements TopicObserver<UnimplementedMessageHandler> {
	@Override
	public void onSubscribe(Disposable disposable) {

	}

	@Override
	public void onNext(MessageContainer aVoid) {

	}

	@Override
	public void onError(Throwable throwable) {

	}

	@Override
	public void onComplete() {

	}

	@Override
	public void registerMessageHandler(UnimplementedMessageHandler messageHandler) {

	}
}
