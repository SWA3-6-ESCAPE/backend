package com.swa.escape.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "report")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "report_id")
 
    private int reportId;
 
    @Column(name = "category1")
    private Boolean category1;

    @Enumerated(EnumType.STRING)
    private Category2 category2;

    @Column(name = "detail")
    private String detail;

    @Column(name = "report_latitude", nullable = false)
    private float latitude;

    @Column(name = "report_longitude", nullable = false)
    private float longitude;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private final LocalDateTime created_time = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

}
