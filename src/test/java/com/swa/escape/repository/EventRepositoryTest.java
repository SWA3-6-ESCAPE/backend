package com.swa.escape.repository;

import com.swa.escape.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void CreateEvent() {
        // Given
        Event event = Event.builder()
            .latitude(1.0f)
            .longitude(1.0f)
            .build();

        // When
        int eventId = eventRepository.save(event).getEventId();

        // Then
        Event savedEvent = eventRepository.findById(eventId).orElse(null);
        assert savedEvent != null;
        assert savedEvent.getEventId() == event.getEventId();
        assert savedEvent.getLatitude() == event.getLatitude();
        assert savedEvent.getLongitude() == event.getLongitude();
    }

    @Test
    void findEventsTest() {
        // Given
        Event event1 = Event.builder()
            .latitude(1.0f)
            .longitude(1.0f)
            .build();


        Event event2 = Event.builder()
            .latitude(1.0f)
            .longitude(1.0f)
            .build();

        // when
        int eventId1 = eventRepository.save(event1).getEventId();
        int eventId2 = eventRepository.save(event2).getEventId();

        // then
        List<Event> allEvents = eventRepository.findAll();

        Assertions.assertThat(allEvents.get(0).getEventId()).isEqualTo(eventId1);
        Assertions.assertThat(allEvents.get(1).getEventId()).isEqualTo(eventId2);
    }

}