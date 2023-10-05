package com.swa.escape.service;

import com.swa.escape.domain.Event;
import com.swa.escape.dto.EventCreateRequest;

import java.util.List;
import java.util.Optional;

public interface EventServiceImpl {
    Event createEvent(EventCreateRequest eventRequest);

    List<Event> getEvents();

    Optional<Event> getEvent(int eventId);

    Event updateEvent(Event event);

    void deleteEvent(int eventId);

    void sendNotifications(Event event);

    boolean checkActivationCondition(Event event);
}
