package com.moing.backend.domain.member.domain.repository;

import com.moing.backend.domain.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

    Optional<Member> findBySocialId(String socialId);

    Optional<Member> findByEmail(String email);

}
