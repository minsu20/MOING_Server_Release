package com.moing.backend.domain.block.presentation;

import static com.moing.backend.domain.block.presentation.constant.BlockResponseMessage.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moing.backend.domain.block.application.service.BlockCreateUseCase;
import com.moing.backend.domain.block.application.service.BlockDeleteUseCase;
import com.moing.backend.domain.block.application.service.BlockReadUseCase;
import com.moing.backend.domain.report.application.dto.BlockMemberRes;
import com.moing.backend.global.config.security.dto.User;
import com.moing.backend.global.response.SuccessResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/block")
public class BlockController {

	private final BlockReadUseCase blockReadUseCase;
	private final BlockCreateUseCase blockCreateUseCase;
	private final BlockDeleteUseCase blockDeleteUseCase;

	@PostMapping("/{targetId}")
	public ResponseEntity<SuccessResponse<Long>> createBlock(@AuthenticationPrincipal User user,
		@PathVariable("targetId") Long targetId) {
		return ResponseEntity.ok(SuccessResponse.create(CREATE_BLOCK_SUCCESS.getMessage(),
			this.blockCreateUseCase.createBlock(user.getSocialId(), targetId)));
	}

	@DeleteMapping("/{targetId}")
	public ResponseEntity<SuccessResponse<Long>> deleteBlock(@AuthenticationPrincipal User user,
		@PathVariable("targetId") Long targetId) {
		return ResponseEntity.ok(SuccessResponse.create(DELETE_BLOCK_SUCCESS.getMessage(),
			this.blockDeleteUseCase.deleteBlock(user.getSocialId(), targetId)));
	}

	@GetMapping("")
	public ResponseEntity<SuccessResponse<List<Long>>> getBlocks(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(SuccessResponse.create(GET_BLOCK_SUCCESS.getMessage(),
			this.blockReadUseCase.getMyBlockList(user.getSocialId())));
	}

	@GetMapping("/info")
	public ResponseEntity<SuccessResponse<List<BlockMemberRes>>> getBlockList(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(SuccessResponse.create(GET_BLOCK_SUCCESS.getMessage(),
			this.blockReadUseCase.getMyBlockInfoList(user.getSocialId())));
	}

}
