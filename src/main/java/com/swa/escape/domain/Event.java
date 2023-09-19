package com.swa.escape.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "event_time")
    private final LocalDateTime eventTime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

    @Column(name = "event_latitude")
    private float latitude;

    @Column(name = "event_longitude")
    private float longitude;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade= CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    public void addReport(Report report) {
        reports.add(report);
        report.setEvent(this);
    }

    public void removeReport(Report report) {
        reports.remove(report);
        report.setEvent(null);
    }
}
