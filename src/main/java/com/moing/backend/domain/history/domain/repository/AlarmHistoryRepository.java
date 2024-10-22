package com.moing.backend.domain.history.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.history.domain.entity.AlarmHistory;

public interface AlarmHistoryRepository extends JpaRepository<AlarmHistory, Long>, AlarmHistoryCustomRepository {

	Optional<AlarmHistory> findAlarmHistoryByIdAndReceiverId(Long id, Long receiverId);

	void deleteByCreatedDateBefore(LocalDateTime dateTime);

}
