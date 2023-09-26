package com.swa.escape.service;

import com.swa.escape.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
    void getEvents() {
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
    void sendNotifications() {
    }

    @Test
    void checkActivationCondition() {
    }
}