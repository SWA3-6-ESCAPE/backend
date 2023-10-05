package com.swa.escape.controller;

import com.swa.escape.domain.Report;
import com.swa.escape.dto.ReportModifyRequest;
import com.swa.escape.service.ReportService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;

  // 새 Report 추가하는 API
//  @PostMapping("/report")
//  public ResponseEntity<Report> createReport(@RequestBody ReportCreateRequest reportRequest) {
//    Report report = Report.builder()
//        .category1(reportRequest.getCategory1())
//        .latitude(reportRequest.getLatitude()).
//        longitude(reportRequest.getLongitude()).build();
//    return ResponseEntity.ok(report);
//  }

  // id에 해당하는 Report 가져오는 API
  @GetMapping("/report/{id}")
  public ResponseEntity<Report> getReport(@PathVariable int id) {
    Optional<Report> report = reportService.getReport(id);

    // report가 존재하면 ok, 없으면 notFound
    return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // id에 해당하는 Report 수정하는 API
  @PatchMapping("/report/{id}")
  public ResponseEntity<Report> updateReport(@PathVariable int id,
      @RequestBody ReportModifyRequest reportRequest) {
    try {
      Report report = reportService.updateReport(id, reportRequest);
      return ResponseEntity.ok(report);
    } catch (IllegalArgumentException e) {
      // id에 해당하는 Report가 없으면 notFound
      return ResponseEntity.notFound().build();
    }
  }
}
