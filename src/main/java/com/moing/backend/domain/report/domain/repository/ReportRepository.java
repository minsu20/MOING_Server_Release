package com.moing.backend.domain.report.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.report.domain.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
