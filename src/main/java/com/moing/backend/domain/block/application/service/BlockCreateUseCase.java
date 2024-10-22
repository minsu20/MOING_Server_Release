package com.moing.backend.domain.block.application.service;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.block.application.mapper.BlockMapper;
import com.moing.backend.domain.block.domain.service.BlockSaveService;
import com.moing.backend.domain.member.domain.service.MemberGetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlockCreateUseCase {

	private final MemberGetService memberGetService;
	private final BlockSaveService blockSaveService;

	/**
	 * 차단 하기
	 */

	public Long createBlock(String socialId, Long targetId) {
		Long memberId = memberGetService.getMemberBySocialId(socialId).getMemberId();

		blockSaveService.save(BlockMapper.mapToBlock(memberId, targetId));

		return targetId;
	}

}
