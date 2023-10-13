package com.swa.escape.controller;


import com.swa.escape.domain.Event;
import com.swa.escape.dto.EventCreateRequest;
import com.swa.escape.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api") // localhost:8080/api/event
public class EventController {


    private final EventService eventService;

    // DI 주입
    public EventController(EventService eventService) {
            this.eventService = eventService;
    }

    // Event 추가하는 API
    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody EventCreateRequest eventRequest) {
        Event event = Event.builder()
                .longitude(eventRequest.getEvent_longitude())
                .latitude(eventRequest.getEvent_latitude())
                .build();
        return ResponseEntity.ok(eventService.createEvent(eventRequest));
    }

    @GetMapping("/event/{event_id}")
    public ResponseEntity<Event> getEvent(@PathVariable int event_id) {
        Optional<Event> event = eventService.getEvent(event_id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 이부분은 사용할 일이 없어서 주석처리 했습니다.
    // @PatchMapping("/event/{event_id}")
    // public ResponseEntity<Event> updateEvent(@PathVariable int event_id) {
    //     Event event = Event.builder()
    //             .longitude(1.0F)
    //             .latitude(1.0F)
    //             .build();
    //     return ResponseEntity.ok(event);
    // }

    @DeleteMapping("/event/{event_id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable int event_id) {
        Optional<Event> event = eventService.getEvent(event_id);
        if(event.isPresent()){
            eventService.deleteEvent(event_id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
