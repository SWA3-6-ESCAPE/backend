package com.swa.escape.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "report")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "category1")
    private Boolean category1;

    @Enumerated(EnumType.STRING)
    private Category2 category2;

    @Column(name = "detail")
    private String detail;

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "longitude", nullable = false)
    private float longitude;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private final LocalDateTime created_time = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
}
