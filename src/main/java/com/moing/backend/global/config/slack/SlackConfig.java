package com.moing.backend.global.config.slack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.slack.api.Slack;

@Configuration
public class SlackConfig {

	@Bean
	public Slack slackClient() {
		return Slack.getInstance();
	}
}
