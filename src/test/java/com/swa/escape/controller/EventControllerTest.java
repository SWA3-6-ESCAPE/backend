package com.swa.escape.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swa.escape.dto.EventCreateRequest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("CREATE EVENT")

    void createEvent() throws Exception{

        // given
        EventCreateRequest eventCreateRequest = new EventCreateRequest();
        eventCreateRequest.setEvent_latitude(1.0F);
        eventCreateRequest.setEvent_longitude(1.0F);

        // when
        mockMvc.perform(
                    post("/api/event")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(eventCreateRequest)))
        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.latitude").value(1.0F))
                .andExpect(jsonPath("$.longitude").value(1.0F))
                .andDo(print());
    }

    @Test
    @DisplayName("GET EVENT")
    void getEvent() throws  Exception {

        // Given
        int event_id = 1;

        // When
        mockMvc.perform(get(("/api/event/" + event_id))).andExpect(status().isOk())

                // THEN
                .andExpect(jsonPath("$.eventId").value(1))
                .andExpect(jsonPath("$.latitude").value(1.0F))
                .andExpect(jsonPath("$.longitude").value(1.0F))
                .andDo(print());
    }



    @Test
    @DisplayName("DELETE EVENT")
    void deleteEvent() throws Exception {

        // Given
        int eventId = 1;

        // When
        mockMvc.perform(get(("/api/event/" + eventId))).andExpect(status().isOk())
                // THEN
                .andExpect(jsonPath("$.eventId").value(1))
                .andExpect(jsonPath("$.latitude").value(1.0F))
                .andExpect(jsonPath("$.longitude").value(1.0F))
                .andDo(print());
    }

    //    @Test
    //    public void patchEventTest() throws Exception{
    //        // given
    //        int event_id = 1;
    //
    //    }
}