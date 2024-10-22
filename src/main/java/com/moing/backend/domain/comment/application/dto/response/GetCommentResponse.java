package com.moing.backend.domain.comment.application.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class GetCommentResponse {
	private List<CommentBlocks> commentBlocks;
}
