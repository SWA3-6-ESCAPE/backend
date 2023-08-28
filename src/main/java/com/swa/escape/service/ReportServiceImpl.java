package com.swa.escape.service;

import com.swa.escape.domain.Report;
import com.swa.escape.dto.ReportCreateRequest;
import com.swa.escape.dto.ReportModifyRequest;

import java.util.List;

public interface ReportServiceImpl {
//  Report 생성
    Report createReport(ReportCreateRequest reportRequest);
//  Report ID로 한 개만 조회
    Report getReport(int id);
//  모든 Report 조회
    List<Report> getReports();
//  Report에 정보 추가
    Report updateReport(int id, ReportModifyRequest reportRequest);

//    당장은 필요 없을 것 같음
//   void deleteReport(int id);
}
