package com.heanoria.reminders.kafkasample.consumer.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UnimplementedObserver implements Observer<Void> {
	@Override
	public void onSubscribe(Disposable disposable) {

	}

	@Override
	public void onNext(Void aVoid) {

	}

	@Override
	public void onError(Throwable throwable) {

	}

	@Override
	public void onComplete() {

	}
}
