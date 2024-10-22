package com.moing.backend.domain.teamScore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.teamScore.domain.entity.TeamScore;

public interface TeamScoreRepository extends JpaRepository<TeamScore, Long>, TeamScoreCustomRepository {
}
