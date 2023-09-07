package com.swa.escape.controller;


import com.swa.escape.domain.Event;
import com.swa.escape.dto.EventCreateRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class EventController {

    // Event 추가하는 API
    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody EventCreateRequest eventRequest) {
        Event event = Event.builder()
                .longitude(eventRequest.getEvent_longitude())
                .latitude(eventRequest.getEvent_longitude())
                .build();
        return ResponseEntity.ok(event);
    }

    @GetMapping("/event/{event_id}")
    public ResponseEntity<Event> getEvent(@PathVariable int event_id) {
        Event event = Event.builder()
                .eventId(event_id)
                .longitude(1.0F)
                .latitude(1.0F)
                .build();
        return ResponseEntity.ok(event);
    }

    @PatchMapping("/event/{event_id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int event_id) {
        Event event = Event.builder()
                .longitude(1.0F)
                .latitude(1.0F)
                .build();
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/event/{event_id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable int event_id) {
        Event event = Event.builder()
                .longitude(1.0F)
                .latitude(1.0F)
                .build();
        return ResponseEntity.ok(event);
    }
}
