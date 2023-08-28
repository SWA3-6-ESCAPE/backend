package com.swa.escape.service;

import com.swa.escape.domain.Report;
import com.swa.escape.dto.ReportCreateRequest;
import com.swa.escape.dto.ReportModifyRequest;
import com.swa.escape.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService implements ReportServiceImpl {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report createReport(ReportCreateRequest reportRequest) {
        return null;
    }

    @Override
    public Report getReport(int id) {
        return null;
    }

    @Override
    public List<Report> getReports() {
        return null;
    }

    @Override
    public Report updateReport(int id, ReportModifyRequest reportRequest) {
        return null;
    }
}
