package com.swa.escape.repository;

import com.swa.escape.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    eventRepository.save(event);

    // Then
    Event savedEvent = eventRepository.findById(eventId).orElse(null);
    assert savedEvent != null;
    assert savedEvent.getEventId() == event.getEventId();
    assert savedEvent.getLatitude() == event.getLatitude();
    assert savedEvent.getLongitude() == event.getLongitude();
  }

}