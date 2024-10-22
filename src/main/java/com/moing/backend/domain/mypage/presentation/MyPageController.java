package com.moing.backend.domain.mypage.presentation;

import static com.moing.backend.domain.mypage.presentation.constant.MypageResponseMessage.*;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moing.backend.domain.mypage.application.dto.request.UpdateProfileRequest;
import com.moing.backend.domain.mypage.application.dto.request.WithdrawRequest;
import com.moing.backend.domain.mypage.application.dto.response.GetAlarmResponse;
import com.moing.backend.domain.mypage.application.dto.response.GetMyPageResponse;
import com.moing.backend.domain.mypage.application.dto.response.GetProfileResponse;
import com.moing.backend.domain.mypage.application.service.AlarmUseCase;
import com.moing.backend.domain.mypage.application.service.GetMyPageUseCase;
import com.moing.backend.domain.mypage.application.service.ProfileUseCase;
import com.moing.backend.domain.mypage.application.service.SignOutUseCase;
import com.moing.backend.domain.mypage.application.service.WithdrawUseCase;
import com.moing.backend.global.config.security.dto.User;
import com.moing.backend.global.response.SuccessResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageController {

	private final SignOutUseCase signOutService;
	private final WithdrawUseCase withdrawService;
	private final ProfileUseCase profileUseCase;
	private final AlarmUseCase alarmUseCase;
	private final GetMyPageUseCase getMyPageUseCase;

	/**
	 * 사용자 회원가입 여부
	 * [GET] api/mypage/test
	 * 작성자: 김민수
	 */
	@GetMapping("/test")
	public ResponseEntity<SuccessResponse> test(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(SuccessResponse.create(TEST_SIGNUP_SUCCESS.getMessage()));
	}

	/**
	 * 로그아웃
	 * [POST] api/mypage/signOut
	 * 작성자 : 김민수
	 */
	@PostMapping("/signOut")
	public ResponseEntity<SuccessResponse> signOut(@AuthenticationPrincipal User user) {
		this.signOutService.signOut(user.getSocialId());
		return ResponseEntity.ok(SuccessResponse.create(SIGN_OUT_SUCCESS.getMessage()));
	}

	/**
	 * 회원탈퇴
	 * [DELETE] api/mypage/withdrawal
	 * 작성자 : 김민수
	 */
	@DeleteMapping("/withdrawal/{provider}")
	public ResponseEntity<SuccessResponse> withdraw(@AuthenticationPrincipal User user,
		@PathVariable String provider,
		@Valid @RequestBody WithdrawRequest withdrawRequest) throws IOException {
		this.withdrawService.withdraw(user.getSocialId(), provider, withdrawRequest);
		return ResponseEntity.ok(SuccessResponse.create(WITHDRAWAL_SUCCESS.getMessage()));
	}

	/**
	 * 마이페이지 조회
	 * [GET] api/mypage
	 * 작성자: 김민수
	 */
	@GetMapping
	public ResponseEntity<SuccessResponse<GetMyPageResponse>> getMyPage(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(SuccessResponse.create(GET_MYPAGE_SUCCESS.getMessage(),
			this.getMyPageUseCase.getMyPageResponse(user.getSocialId())));
	}

	/**
	 * 프로필 조회
	 * [GET] api/mypage/profile
	 * 작성자 : 김민수
	 */
	@GetMapping("/profile")
	public ResponseEntity<SuccessResponse<GetProfileResponse>> getProfile(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(SuccessResponse.create(GET_PROFILE_SUCCESS.getMessage(),
			this.profileUseCase.getProfile(user.getSocialId())));
	}

	/**
	 * 프로필 수정
	 * [PUT] api/mypage/profile
	 * 작성자 : 김민수
	 */
	@PutMapping("/profile")
	public ResponseEntity<SuccessResponse> updatePorfile(@AuthenticationPrincipal User user,
		@RequestBody UpdateProfileRequest updateProfileRequest) {
		this.profileUseCase.updateProfile(user.getSocialId(), updateProfileRequest);
		return ResponseEntity.ok(SuccessResponse.create(UPDATE_PROFILE_SUCCESS.getMessage()));
	}

	/**
	 * 알림정보 조회
	 * [GET] api/mypage/alarm
	 * 작성자 : 김민수
	 */
	@GetMapping("/alarm")
	public ResponseEntity<SuccessResponse<GetAlarmResponse>> getAlarm(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(
			SuccessResponse.create(GET_ALARM_SUCCESS.getMessage(), this.alarmUseCase.getAlarm(user.getSocialId())));
	}

	/**
	 * 알림정보 수정
	 * [POST] api/mypage/alarm?type=all || isNewUploadPush || isRemindPush || isFirePush || isCommentPush && status= on || off
	 */
	@PutMapping("/alarm")
	public ResponseEntity<SuccessResponse<GetAlarmResponse>> updateAlarm(@AuthenticationPrincipal User user,
		@RequestParam(name = "type") String type,
		@RequestParam(name = "status") String status) {
		return ResponseEntity.ok(SuccessResponse.create(UPDATE_PROFILE_SUCCESS.getMessage(),
			this.alarmUseCase.updateAlarm(user.getSocialId(), type, status)));
	}

}
