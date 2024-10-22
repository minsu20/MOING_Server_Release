package com.moing.backend.domain.mission.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moing.backend.domain.mission.domain.entity.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long>, MissionCustomRepository {
}
