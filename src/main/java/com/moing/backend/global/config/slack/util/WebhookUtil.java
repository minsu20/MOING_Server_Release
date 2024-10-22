package com.moing.backend.global.config.slack.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface WebhookUtil {

	void sendSlackAlertErrorLog(HttpServletRequest request, Exception e);

	void sendSlackTeamCreatedMessage(String teamName, Long leaderId);

	void sendDailyStatsMessage(Map<String, Long> todayStats, Map<String, Long> yesterdayStats);
}
