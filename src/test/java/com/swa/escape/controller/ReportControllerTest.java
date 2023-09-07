package com.swa.escape.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swa.escape.domain.Category2;
import com.swa.escape.dto.ReportCreateRequest;
import com.swa.escape.dto.ReportModifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReportController.class)
class ReportControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createReportTest() throws Exception {
    // given
    ReportCreateRequest reportRequest = new ReportCreateRequest();
    reportRequest.setCategory1(true);
    reportRequest.setLatitude(36.366535F);
    reportRequest.setLongitude(127.344508F);

    // when // then
    mockMvc.perform(
        post(("/api/report"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(reportRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.category1").value(true))
        .andExpect(jsonPath("$.latitude").value(36.366535F))
        .andExpect(jsonPath("$.longitude").value(127.344508F))
        .andDo(print()
    );
  }

  @Test
  void getReportTest() throws Exception {
    // given
    int testId = 1;

    // when // then
    mockMvc.perform(
            get(("/api/report/" + testId)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.reportId").value(testId))
        .andExpect(jsonPath("$.category1").value(true))
        .andExpect(jsonPath("$.category2").value("ASSAULT"))
        .andExpect(jsonPath("$.latitude").value(36.366535F))
        .andExpect(jsonPath("$.longitude").value(127.344508F))
        .andExpect(jsonPath("$.detail").value("detail"))
        .andDo(print()
        );
  }

  @Test
  void modifyReportTest() throws Exception {
    // given
    int testId = 1;
    ReportModifyRequest reportRequest = new ReportModifyRequest();
    reportRequest.setCategory2(Category2.ASSAULT);
    reportRequest.setDetail("detail");

    // when // then
    mockMvc.perform(
            patch(("/api/report/"+ testId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.reportId").value(testId))
        .andExpect(jsonPath("$.category2").value(Category2.ASSAULT.toString()))
        .andExpect(jsonPath("$.detail").value("detail"))
        .andDo(print()
        );
  }

  @Test
  void deleteReportTest() throws Exception {
    // given
    int testId = 1;

    // when // then
    mockMvc.perform(
            get(("/api/report/" + testId)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.reportId").value(testId))
        .andExpect(jsonPath("$.category1").value(true))
        .andExpect(jsonPath("$.category2").value(Category2.ASSAULT.toString()))
        .andExpect(jsonPath("$.latitude").value(36.366535F))
        .andExpect(jsonPath("$.longitude").value(127.344508F))
        .andExpect(jsonPath("$.detail").value("detail"))
        .andDo(print()
        );
  }
}