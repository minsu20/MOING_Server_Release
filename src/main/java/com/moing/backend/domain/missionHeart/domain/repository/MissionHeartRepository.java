package com.moing.backend.domain.missionHeart.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.missionHeart.domain.entity.MissionHeart;

public interface MissionHeartRepository extends JpaRepository<MissionHeart, Long>, MissionHeartCustomRepository {
}
