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
    int eventId = 1;
    Event event = Event.builder()
            .eventId(eventId)
            .latitude(1.0f)
            .longitude(1.0f)
            .build();

    // When
    System.out.println("event.getEventId() = " + event.getEventId());
    eventRepository.save(event);
    // Then
    Event savedEvent = eventRepository.findById(eventId).orElse(null);
    System.out.println("event.getEventId() = " + savedEvent.getEventId());
    assert savedEvent != null;
    assert savedEvent.getEventId() == event.getEventId();
    assert savedEvent.getLatitude() == event.getLatitude();
    assert savedEvent.getLongitude() == event.getLongitude();
  }

  @Test
  void findEventsTest(){

    int eventId = 2;
    Event event = Event.builder()
            .eventId(eventId)
            .latitude(1.0f)
            .longitude(1.0f)
            .build();

    eventRepository.save(event);

    Event event1 = Event.builder()
            .eventId(++eventId)
            .latitude(1.0f)
            .longitude(1.0f)
            .build();

    eventRepository.save(event1);

    List<Event> allEvents = eventRepository.findAll();

    Assertions.assertThat(allEvents.get(0).getEventId()).isEqualTo(1);
    System.out.println("EventRepositoryTest.findEventsTest");
    Assertions.assertThat(allEvents.get(1).getEventId()).isEqualTo(2);
  }

}