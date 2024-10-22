package com.moing.backend.domain.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.member.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
}
