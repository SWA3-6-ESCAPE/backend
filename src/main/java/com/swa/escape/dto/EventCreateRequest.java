package com.swa.escape.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventCreateRequest {
    private float event_latitude;
    private float event_longitude;
}
