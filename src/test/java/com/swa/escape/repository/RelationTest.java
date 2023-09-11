package com.swa.escape.repository;

import com.swa.escape.domain.Category2;
import com.swa.escape.domain.Event;
import com.swa.escape.domain.Report;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
public class RelationTest {
  @Autowired
  private ReportRepository reportRepository;
  @Autowired
  private EventRepository eventRepository;

  @Test
  @DisplayName("Event와 Report의 생성 테스트")
  public void createTest() {
    // Given
    Event event = Event.builder()
            .latitude(1.0f)
            .longitude(1.0f)
            .build();
    Report report = Report.builder()
            .category1(null)
            .category2(Category2.FIRE)
            .detail("test detail")
            .latitude(0)
            .longitude(0)
            .build();

    event.addReport(report);

    // When
    int eventId = eventRepository.save(event).getEventId();

    // Then
    Event savedEvent = eventRepository.findById(eventId).orElse(null);
    assert savedEvent != null;
    assert savedEvent.getEventId() == event.getEventId();
    assert savedEvent.getLatitude() == event.getLatitude();
    assert savedEvent.getLongitude() == event.getLongitude();

    int reportId = savedEvent.getReports().get(0).getReportId();
    Report savedReport = reportRepository.findById(reportId).orElse(null);
    assert savedReport != null;
    assert report.getReportId() == savedReport.getReportId();
    assert report.getDetail().equals(savedReport.getDetail());
  }

  @Test
  @DisplayName("Report의 Event 조회 테스트")
  public void getEventByReportTest() {
    // Given
    Event event = Event.builder()
        .latitude(1.0f)
        .longitude(1.0f)
        .build();
    Report report = Report.builder()
        .category1(null)
        .category2(Category2.FIRE)
        .detail("test detail")
        .latitude(0)
        .longitude(0)
        .build();

    event.addReport(report);

    // When
    int eventId = eventRepository.save(event).getEventId();

    // Then
    Report savedReport = reportRepository.findById(eventId).orElse(null);
    assert savedReport != null;
    Event savedEvent = savedReport.getEvent();
    assert savedEvent != null;
    assert savedEvent.getEventId() == event.getEventId();
    assert savedEvent.getLatitude() == event.getLatitude();
    assert savedEvent.getLongitude() == event.getLongitude();
  }

  @Test
  @DisplayName("Event의 Report 조회 테스트")
  public void getReportsByEventTest() {
    // Given
    Event event = Event.builder()
        .latitude(1.0f)
        .longitude(1.0f)
        .build();
    Report report = Report.builder()
        .category1(null)
        .category2(Category2.FIRE)
        .detail("test detail")
        .latitude(0)
        .longitude(0)
        .build();

    event.addReport(report);
    int eventId = eventRepository.save(event).getEventId();

    // When
    Event savedEvent = eventRepository.findById(eventId).orElse(null);
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
    Event event = Event.builder()
        .latitude(1.0f)
        .longitude(1.0f)
        .build();
    Report report = Report.builder()
        .category1(null)
        .category2(Category2.FIRE)
        .detail("test detail")
        .latitude(0)
        .longitude(0)
        .build();

    event.addReport(report);
    int eventId = eventRepository.save(event).getEventId();

    // When
    Event savedEvent = eventRepository.findById(eventId).orElse(null);
    assert savedEvent != null;
    Report savedReport = savedEvent.getReports().get(0);
    savedEvent.removeReport(savedReport);
    eventRepository.save(savedEvent);

    // Then
    Event deletedEvent = eventRepository.findById(eventId).orElse(null);
    assert deletedEvent != null;
    assert deletedEvent.getReports().size() == 0;

    Report deletedReport = reportRepository.findById(savedReport.getReportId()).orElse(null);
    assert deletedReport != null;
    assert deletedReport.getEvent() == null;
  }
}
