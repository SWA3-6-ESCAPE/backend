package com.swa.escape.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "event_time")
    private final LocalDateTime eventTime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

    @Column(name = "event_latitude")
    private float latitude;


    @Column(name = "event_longitude")
    private float longitude;

    @OneToMany(mappedBy = "event")
    private List<Report> reports = new ArrayList<>();
}
