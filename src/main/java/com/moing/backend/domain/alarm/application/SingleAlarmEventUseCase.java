package com.moing.backend.domain.alarm.application;

import java.util.concurrent.CompletableFuture;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.moing.backend.domain.history.application.service.SaveSingleAlarmHistoryUseCase;
import com.moing.backend.global.config.fcm.dto.event.SingleFcmEvent;
import com.moing.backend.global.config.fcm.dto.request.SingleRequest;
import com.moing.backend.global.config.fcm.service.SingleMessageSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SingleAlarmEventUseCase {

	private final SingleMessageSender singleMessageSender;
	private final SaveSingleAlarmHistoryUseCase saveSingleAlarmHistoryUseCase;

	@Async
	@EventListener
	public void onSingleAlarmEvent(SingleFcmEvent event) {
		// Push 알림 전송 작업을 비동기적으로 처리
		CompletableFuture<Void> sendNotificationFuture = CompletableFuture.runAsync(() -> {
			if (event.isAlarmPush() && !event.getMember().isSignOut()) { // 알림 on, 로그아웃 안함
				singleMessageSender.send(
					new SingleRequest(event.getMember().getFcmToken(), event.getTitle(), event.getBody(),
						event.getMember().getMemberId(), event.getIdInfo(), event.getName(), event.getAlarmType(),
						event.getPath()));
			}
		});

		// 알림 기록 저장 작업을 비동기적으로 처리
		CompletableFuture<Void> saveHistoryFuture = CompletableFuture.runAsync(() -> {
			saveSingleAlarmHistoryUseCase.saveAlarmHistory(
				event.getMember().getMemberId(), event.getIdInfo(),
				event.getTitle(), event.getBody(), event.getName(), event.getAlarmType(), event.getPath()
			);
		});

		// 두 작업이 모두 완료될 때까지 대기
		CompletableFuture.allOf(sendNotificationFuture, saveHistoryFuture)
			.exceptionally(ex -> {
				// 예외 처리
				log.error("Error processing single alarm event: " + ex.getMessage());
				return null;
			});
	}
}
