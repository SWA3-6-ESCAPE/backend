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

    /**
     * 리포트 생성
     * 1. 새로운 리포트를 생성한다
     * 2. 반경 1km 내에 이벤트가 있으면 그곳에 귀속시키고, 아니면 createEvent를 한다.
     * 3. 귀속 시킬 때 해당 이벤트에 속한 리포트가 10개가 넘는지 확인힌다. -> enableEvent
     */
    @Override
    public Report createReport(ReportCreateRequest reportRequest) {
        // 새로운 리포트 생성
        Report newReport = new Report();
        newReport.setCategory1(reportRequest.getCategory1());
        newReport.setLatitude(reportRequest.getLatitude());
        newReport.setLongitude(reportRequest.getLongitude());

        reportRepository.save(newReport);

        // 반경 1km 내에 속하는 이벤트가 있는 지 확인, 해당 이벤트에 10개의 리포트가 있는 지 확인
        // 없다면 createEvent 호출
        if () {


        } else {


        }

        return null;
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

        else {
            throw new IllegalArgumentException("해당 id의 리포트가 없습니다");
        }
    }
}
