package com.swa.escape.service;

import com.swa.escape.domain.Event;
import com.swa.escape.domain.EventStatus;
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

        // api 를 사용해 위도/경도를 지역 이름으로 변경하여 eventRegion 으로 저장
        String region = addressConverter(eventRequest.getEvent_latitude(), eventRequest.getEvent_longitude());
        newEvent.setEventRegion(region);

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


    // 이벤트 삭제
    @Override
    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }

  
    // 이벤트 활성화
    public void enableEvent(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event activeEvent = optionalEvent.get();
            activeEvent.setEventStatus(EventStatus.ACTIVE);

            eventRepository.save(activeEvent);

            // 활성화 후 알림을 ios 에게 전달해야 함
        }
    }


    // 주소 변환 메소드
    public String addressConverter(float latitude, float longitude) {

        return null;
    }

  
    // 알림 생성
    @Override
    public void sendNotifications(Event event) {

    }


}
