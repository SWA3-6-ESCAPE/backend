package com.swa.escape;

import com.swa.escape.domain.Category2;
import com.swa.escape.domain.Report;
import com.swa.escape.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
// 실제로 DB에 저장해보기 위해 SpringBootTest 사용
@SpringBootTest
public class MysqlTest {
    @Autowired
    private ReportRepository reportRepository;

    @Test
    void CreateReport() {
        Report report = Report.builder()
                .category1(null)
                .category2(Category2.FIRE)
                .detail("test detail")
                .latitude(0)
                .longitude(0)
                .build();

        // DB에 저장
        reportRepository.save(report);

        Report savedReport = reportRepository.findById(report.getId()).orElse(null);
        assert savedReport != null;
        assert report.getId() == savedReport.getId();
        assert report.getDetail().equals(savedReport.getDetail());
    }
}
