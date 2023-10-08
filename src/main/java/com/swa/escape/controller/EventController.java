package com.swa.escape.controller;


import com.swa.escape.domain.Event;
import com.swa.escape.dto.EventCreateRequest;
import com.swa.escape.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class EventController {

    private EventRepository eventRepository;

    // Event 추가하는 API
    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody EventCreateRequest eventRequest) {
        Event event = Event.builder()
                .longitude(eventRequest.getEvent_longitude())
                .latitude(eventRequest.getEvent_longitude())
                .build();
        eventRepository.save(event);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/event/{event_id}")
    public ResponseEntity<Event> getEvent(@PathVariable int event_id) {
        Event event = eventRepository.findEventByEventId(event_id);

        if(event != null){
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
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
         Event event = eventRepository.findEventByEventId(event_id);
        eventRepository.deleteById(event_id);
        // redirect
        return ResponseEntity.ok(event);
    }
}
