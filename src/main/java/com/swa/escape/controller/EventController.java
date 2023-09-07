package com.swa.escape.controller;


import com.swa.escape.domain.Event;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class EventController {

    @PostMapping("/event/{id}")
    public ResponseEntity<Event> createEvent(@RequestBody RequestBody requestBody) {
        Event event = Event.builder()
                .longitude(1.0F)
                .latitude(1.0F)
                .build();
        return ResponseEntity.ok(event);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable int id) {
        Event event = Event.builder()
                .longitude(1.0F)
                .latitude(1.0F)
                .build();
        return ResponseEntity.ok(event);
    }

    @PatchMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id) {
        Event event = Event.builder()
                .longitude(1.0F)
                .latitude(1.0F)
                .build();
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable int id) {
        Event event = Event.builder()
                .longitude(1.0F)
                .latitude(1.0F)
                .build();
        return ResponseEntity.ok(event);
    }
}
