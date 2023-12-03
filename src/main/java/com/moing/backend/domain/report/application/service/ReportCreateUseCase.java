package com.moing.backend.domain.report.application.service;

import com.moing.backend.domain.member.domain.service.MemberGetService;
import com.moing.backend.domain.report.application.mapper.ReportMapper;
import com.moing.backend.domain.report.domain.entity.Report;
import com.moing.backend.domain.report.domain.service.ReportSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportCreateUseCase {

    private final ReportSaveService reportSaveService;
    private final MemberGetService memberGetService;

    public Long createReport(String socialId, Long targetId, String reportType) {
        Long memberId = memberGetService.getMemberBySocialId(socialId).getMemberId();
        Report save = reportSaveService.save(ReportMapper.mapToReport(memberId, targetId, reportType));
        return save.getTargetId();
    }
}
