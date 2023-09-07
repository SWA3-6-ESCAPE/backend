package com.swa.escape.controller;

import com.swa.escape.domain.Category2;
import com.swa.escape.domain.Report;
import com.swa.escape.dto.ReportCreateRequest;
import com.swa.escape.dto.ReportModifyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReportController {

  // 새 Report 추가하는 API
  @PostMapping("/report")
  public ResponseEntity<Report> createReport(@RequestBody ReportCreateRequest reportRequest) {
    Report report = Report.builder()
        .category1(reportRequest.getCategory1())
        .latitude(reportRequest.getLatitude()).
        longitude(reportRequest.getLongitude()).build();
    return ResponseEntity.ok(report);
  }

  // id에 해당하는 Report 가져오는 API
  @GetMapping("/report/{id}")
  public ResponseEntity<Report> getReport(@PathVariable int id) {
    Report report = Report.builder()
        .reportId(id)
        .category1(true)
        .category2(Category2.ASSAULT)
        .latitude(36.366535F)
        .longitude(127.344508F)
        .detail("detail")
        .build();

    return ResponseEntity.ok(report);
  }

  // id에 해당하는 Report 수정하는 API
  @PatchMapping("/report/{id}")
  public ResponseEntity<Report> updateReport(@PathVariable int id, @RequestBody ReportModifyRequest reportRequest) {
    Report report = Report.builder()
        .reportId(id)
        .category1(true)
        .category2(reportRequest.getCategory2())
        .latitude(36.366535F)
        .longitude(127.344508F)
        .detail(reportRequest.getDetail())
        .build();

    return ResponseEntity.ok(report);
  }

  // id에 해당하는 Report 삭제하는 API
  @DeleteMapping("/report/{id}")
  public ResponseEntity<Report> deleteReport(@PathVariable int id) {
    Report report = Report.builder()
        .reportId(id)
        .category1(true)
        .category2(Category2.ASSAULT)
        .latitude(36.366535F)
        .longitude(127.344508F)
        .detail("detail")
        .build();

    return ResponseEntity.ok(report);
  }
}
