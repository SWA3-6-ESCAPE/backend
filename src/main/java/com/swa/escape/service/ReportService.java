package com.swa.escape.service;

import com.swa.escape.domain.Report;
import com.swa.escape.dto.ReportCreateRequest;
import com.swa.escape.dto.ReportModifyRequest;
import com.swa.escape.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService implements ReportServiceImpl {

    private final ReportRepository reportRepository;

    @Override
    public Report createReport(ReportCreateRequest reportRequest) {
        Report newReport = new Report();
        newReport.setCategory1(reportRequest.getCategory1());
        newReport.setLatitude(reportRequest.getLatitude());
        newReport.setLongitude(reportRequest.getLongitude());

        return reportRepository.save(newReport);
    }

    @Override
    public Optional<Report> getReport(int id) {
        return reportRepository.findById(id);
    }

    @Override
    public List<Report> getReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report updateReport(int id, ReportModifyRequest reportRequest) {
        Optional<Report> updateReportId = reportRepository.findById(id);

        // update 할 리포트를 찾았으면
        if (updateReportId.isPresent()) {
            Report existingReport = updateReportId.get();

            existingReport.setCategory2(reportRequest.getCategory2());
            existingReport.setDetail(reportRequest.getDetail());

            return reportRepository.save(existingReport);
        }

        return null;
    }
}
