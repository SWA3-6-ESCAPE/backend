package com.swa.escape.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swa.escape.domain.Category2;
import com.swa.escape.domain.Report;
import com.swa.escape.dto.ReportCreateRequest;
import com.swa.escape.dto.ReportModifyRequest;
import com.swa.escape.service.ReportService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReportController.class)
class ReportControllerTest {

  @MockBean
  private ReportService reportService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createReportTest() throws Exception {
    // given
    int testId = 1;
    ReportCreateRequest reportRequest = new ReportCreateRequest();
    reportRequest.setCategory1(true);
    reportRequest.setLatitude(36.366535F);
    reportRequest.setLongitude(127.344508F);

    Report report = Report.builder()
        .reportId(testId)
        .category1(reportRequest.getCategory1())
        .latitude(reportRequest.getLatitude())
        .longitude(reportRequest.getLongitude())
        .detail("detail")
        .build();

    when(reportService.createReport(any(ReportCreateRequest.class))).thenReturn(report);

    // when // then
    mockMvc.perform(
            post(("/api/report"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.reportId").value(testId))
        .andExpect(jsonPath("$.category1").value(reportRequest.getCategory1()))
        .andExpect(jsonPath("$.latitude").value(reportRequest.getLatitude()))
        .andExpect(jsonPath("$.longitude").value(reportRequest.getLongitude()))
        .andDo(print()
        );
  }

  @Test
  @DisplayName("id로 report 조회")
  void getReportTest() throws Exception {
    // given
    int testId = 1;
    Report report = Report.builder()
        .reportId(testId)
        .category1(true)
        .category2(Category2.ASSAULT)
        .latitude(36.366535F)
        .longitude(127.344508F)
        .detail("detail")
        .build();
    when(reportService.getReport(testId)).thenReturn(Optional.of(report));

    // when // then
    mockMvc.perform(
            get(("/api/report/" + testId)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.reportId").value(testId))
        .andExpect(jsonPath("$.category1").value(report.getCategory1()))
        .andExpect(jsonPath("$.category2").value(report.getCategory2().toString()))
        .andExpect(jsonPath("$.latitude").value(report.getLatitude()))
        .andExpect(jsonPath("$.longitude").value(report.getLongitude()))
        .andExpect(jsonPath("$.detail").value(report.getDetail()))
        .andDo(print()
        );
  }

  @Test
  @DisplayName("id로 존재하지 않는 report 조회")
  void getNonExistingReportTest() throws Exception {
    // given
    int testId = 1;
    when(reportService.getReport(testId)).thenReturn(Optional.empty());

    // when // then
    mockMvc.perform(
            get(("/api/report/" + testId)))
        .andExpect(status().isNotFound())
        .andDo(print()
        );
  }

  @Test
  @DisplayName("id로 report 수정")
  void modifyReportTest() throws Exception {
    // given
    int testId = 1;
    ReportModifyRequest reportRequest = new ReportModifyRequest();
    reportRequest.setCategory2(Category2.FIRE);
    reportRequest.setDetail("modified detail");

    Report report = Report.builder()
        .reportId(testId)
        .category1(true)
        .category2(reportRequest.getCategory2())
        .latitude(36.366535F)
        .longitude(127.344508F)
        .detail(reportRequest.getDetail())
        .build();

    when(reportService.updateReport(eq(testId), any(ReportModifyRequest.class))).thenReturn(report);

    // when // then
    mockMvc.perform(
            patch(("/api/report/" + testId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.reportId").value(testId))
        .andExpect(jsonPath("$.category2").value(reportRequest.getCategory2().toString()))
        .andExpect(jsonPath("$.detail").value(reportRequest.getDetail()))
        .andDo(print()
        );
  }

  @Test
  @DisplayName("id로 존재하지 않는 report 수정")
  void modifyNonExistingReportTest() throws Exception {
    // given
    int testId = 1;
    ReportModifyRequest reportRequest = new ReportModifyRequest();
    reportRequest.setCategory2(Category2.ASSAULT);
    reportRequest.setDetail("detail");

    when(reportService.updateReport(eq(testId), any(ReportModifyRequest.class))).thenThrow(
        new IllegalArgumentException());

    // when // then
    mockMvc.perform(
            patch(("/api/report/" + testId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportRequest)))
        .andExpect(status().isNotFound())
        .andDo(print()
        );
  }
}
