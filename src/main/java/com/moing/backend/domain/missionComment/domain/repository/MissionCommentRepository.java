package com.moing.backend.domain.missionComment.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.missionComment.domain.entity.MissionComment;

public interface MissionCommentRepository extends JpaRepository<MissionComment, Long>, MissionCommentCustomRepository {

	Optional<MissionComment> findMissionCommentByMissionCommentId(Long missionCommentId);

	void deleteAllMissionCommentsByMissionArchiveId(Long missionArchiveId);

}
