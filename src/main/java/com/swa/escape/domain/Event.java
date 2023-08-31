package com.swa.escape.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Event {

    @Id
    @Column(name = "eventId")
    private int eventId;

    @Column
    private final LocalDateTime eventTime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

    @Column
    private float latitude;

    @Column
    private float longitude;
    @OneToMany(mappedBy = "report_id") // 수정해야 함
    private List<Report> reports = new ArrayList<>();

    // EventTable - Report Table
}
