package com.moing.backend.domain.mypage.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.mypage.domain.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
