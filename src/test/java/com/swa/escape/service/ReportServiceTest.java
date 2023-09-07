package com.swa.escape.service;

import com.swa.escape.domain.Category2;
import com.swa.escape.domain.Report;
import com.swa.escape.dto.ReportCreateRequest;
import com.swa.escape.dto.ReportModifyRequest;
import com.swa.escape.repository.ReportRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    @DisplayName("리포트 생성")
    void createReport() {
        //given
        Report report = Report.builder()
                .reportId(1)
                .category1(true)
                .latitude(36.366535F)
                .longitude(127.344508F)
                .build();
        when(reportRepository.save(any(Report.class))).thenReturn(report);

        //when
        ReportCreateRequest request = new ReportCreateRequest();
        Report newReport = reportService.createReport(request);

        //then
        assertThat(newReport.getReportId()).isEqualTo(report.getReportId());
        assertThat(newReport.getCategory1()).isEqualTo(report.getCategory1());
        assertThat(newReport.getLatitude()).isEqualTo(report.getLatitude());
        assertThat(newReport.getLongitude()).isEqualTo(report.getLongitude());

    }

    @Test
    @DisplayName("id로 리포트 조회")
    void getReport() {
        //given
        Report report = Report.builder()
                .reportId(2)
                .category1(true)
                .latitude(36.366535F)
                .longitude(127.344508F)
                .build();
        when(reportRepository.findById(any(Integer.class))).thenReturn(Optional.of(report));

        //when
        int testId = 2;
        Report findReport = reportService.getReport(testId).get();

        // then
        assertThat(findReport.getReportId()).isEqualTo(report.getReportId());
        assertThat(findReport.getCategory1()).isEqualTo(report.getCategory1());
        assertThat(findReport.getLatitude()).isEqualTo(report.getLatitude());
        assertThat(findReport.getLongitude()).isEqualTo(report.getLongitude());

    }

    @Test
    @DisplayName("모든 리포트 조회")
    void getReports() {
        //given
        List<Report> reportList = new ArrayList<>();
        Report report1 = Report.builder()
                .reportId(1)
                .category1(true)
                .latitude(36.366535F)
                .longitude(127.344508F)
                .build();
        reportList.add(report1);
        Report report2 = Report.builder()
                .reportId(2)
                .category1(false)
                .latitude(26.366535F)
                .longitude(227.344508F)
                .build();
        reportList.add(report2);
        when(reportRepository.findAll()).thenReturn(reportList);

        //when
        List<Report> reports = reportService.getReports();

        //then
        assertThat(reports.size()).isEqualTo(2);
        assertThat(reports).contains(report1, report2);

    }

    @Test
    @DisplayName("리포트 수정")
    void updateReport() {
        //given
        // 새로운 리포트 생성
        int testId = 1;
        Report report = Report.builder()
                .reportId(testId)
                .category1(true)
                .latitude(36.366535F)
                .longitude(127.344508F)
                .build();
        when(reportRepository.save(any(Report.class))).thenReturn(report);
        when(reportRepository.findById(testId)).thenReturn(Optional.of(report));

        ReportCreateRequest reportCreateRequest = new ReportCreateRequest();
        Report newReport = reportService.createReport(reportCreateRequest);

        // 임의 지정한 수정할 데이터
        ReportModifyRequest modifyRequest = new ReportModifyRequest();
        modifyRequest.setCategory2(Category2.FIRE);
        modifyRequest.setDetail("BIG FIRE!!! RUN!!");

        //when
        Report modifyReport = reportService.updateReport(testId, modifyRequest);

        //then
        assertThat(newReport.getReportId()).isEqualTo(modifyReport.getReportId());
        assertThat(newReport.getCategory1()).isEqualTo(modifyReport.getCategory1());
        assertThat(newReport.getCategory2()).isEqualTo(modifyReport.getCategory2());
        assertThat(newReport.getDetail()).isEqualTo(modifyReport.getDetail());

    }
}