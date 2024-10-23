package com.moing.backend.global.config.fcm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.moing.backend.domain.history.application.mapper.AlarmHistoryMapper;
import com.moing.backend.global.config.fcm.dto.request.MultiRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MultiMessageSender implements MessageSender<MultiRequest> {

	@Override
	@Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public void send(MultiRequest request) {

		List<String> fcmTokens = AlarmHistoryMapper.getFcmTokens(request.getMemberIdAndTokens());
		Notification notification = Notification.builder()
			.setTitle(request.getTitle())
			.setBody(request.getBody())
			.build();

		// Android Configuration
		AndroidConfig androidConfig = AndroidConfig.builder()
			.setPriority(AndroidConfig.Priority.HIGH)
			.setNotification(AndroidNotification.builder()
				.setChannelId("FCM_Channel")
				.setTitle(request.getTitle())
				.setBody(request.getBody())
				.build())
			.build();

		// APNs Configuration
		ApnsConfig apnsConfig = ApnsConfig.builder()
			.setAps(Aps.builder()
				.setCategory("YOUR_CATEGORY") // Replace with your category
				.setAlert(ApsAlert.builder()
					.setTitle(request.getTitle())
					.setBody(request.getBody())
					.build())
				.build())
			.build();

		Map<String, String> additionalData = new HashMap<>();
		additionalData.put("path", request.getPath());
		additionalData.put("idInfo", request.getIdInfo());

		MulticastMessage message = MulticastMessage.builder()
			.addAllTokens(fcmTokens)
			.setNotification(notification)
			.setAndroidConfig(androidConfig) // Applying Android configuration
			.setApnsConfig(apnsConfig) // Applying APNs configuration
			.putAllData(additionalData)
			.build();

		CompletableFuture.supplyAsync(() -> {
			try {
				return FirebaseMessaging.getInstance().sendEachForMulticastAsync(message).get();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).exceptionally(throwable -> {
			log.error("Firebase messaging error occurred", throwable);
			return null;
		});
	}

}
