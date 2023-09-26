package com.swa.escape.service;

import com.swa.escape.domain.Event;
import com.swa.escape.dto.EventCreateRequest;
import com.swa.escape.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService implements EventServiceImpl {

    private final EventRepository eventRepository;

    // 이벤트 생성
    @Override
    public Event createEvent(EventCreateRequest eventRequest) {
        Event newEvent = new Event();
        newEvent.setLatitude(eventRequest.getEvent_latitude());
        newEvent.setLongitude(eventRequest.getEvent_longitude());

        return eventRepository.save(newEvent);
    }

    // 전체 이벤트 조회
    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    // 개별 이벤트 조회
    @Override
    public Optional<Event> getEvent(int eventId) {
        return eventRepository.findById(eventId);
    }

    // 이벤트 수정
    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    // 이벤트 삭제
    @Override
    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }

    // 알림 생성
    @Override
    public void sendNotifications(Event event) {

    }

    // 이벤트가 활성되는 조건 검사 -> 10 인지 아닌지
    @Override
    public boolean checkActivationCondition(Event event) {
        return false;
    }
}
