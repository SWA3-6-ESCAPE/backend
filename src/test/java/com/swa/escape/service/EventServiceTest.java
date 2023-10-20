package com.swa.escape.service;

import com.swa.escape.domain.Event;
import com.swa.escape.repository.EventRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void createEvent() {
    }

    @Test
    @DisplayName("모든 이벤트 조회")
    void getEvents() {
        Event event1 = Event.builder()
                .eventId(1)
                .latitude(1.0F)
                .longitude(1.0F)
                .build();
        Event event2 = Event.builder()
                .eventId(2)
                .latitude(2.0F)
                .longitude(2.0F)
                .build();
        List<Event> events = List.of(event1, event2);
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.getEvents();
        assertEquals(result.size(), 2);
        assert result.size() > 0;
        assert result.contains(event1) && result.contains(event2);
    }

    @Test
    void getEvent() {
    }

    @Test
    void updateEvent() {
    }

    @Test
    void deleteEvent() {
    }

    @Test
    void addressConverter() {
        String address = eventService.addressConverter(36.369023F, 127.346090F);
        System.out.println(address);

    }

    @Test
    void sendNotifications() {
    }

    @Test
    void checkActivationCondition() {
    }
}