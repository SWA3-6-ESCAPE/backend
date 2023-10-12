package com.swa.escape.dto;


import com.swa.escape.domain.EventStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventCreateRequest {
    private float event_latitude;
    private float event_longitude;
    private EventStatus eventStatus;
}
