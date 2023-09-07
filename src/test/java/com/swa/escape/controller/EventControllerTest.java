package com.swa.escape.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swa.escape.domain.Event;
import com.swa.escape.dto.EventCreateRequest;
import com.swa.escape.dto.ReportModifyRequest;
import jakarta.persistence.AssociationOverride;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    void createEvent() throws IOException, Exception{

        EventCreateRequest eventCreateRequest = new EventCreateRequest();
        eventCreateRequest.setEvent_latitude(1.0F);
        eventCreateRequest.setEvent_longitude(1.0F);
//        eventController
        mockMvc.perform(
                    post("/api/event")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(eventCreateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.latitude").value(1.0F))
                .andExpect(jsonPath("$.longitude").value(1.0F))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("GET EVENT")
    void getEvent() throws  Exception, IOException{

        // Given
        int event_id = 1;

        // When
        mockMvc.perform(get(("/api/event/" + event_id))).andExpect(status().isOk())

                // THEN
                .andExpect(jsonPath("$.eventId").value(1))
                .andExpect(jsonPath("$.latitude").value(1.0F))
                .andExpect(jsonPath("$.longitude").value(1.0F))
                .andDo(MockMvcResultHandlers.print());
    }

    // 쓰지 않을 예정 -> Event를 수정하면 잘못된 정보가 갱신될 수도 있음.
    //
    //    @Test
    //    void updateEvent() {
    //
    //    }

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
                .andDo(MockMvcResultHandlers.print());
    }
}