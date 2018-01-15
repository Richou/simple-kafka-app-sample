package com.heanoria.reminders.kafkasample.consumer.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "messaging.consumer")
public class ConsumerProperties {

	private Integer autoCommitIntervalMs;
	private Integer sessionTimeoutMs;
	private String offsetReset;
	private Integer maxPollIntervalMs;

	public Integer getAutoCommitIntervalMs() {
		return autoCommitIntervalMs;
	}

	public void setAutoCommitIntervalMs(Integer autoCommitIntervalMs) {
		this.autoCommitIntervalMs = autoCommitIntervalMs;
	}

	public Integer getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(Integer sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public String getOffsetReset() {
		return offsetReset;
	}

	public void setOffsetReset(String offsetReset) {
		this.offsetReset = offsetReset;
	}

	public Integer getMaxPollIntervalMs() {
		return maxPollIntervalMs;
	}

	public void setMaxPollIntervalMs(Integer maxPollIntervalMs) {
		this.maxPollIntervalMs = maxPollIntervalMs;
	}
}
