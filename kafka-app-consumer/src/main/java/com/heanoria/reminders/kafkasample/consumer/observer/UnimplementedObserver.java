package com.heanoria.reminders.kafkasample.consumer.observer;

import com.heanoria.reminders.kafkasample.consumer.datas.MessageContainer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UnimplementedObserver implements Observer<MessageContainer> {
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
}
