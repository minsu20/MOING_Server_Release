package com.moing.backend.domain.alarm.application;

import java.util.concurrent.CompletableFuture;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.moing.backend.domain.history.application.mapper.AlarmHistoryMapper;
import com.moing.backend.domain.history.application.service.SaveMultiAlarmHistoryUseCase;
import com.moing.backend.global.config.fcm.dto.event.MultiFcmEvent;
import com.moing.backend.global.config.fcm.dto.request.MultiRequest;
import com.moing.backend.global.config.fcm.service.MultiMessageSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MultiAlarmEventUseCase {

	private final MultiMessageSender multiMessageSender;
	private final SaveMultiAlarmHistoryUseCase saveMultiAlarmHistoryUseCase;

	@Async
	@EventListener
	public void onMultiAlarmEvent(MultiFcmEvent event) {
		// Push 알람 전송
		CompletableFuture<Void> pushAlarmFuture = event.getIdAndTokensByPush()
			.filter(idAndTokensByPush -> !idAndTokensByPush.isEmpty())
			.map(idAndTokensByPush -> CompletableFuture.runAsync(() -> {
				multiMessageSender.send(new MultiRequest(idAndTokensByPush, event.getTitle(), event.getBody(),
					event.getIdInfo(), event.getName(), event.getAlarmType(), event.getPath()));
			}))
			.orElse(CompletableFuture.completedFuture(null));

		// 알림 기록 저장
		CompletableFuture<Void> saveHistoryFuture = event.getIdAndTokensBySave()
			.filter(idAndTokensBySave -> !idAndTokensBySave.isEmpty())
			.map(idAndTokensBySave -> CompletableFuture.runAsync(() -> {
				saveMultiAlarmHistoryUseCase.saveAlarmHistories(
					AlarmHistoryMapper.getMemberIds(idAndTokensBySave), event.getIdInfo(),
					event.getTitle(), event.getBody(), event.getName(), event.getAlarmType(), event.getPath());
			}))
			.orElse(CompletableFuture.completedFuture(null));

		// 두 작업이 모두 완료될 때까지 대기
		CompletableFuture.allOf(pushAlarmFuture, saveHistoryFuture)
			.exceptionally(ex -> {
				log.error("Error processing multi-alarm event", ex);
				return null;
			});
	}
}
