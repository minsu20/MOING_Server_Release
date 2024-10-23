package com.moing.backend.domain.auth.application.service.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.moing.backend.global.utils.FeignClientConfig;

@FeignClient(name = "kakaoClient", url = "https://kapi.kakao.com", configuration = FeignClientConfig.class)
public interface KakaoClient {
	@PostMapping("/v1/user/unlink")
	KakaoUnlinkResponse unlinkUser(@RequestHeader("Authorization") String accessToken);
}
