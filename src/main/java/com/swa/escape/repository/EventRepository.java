package com.swa.escape.repository;

import com.swa.escape.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {


    public Event findEventByEventId(int eventId);

    public List<Event> findAllEvents();
}
