package com.heanoria.reminders.kafkasample.consumer.configuration.properties;

public class TopicProperties {

	private String names;
	private String group;
	private Integer consumerNumber;

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getConsumerNumber() {
		return consumerNumber;
	}

	public void setConsumerNumber(Integer consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
}
