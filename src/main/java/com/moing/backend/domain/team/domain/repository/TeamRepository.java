package com.moing.backend.domain.team.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.team.domain.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamCustomRepository {
}
