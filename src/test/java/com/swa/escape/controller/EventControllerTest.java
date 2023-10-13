package com.swa.escape.controller;

import com.swa.escape.domain.Event;

import com.swa.escape.service.EventService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @MockBean
    private EventService eventService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("CREATE EVENT")

    void createEvent() {
    }

    @Test
    @DisplayName("GET ALL EVENTS")
    void getAllEvents() throws Exception {

        // Given
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

        // When
        when(eventService.getEvents()).thenReturn(events);
        mockMvc.perform(get("/api/event"))
                // Then
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("GET EVENT")
    void getEvent() {
    }



    @Test
    @DisplayName("DELETE EVENT")
    void deleteEvent() {
    }
}
