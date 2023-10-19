package com.swa.escape.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.swa.escape.utils.Calcul;
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

    @Column(name = "event_region")
    private String eventRegion;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade= CascadeType.ALL)
    @JsonManagedReference
    private List<Report> reports = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.AWAITING;

    public void addReport(Report report) {
        reports.add(report);
        report.setEvent(this);
    }

    public void removeReport(Report report) {
        reports.remove(report);
        report.setEvent(null);
    }

    // 넣으려면 넣어도 좋습니다.
    // EventService 구현할 때 편할 거 같아서 넣었습니다.
    // 필요없으면 다 지워도 됩니다.
    public boolean isPopAlarm(){
        int reportCnt = reports.size();
        // do alarm
        return reportCnt >= 10 && eventStatus == EventStatus.AWAITING;
    }

    public double distanceReport(Report report) {
        double lat1 = report.getLatitude();
        double lon1 = report.getLongitude();
        double lat2 = latitude;
        double lon2 = longitude;
        return Calcul.haversine(lat1, lon1, lat2, lon2);
    }
}
