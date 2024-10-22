package com.moing.backend.domain.fire.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.fire.domain.entity.Fire;

public interface FireRepository extends JpaRepository<Fire, Long>, FireCustomRepository {
}
