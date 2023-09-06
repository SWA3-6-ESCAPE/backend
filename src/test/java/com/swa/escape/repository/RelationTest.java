package com.swa.escape.repository;

import com.swa.escape.domain.Category2;
import com.swa.escape.domain.Event;
import com.swa.escape.domain.Report;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RelationTest {
  @Autowired
  private ReportRepository reportRepository;
  @Autowired
  private EventRepository eventRepository;

  @Test
  @DisplayName("Event와 Report의 생성 테스트")
  public void createTest() {
    // Given
    int testId = 1;
    Event event = Event.builder()
            .eventId(testId)
            .latitude(1.0f)
            .longitude(1.0f)
            .build();
    Report report = Report.builder()
            .reportId(testId)
            .category1(null)
            .category2(Category2.FIRE)
            .detail("test detail")
            .latitude(0)
            .longitude(0)
            .build();

    event.addReport(report);

    // When
    eventRepository.save(event);

    // Then
    Event savedEvent = eventRepository.findById(testId).orElse(null);
    assert savedEvent != null;
    assert savedEvent.getEventId() == event.getEventId();
    assert savedEvent.getLatitude() == event.getLatitude();
    assert savedEvent.getLongitude() == event.getLongitude();

    Report savedReport = reportRepository.findById(testId).orElse(null);
    assert savedReport != null;
    assert report.getReportId() == savedReport.getReportId();
    assert report.getDetail().equals(savedReport.getDetail());
  }

  @Test
  @DisplayName("Report의 Event 조회 테스트")
  public void getEventByReportTest() {
    // Given
    int testId = 1;
    Event event = Event.builder()
        .eventId(testId)
        .latitude(1.0f)
        .longitude(1.0f)
        .build();
    Report report = Report.builder()
        .reportId(testId)
        .category1(null)
        .category2(Category2.FIRE)
        .detail("test detail")
        .latitude(0)
        .longitude(0)
        .build();

    event.addReport(report);
    eventRepository.save(event);

    // When
    Report savedReport = reportRepository.findById(testId).orElse(null);
    assert savedReport != null;
    Event savedEvent = savedReport.getEvent();

    // Then
    assert savedEvent != null;
    assert savedEvent.getEventId() == event.getEventId();
    assert savedEvent.getLatitude() == event.getLatitude();
    assert savedEvent.getLongitude() == event.getLongitude();
  }

  @Test
  @DisplayName("Event의 Report 조회 테스트")
  public void getReportsByEventTest() {
    // Given
    int testId = 1;
    Event event = Event.builder()
        .eventId(testId)
        .latitude(1.0f)
        .longitude(1.0f)
        .build();
    Report report = Report.builder()
        .reportId(testId)
        .category1(null)
        .category2(Category2.FIRE)
        .detail("test detail")
        .latitude(0)
        .longitude(0)
        .build();

    event.addReport(report);
    eventRepository.save(event);

    // When
    Event savedEvent = eventRepository.findById(testId).orElse(null);
    assert savedEvent != null;
    Report savedReport = savedEvent.getReports().get(0);

    // Then
    assert savedReport != null;
    assert savedReport.getReportId() == report.getReportId();
    assert savedReport.getDetail().equals(report.getDetail());
  }

  @Test
  @DisplayName("Event의 Report 삭제 테스트")
  public void deleteReportByEventTest() {
    // Given
    int testId = 1;
    Event event = Event.builder()
        .eventId(testId)
        .latitude(1.0f)
        .longitude(1.0f)
        .build();
    Report report = Report.builder()
        .reportId(testId)
        .category1(null)
        .category2(Category2.FIRE)
        .detail("test detail")
        .latitude(0)
        .longitude(0)
        .build();

    event.addReport(report);
    eventRepository.save(event);

    // When
    Event savedEvent = eventRepository.findById(testId).orElse(null);
    assert savedEvent != null;
    savedEvent.removeReport(savedEvent.getReports().get(0));
    eventRepository.save(savedEvent);

    // Then
    Event deletedEvent = eventRepository.findById(testId).orElse(null);
    assert deletedEvent != null;
    assert deletedEvent.getReports().size() == 0;

    Report deletedReport = reportRepository.findById(testId).orElse(null);
    assert deletedReport != null;
    assert deletedReport.getEvent() == null;
  }
}
