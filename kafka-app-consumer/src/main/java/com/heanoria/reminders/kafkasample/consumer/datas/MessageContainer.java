package com.heanoria.reminders.kafkasample.consumer.datas;

public class MessageContainer<T> {

	private final boolean error;
	private final T value;
	private final Exception exception;

	private MessageContainer(boolean error, T value, Exception exception) {
		this.error = error;
		this.value = value;
		this.exception = exception;
	}

	public boolean isError() {
		return error;
	}

	public T getValue() {
		return value;
	}

	public Exception getException() {
		return exception;
	}

	public static <T> MessageContainerBuilder<T> builder() {
		return new MessageContainerBuilder<>();
	}

	public static final class MessageContainerBuilder<T> {
		private T value;
		private Exception exception;

		private MessageContainerBuilder() {
		}

		public MessageContainerBuilder value(T value) {
			this.value = value;
			return this;
		}

		public MessageContainerBuilder exception(Exception exception) {
			this.exception = exception;
			return this;
		}

		public MessageContainer<T> buildValidMessage() {
			return new MessageContainer<>(false, value, null);
		}

		public MessageContainer<T> buildErrorMessage() {
			return new MessageContainer<>(true, null, exception);
		}
	}
}
