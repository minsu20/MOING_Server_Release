package com.moing.backend.domain.block.application.service;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.block.domain.service.BlockDeleteService;
import com.moing.backend.domain.member.domain.service.MemberGetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlockDeleteUseCase {

	private final MemberGetService memberGetService;
	private final BlockDeleteService blockDeleteService;

	/**
	 * 차단 철회하기
	 */

	public Long deleteBlock(String socialId, Long targetId) {
		Long memberId = memberGetService.getMemberBySocialId(socialId).getMemberId();
		blockDeleteService.delete(memberId, targetId);

		return targetId;
	}

}
