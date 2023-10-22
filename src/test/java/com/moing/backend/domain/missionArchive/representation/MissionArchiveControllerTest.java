package com.moing.backend.domain.missionArchive.representation;

import com.moing.backend.config.CommonControllerTest;
import com.moing.backend.domain.missionArchive.application.dto.req.MissionArchiveHeartReq;
import com.moing.backend.domain.missionArchive.application.dto.req.MissionArchiveReq;
import com.moing.backend.domain.missionArchive.application.dto.res.MissionArchiveRes;
import com.moing.backend.domain.missionArchive.application.dto.res.MissionArchiveStatusRes;
import com.moing.backend.domain.missionArchive.application.dto.res.PersonalArchiveRes;
import com.moing.backend.domain.missionArchive.application.service.*;
import com.moing.backend.domain.missionArchive.presentation.MissionArchiveController;
import com.moing.backend.domain.missionHeart.application.dto.MissionHeartRes;
import com.moing.backend.domain.missionHeart.application.service.MissionHeartUseCase;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.moing.backend.domain.missionArchive.domain.constant.MissionArchiveResponseMessage.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@WebMvcTest(MissionArchiveController.class)
public class MissionArchiveControllerTest extends CommonControllerTest {

    @MockBean
    private MissionArchiveCreateUseCase missionArchiveCreateUseCase;
    @MockBean
    private MissionArchiveUpdateUseCase missionArchiveUpdateUseCase;
    @MockBean
    private MissionArchiveReadUseCase missionArchiveReadUseCase;
    @MockBean
    private RepeatMissionArchiveReadUseCase repeatMissionArchiveReadUseCase;
    @MockBean
    private MissionHeartUseCase missionHeartUseCase;



    @Test
    public void 미션_인증하기() throws Exception {
        //given
        MissionArchiveReq input = MissionArchiveReq.builder()
                .status("COMPLETE/SKIP")
                .archive("content[s3 Link / text / link]")
                .build();

        String body = objectMapper.writeValueAsString(input);

        MissionArchiveRes output = MissionArchiveRes.builder()
                .archiveId(1L)
                .archive("content[s3 Link / text / link]")
                .createdDate("2023-09-03T21:32:33.888")
                .status("COMPLETE/SKIP")
                .count(1L)
                .heartStatus("[True/False]")
                .hearts(1L)
                .build();

        given(missionArchiveCreateUseCase.createArchive(any(),any(),any())).willReturn(output);

        Long teamId = 1L;
        Long missionId = 1L;
        //when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.
                post("/api/team/{teamId}/missions/{missionId}/archive",teamId,missionId)
                        .header("Authorization", "Bearer ACCESS_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );

        //then
        actions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("teamId").description("팀 아이디"),
                                        parameterWithName("missionId").description("미션 아이디")
                                ),
                                requestFields(
                                        fieldWithPath("status").description("미션 인증 상태 [COMPLETE/SKIP]"),
                                        fieldWithPath("archive").description("미션 인증물 [s3URL/text/링크] ")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description("미션 인증을 완료 했습니다."),
                                        fieldWithPath("data.archiveId").description("미션 인증 아이디"),
                                        fieldWithPath("data.archive").description("미션 인증물 [s3URL/text/링크]"),
                                        fieldWithPath("data.createdDate").description("미션 제출 시각"),
                                        fieldWithPath("data.status").description("미션 인증 상태"),
                                        fieldWithPath("data.count").description("미션 인증 횟수"),
                                        fieldWithPath("data.hearts").description("미션 인증 좋아요 수"),
                                        fieldWithPath("data.heartStatus").description("미션 인증 좋아요 상태")
                                )
                        )
                )
                       .andReturn();

    }

    @Test
    public void 미션_재인증하기() throws Exception {
        //given
        MissionArchiveReq input = MissionArchiveReq.builder()
                .status("COMPLETE/SKIP")
                .archive("content[s3 Link / text / link]")
                .build();

        String body = objectMapper.writeValueAsString(input);

        MissionArchiveRes output = MissionArchiveRes.builder()
                .archiveId(1L)
                .archive("content[s3 Link / text / link]")
                .createdDate("2023-09-03T21:32:33.888")
                .status("COMPLETE/SKIP")
                .count(1L)
                .heartStatus("[True/False]")
                .hearts(1L)
                .build();

        given(missionArchiveUpdateUseCase.updateArchive(any(),any(),any())).willReturn(output);

        Long teamId = 1L;
        Long missionId = 1L;
        //when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.
                put("/api/team/{teamId}/missions/{missionId}/archive",teamId,missionId)
                .header("Authorization", "Bearer ACCESS_TOKEN")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        );

        //then
        actions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("teamId").description("팀 아이디"),
                                        parameterWithName("missionId").description("미션 아이디")
                                ),
                                requestFields(
                                        fieldWithPath("status").description("미션 인증 상태 [COMPLETE/SKIP]"),
                                        fieldWithPath("archive").description("미션 인증물 [s3URL/text/링크] ")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description(UPDATE_ARCHIVE_SUCCESS),
                                        fieldWithPath("data.archiveId").description("미션 인증 아이디"),
                                        fieldWithPath("data.archive").description("미션 인증물 [s3URL/text/링크]"),
                                        fieldWithPath("data.createdDate").description("미션 제출 시각"),
                                        fieldWithPath("data.hearts").description("미션 인증 좋아요 수"),
                                        fieldWithPath("data.status").description("미션 인증 상태"),
                                        fieldWithPath("data.count").description("미션 인증 횟수"),
                                        fieldWithPath("data.heartStatus").description("미션 인증 좋아요 상태"),
                                        fieldWithPath("data.hearts").description("미션 인증 좋아요 수")
                                )
                        )
                )
                .andReturn();

    }

    @Test
    public void 나의_미션_인증_조회() throws Exception {
        //given

        List<MissionArchiveRes> output = Lists.newArrayList(MissionArchiveRes.builder()
                .archiveId(1L)
                .archive("content[s3 Link / text / link]")
                .createdDate("2023-09-03T21:32:33.888")
                .status("COMPLETE/SKIP")
                .count(1L)
                .heartStatus("[True/False]")
                .hearts(1L)
                .build());

        given(missionArchiveReadUseCase.getMyArchive(any(),any())).willReturn(output);

        Long teamId = 1L;
        Long missionId = 1L;
        //when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.
                get("/api/team/{teamId}/missions/{missionId}/archive",teamId,missionId)
                .header("Authorization", "Bearer ACCESS_TOKEN")
                .contentType(MediaType.APPLICATION_JSON)

        );

        //then
        actions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("teamId").description("팀 아이디"),
                                        parameterWithName("missionId").description("미션 아이디")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description(READ_MY_ARCHIVE_SUCCESS.getMessage()),
                                        fieldWithPath("data[].archiveId").description("미션 인증 아이디"),
                                        fieldWithPath("data[].archive").description("미션 인증물 [s3URL/text/링크]"),
                                        fieldWithPath("data[].createdDate").description("미션 제출 시각"),
                                        fieldWithPath("data[].status").description("미션 인증 상태"),
                                        fieldWithPath("data[].count").description("미션 인증 횟수"),
                                        fieldWithPath("data[].heartStatus").description("미션 인증 좋아요 상태"),
                                        fieldWithPath("data[].hearts").description("미션 인증 좋아요 수")


                                        )
                        )
                )
                .andReturn();

    }

    @Test
    public void 모임원_미션_인증_조회() throws Exception {
        //given

        List<PersonalArchiveRes> output =  Lists.newArrayList(PersonalArchiveRes.builder()
                .archiveId(1L)
                .nickname("modagbul_tester1")
                .profileImg("[s3 Link]")
                .archive("content[s3 Link / text / link]")
                .createdDate("2023-09-03T21:32:33.888")
                .status("COMPLETE/SKIP")
                .count(1L)
                .heartStatus("[True/False]")
                .hearts(3)
                .build());

        given(missionArchiveReadUseCase.getPersonalArchive(any(),any())).willReturn(output);

        Long teamId = 1L;
        Long missionId = 1L;
        //when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.
                get("/api/team/{teamId}/missions/{missionId}/archive/others",teamId,missionId)
                .header("Authorization", "Bearer ACCESS_TOKEN")
                .contentType(MediaType.APPLICATION_JSON)

        );

        //then
        actions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("teamId").description("팀 아이디"),
                                        parameterWithName("missionId").description("미션 아이디")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description(READ_TEAM_ARCHIVE_SUCCESS),
                                        fieldWithPath("data[].archiveId").description("미션 인증 아이디"),
                                        fieldWithPath("data[].nickname").description("미션 인증자 닉네임 "),
                                        fieldWithPath("data[].profileImg").description("미션 인증자 프로필 이미지 "),
                                        fieldWithPath("data[].archive").description("미션 인증물 [s3URL/text/링크] "),
                                        fieldWithPath("data[].createdDate").description("미션 인증 날짜 "),
                                        fieldWithPath("data[].status").description("미션 인증 상태"),
                                        fieldWithPath("data[].count").description("미션 인증 횟수"),
                                        fieldWithPath("data[].heartStatus").description("미션 인증 좋아요 상태 "),
                                        fieldWithPath("data[].hearts").description("미션 인증 좋아요 수 ")


                                        )
                        )
                )
                .andReturn();

    }

    @Test
    public void 인증_성공_인원_조회() throws Exception {
        //given

        MissionArchiveStatusRes output = MissionArchiveStatusRes.builder()
                .total("8")
                .done("3")
                .build();

        given(missionArchiveReadUseCase.getMissionDoneStatus(any())).willReturn(output);

        Long teamId = 1L;
        Long missionId = 1L;
        //when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.
                get("/api/team/{teamId}/missions/{missionId}/archive/status",teamId,missionId)
                .header("Authorization", "Bearer ACCESS_TOKEN")
                .contentType(MediaType.APPLICATION_JSON)

        );

        //then
        actions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("teamId").description("팀 아이디"),
                                        parameterWithName("missionId").description("미션 아이디")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description(MISSION_ARCHIVE_PEOPLE_STATUS_SUCCESS.getMessage()),
                                        fieldWithPath("data.total").description("전체 미션 참여자"),
                                        fieldWithPath("data.done").description("미션 인증 완료한 미션 참여자 ")

                                )
                        )
                )
                .andReturn();

    }
    @Test
    public void 미션_인증물_좋아요() throws Exception {
        //given


        MissionHeartRes output = MissionHeartRes.builder()
                .missionArchiveId(1L)
                .missionHeartStatus("[True/False]")
                .hearts(3)
                .build();

        given(missionHeartUseCase.pushHeart(any(),any(),any())).willReturn(output);

        Long teamId = 1L;
        Long missionId = 1L;
        Long archiveId = 1L;
        String missionHeartStatus = "False";
        //when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.
                put("/api/team/{teamId}/missions/{missionId}/archive/{archiveId}/heart/{missionHeartStatus}", teamId, missionId,archiveId,missionHeartStatus)
                .header("Authorization", "Bearer ACCESS_TOKEN")
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("teamId").description("팀 아이디"),
                                        parameterWithName("missionId").description("미션 아이디"),
                                        parameterWithName("archiveId").description("미션 인증물 아이디"),
                                        parameterWithName("missionHeartStatus").description("미션 인증물 좋아요 상태")
                                ),

                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description(HEART_UPDATE_SUCCESS.getMessage()),
                                        fieldWithPath("data.missionArchiveId").description("미션 인증 아이디"),
                                        fieldWithPath("data.missionHeartStatus").description("미션 인증물 좋아요 상태 [True]"),
                                        fieldWithPath("data.hearts").description("미션 인증물 좋아요 수")

                                )
                        )
                )
                .andReturn();
    }






}